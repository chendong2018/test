package com.jianwu.manager.impl;

import com.jianwu.dao.ZipManageUserDao;
import com.jianwu.domain.ZipManageUser;
import com.jianwu.domain.enums.ZippoStatus;
import com.jianwu.domain.request.ZipSysUserRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.PageResult;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.domain.result.ZipSysUserResponse;
import com.jianwu.example.ZipManageUserExample;
import com.jianwu.manager.UserManager;
import com.jianwu.utils.MD5Util;
import com.jianwu.utils.TransferUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserManagerImpl implements UserManager {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ZipManageUserDao zippoManageUserDao;

    @Override
    public ZipManageUser findUserByPhone(String phone) {
        if(StringUtils.isEmpty(phone)){
            return null;
        }
        ZipManageUserExample example = new ZipManageUserExample();
        example.or().andPhoneEqualTo(phone).andStatusEqualTo(ZippoStatus.OPEN.getStatus());
        List<ZipManageUser> zippoManageUserList = zippoManageUserDao.selectByExample(example);
        if(CollectionUtils.isEmpty(zippoManageUserList)){
            return null;
        }
        return zippoManageUserList.get(0);
    }

    @Override
    public Boolean checkPassword(String password,String targetPassword) {
//        String newPassword = Md5Utils.digest(password);
        String oldPassword = targetPassword;
        return oldPassword.equals(password) ? true : false;
    }

    @Override
    @Transactional(readOnly = false)
    public Integer add(ZipSysUserRequest zipSysUserRequest) {
        if(zipSysUserRequest == null){
            return 0;
        }
        ZipManageUser zipManageUser = new ZipManageUser();
        TransferUtil.transfer(zipSysUserRequest,zipManageUser);
        //新增 账户信息 默认密码123456
        zipManageUser.setPassword(MD5Util.digest("123456"));

        ZipManageUserExample example = new ZipManageUserExample();
        example.or().andPhoneEqualTo(zipSysUserRequest.getPhone()).andStatusEqualTo(ZippoStatus.DELETE.getStatus());
        List<ZipManageUser> zipManageUsers = zippoManageUserDao.selectByExample(example);
        if(CollectionUtils.isNotEmpty(zipManageUsers)){//修改这一条
            zipManageUser.setId(zipManageUsers.get(0).getId());
            zipManageUser.setStatus(ZippoStatus.OPEN.getStatus());
            return zippoManageUserDao.updateByPrimaryKeySelective(zipManageUser);
        }
        return zippoManageUserDao.insertSelective(zipManageUser);
    }

    @Override
    @Transactional(readOnly = false)
    public Integer update(ZipSysUserRequest zipSysUserRequest) {
        if(zipSysUserRequest == null){
            return 0;
        }
        ZipManageUserExample example = new ZipManageUserExample();
        example.or().andStatusEqualTo(ZippoStatus.OPEN.getStatus()).andPhoneEqualTo(zipSysUserRequest.getPhone());
        List<ZipManageUser> list = zippoManageUserDao.selectByExample(example);
        if(list.size()>1){
            return 0;
        }
        ZipManageUser user = new ZipManageUser();
        TransferUtil.transfer(zipSysUserRequest,user);
        return zippoManageUserDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public ZipManageUser findUserById(Integer userId) {
        if(userId == null || userId == 0){
            return null;
        }
        ZipManageUserExample example = new ZipManageUserExample();
        example.or().andIdEqualTo(userId).andStatusEqualTo(ZippoStatus.OPEN.getStatus());
        List<ZipManageUser> rowSysUserList = zippoManageUserDao.selectByExample(example);
        ZipManageUser zipManageUser = new ZipManageUser();
        if(!CollectionUtils.isEmpty(rowSysUserList)){
            zipManageUser = rowSysUserList.get(0);
        }
        return zipManageUser;
    }

    @Override
    public int countUser() {
        ZipManageUserExample example = new ZipManageUserExample();
        example.or().andStatusEqualTo(ZippoStatus.OPEN.getStatus());
        return zippoManageUserDao.countByExample(example);
    }

    @Override
    public List<ZipManageUser> findUserByPage(Integer start, Integer end) {
        return zippoManageUserDao.findUserByPage(start,end);
    }

    @Override
    public Boolean checkUserAttribute(String phone) {
        if(StringUtils.isEmpty(phone)){
            return false;
        }
        ZipManageUserExample example = new ZipManageUserExample();
        example.or().andStatusEqualTo(ZippoStatus.OPEN.getStatus()).andPhoneEqualTo(phone);
        List<ZipManageUser> list = zippoManageUserDao.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            //用户不存在 说明没有其他信息了
            return false;
        }
        return true;
    }

    @Override
    @Transactional(readOnly = false)
    public Integer delete(Integer id) {
        ZipManageUserExample example = new ZipManageUserExample();
        example.or().andIdEqualTo(id);
        List<ZipManageUser> list = zippoManageUserDao.selectByExample(example);
        ZipManageUser zipManageUser = list.get(0);
        zipManageUser.setStatus(ZippoStatus.DELETE.getStatus());
        return zippoManageUserDao.updateByPrimaryKeySelective(zipManageUser);
    }

    @Override
    public ResultResponse queryLike(Page page, String name, String phone) {
        PageResult pageResult = new PageResult();
        Integer start = page.getStart();
        Integer end = page.getPageSize();
        List<ZipSysUserResponse> list = zippoManageUserDao.queryLike(start,end,name,phone);
        int total = zippoManageUserDao.queryLikeCount(name,phone);
        if(total>0){
            pageResult.setSuccess(true);
            pageResult.setTotal(total);
            pageResult.setData(list);
        }
        return ResultResponse.success(pageResult);
    }
}
