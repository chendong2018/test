package com.jianwu.manager.impl;

import com.jianwu.dao.ZipCustomerServiceUserDao;
import com.jianwu.domain.ZipCustomerServiceUser;
import com.jianwu.domain.enums.ZippoStatus;
import com.jianwu.domain.request.ZipCustomerServiceUserRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.PageResult;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.domain.result.ZipCustomerServiceUserResponse;
import com.jianwu.example.ZipCustomerServiceUserExample;
import com.jianwu.manager.CustomerManager;
import com.jianwu.utils.TransferUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:lijin
 * @Date:15:26 2018/6/1
 * @Remark:
 */
@Service
@Transactional(readOnly = true)
public class CustomerManagerImpl implements CustomerManager{

    @Autowired
    ZipCustomerServiceUserDao zipCustomerServiceUserDao;

    @Override
    public ResultResponse list(Page page, String customerServiceName, String wechatNumber, Integer status) {
        PageResult pageResult = new PageResult();
        Integer start = page.getStart();
        Integer end = page.getPageSize();
        List<ZipCustomerServiceUserResponse> list = new ArrayList<>();
        if(null==status){
            status = 2;
        }
        int total = zipCustomerServiceUserDao.queryLikeCount(customerServiceName,wechatNumber,status);
        if(total>0){
            list = zipCustomerServiceUserDao.queryLike(start,end,customerServiceName,wechatNumber,status);
        }
        pageResult.setSuccess(true);
        pageResult.setTotal(total);
        pageResult.setData(list);
        return ResultResponse.success(pageResult);
    }

    @Override
    @Transactional(readOnly = false)
    public ResultResponse addOrUpdate(ZipCustomerServiceUserRequest zipCustomerServiceUserRequest) {
        if(null==zipCustomerServiceUserRequest){
            return ResultResponse.error("500","参数为空");
        }
        int num = 0;
        ZipCustomerServiceUser zipCustomerServiceUser = new ZipCustomerServiceUser();
        if(null == zipCustomerServiceUserRequest.getId()){//新增
            TransferUtil.transfer(zipCustomerServiceUserRequest,zipCustomerServiceUser);
            num = zipCustomerServiceUserDao.insertSelective(zipCustomerServiceUser);
        }else {//修改
            TransferUtil.transfer(zipCustomerServiceUserRequest,zipCustomerServiceUser);
            num = zipCustomerServiceUserDao.updateByPrimaryKeySelective(zipCustomerServiceUser);
        }
        if(num>0){
            return ResultResponse.success("操作成功");
        }
        return ResultResponse.error("500","操作失败");
    }

    @Override
    @Transactional(readOnly = false)
    public ResultResponse delete(Integer id) {
        if(null!=id){
            return ResultResponse.success(zipCustomerServiceUserDao.deleteByPrimaryKey(id));
        }
        return ResultResponse.error("500","参数为空");
    }

    @Override
    public ResultResponse customerInfo() {
        ZipCustomerServiceUserExample example = new ZipCustomerServiceUserExample();
        example.or().andStatusEqualTo(0);
        List<ZipCustomerServiceUser> list = zipCustomerServiceUserDao.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return ResultResponse.error("500","暂无客服信息");
        }
        return ResultResponse.success(list.get(0));
    }

    @Override
    public ResultResponse getInfo(Integer id) {
        if(null==id){
            return ResultResponse.error("500","参数为空");
        }
        ZipCustomerServiceUserExample zipCustomerServiceUserExample = new ZipCustomerServiceUserExample();
        zipCustomerServiceUserExample.or().andIdEqualTo(id);
        List<ZipCustomerServiceUser> list = zipCustomerServiceUserDao.selectByExample(zipCustomerServiceUserExample);
        ZipCustomerServiceUserResponse zipCustomerServiceUserResponse = new ZipCustomerServiceUserResponse();
        if(list.size()>0){
            ZipCustomerServiceUser zipCustomerServiceUser = list.get(0);
            TransferUtil.transfer(zipCustomerServiceUser,zipCustomerServiceUserResponse);
        }
        return ResultResponse.success(zipCustomerServiceUserResponse);
    }

    @Override
    @Transactional(readOnly = false)
    public ResultResponse enableDisable(Integer status, Integer id) {
        List<ZipCustomerServiceUser> list = new ArrayList<>();
        if(ZippoStatus.OPEN.getStatus().equals(status)){
            ZipCustomerServiceUserExample example = new ZipCustomerServiceUserExample();
            example.or().andStatusEqualTo(ZippoStatus.OPEN.getStatus());
            list = zipCustomerServiceUserDao.selectByExample(example);
        }
        if(list.size()>0){
            return ResultResponse.error("500","已有启用的用户");
        }
        ZipCustomerServiceUser zipCustomerServiceUser = new ZipCustomerServiceUser();
        zipCustomerServiceUser.setId(id);
        zipCustomerServiceUser.setStatus(status);
        return ResultResponse.success(zipCustomerServiceUserDao.updateByPrimaryKeySelective(zipCustomerServiceUser));
    }

}
