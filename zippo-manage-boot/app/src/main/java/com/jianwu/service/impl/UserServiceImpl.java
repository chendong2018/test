package com.jianwu.service.impl;

import com.jianwu.comm.Const;
import com.jianwu.domain.ZipManageUser;
import com.jianwu.domain.request.LoginRequest;
import com.jianwu.domain.request.ZipSysUserRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.PageResult;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.domain.result.ZipSysUserResponse;
import com.jianwu.manager.UserManager;
import com.jianwu.service.UserService;
import com.jianwu.utils.PhoneUtil;
import com.jianwu.utils.TransferUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserManager userManager;


    @Override
    public ResultResponse login(LoginRequest loginRequest) {
        if(loginRequest == null || StringUtils.isEmpty(loginRequest.getPhone())){
            ResultResponse.error("","账号不能为空");
        }
        if(loginRequest.getPhone().length() != 11 ){
            return ResultResponse.error("","请输入11位手机号码！");
        }
        if(!PhoneUtil.isChinaPhoneLegal(loginRequest.getPhone())){
            return ResultResponse.error("","请输入正确的手机号码！");
        }
        if(loginRequest == null || StringUtils.isEmpty(loginRequest.getPassword())){
            ResultResponse.error("","密码不能为空");
        }
        //获取用户信息
        ZipManageUser user = userManager.findUserByPhone(loginRequest.getPhone());
        if(user == null){
            return ResultResponse.error("","用户或密码不正确,请确认！");
        }
        //验证登录密码是否正确
        Boolean result = userManager.checkPassword(loginRequest.getPassword(),user.getPassword());
        if(!result){
            return ResultResponse.error("","密码不正确，请重新输入!");
        }
        return ResultResponse.success(user);
    }

    @Override
    public ResultResponse list(Integer userId,Page page) {
        int total = userManager.countUser();
        PageResult pageResult = null;
        if(total == 0){
            pageResult = new PageResult(true,"",null,0);
            return ResultResponse.success(pageResult);
        }
        Integer start = page.getStart();
        Integer end = page.getPageSize();
        List<ZipManageUser> list = userManager.findUserByPage(start,end);
        List<ZipSysUserResponse> responseList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(list)){
            responseList = TransferUtil.transferList(list,ZipSysUserResponse.class);
        }
        pageResult = new PageResult(true,"获取成功",responseList,total);
        return ResultResponse.success(pageResult);
    }

    @Override
    @Transactional(readOnly = false)
    public ResultResponse addOrUpdate(ZipSysUserRequest userRequest) {
        if(userRequest == null || StringUtils.isEmpty(userRequest.getName())){
            return ResultResponse.error("","用户姓名不能为空");
        }
        if(userRequest == null || StringUtils.isEmpty(userRequest.getPhone())){
            return ResultResponse.error("","账户不能为空");
        }
        if(userRequest.getPhone().length() != 11 ){
            return ResultResponse.error("","请输入11位手机号码！");
        }
        if(!PhoneUtil.isChinaPhoneLegal(userRequest.getPhone())){
            return ResultResponse.error("","请输入正确的手机号码！");
        }
        //验证账户对应用户是否存在

        Integer num = 0;
        if(userRequest.getId() == null || userRequest.getId() == 0){//新增
            Boolean userResult =  userManager.checkUserAttribute(userRequest.getPhone());
            if(userResult){
                return ResultResponse.error("","用户已经存在，请确认！");
            }
            num = userManager.add(userRequest);
        }else{//修改
            num = userManager.update(userRequest);
        }
        if(num > 0){
            return ResultResponse.success("操作成功");
        }
       return ResultResponse.error("","操作失败");
    }

    @Transactional(readOnly = false)
    public Integer addUser(ZipSysUserRequest userRequest){
        Integer userId = 0;
        ZipManageUser user = userManager.findUserByPhone(userRequest.getPhone());
        if(user == null){

        }else{
            userId = user.getId();
        }
        return  userId;
    }

    @Override
    @Transactional(readOnly = false)
    public ResultResponse delete(Integer id) {
        if(id == null){
            return ResultResponse.error("","参数为空");
        }
        Integer num = userManager.delete(id);
        if(num > 0 ){
            return ResultResponse.success("删除成功");
        }
        return ResultResponse.error("","删除失败");
    }

    @Override
    public ResultResponse queryLike(Page page, String name, String phone) {
        return userManager.queryLike(page,name,phone);
    }

    @Override
    @Transactional(readOnly = false)
    public ResultResponse editPwd(Integer userId,String oldPwd, String newPwd) {
        ZipManageUser zipManageUser = userManager.findUserById(userId);
        if(zipManageUser == null){
            return ResultResponse.error("","用户信息已经删除");
        }
        String password = zipManageUser.getPassword();
        if(!password.equals(oldPwd)){
            return ResultResponse.error("","旧密码输入错误");
        }
        ZipSysUserRequest zipSysUserRequest = new ZipSysUserRequest();
        zipSysUserRequest.setId(zipManageUser.getId());
        zipSysUserRequest.setPassword(newPwd);
        int num = userManager.update(zipSysUserRequest);
        if (num > 0) {
            return ResultResponse.success("修改用户密码成功");
        }
        return ResultResponse.error("","修改用户密码失败");
    }

    @Override
    public ResultResponse getUserResponse(Integer userId) {
        ZipManageUser zipManageUser = userManager.findUserById(userId);
        ZipSysUserResponse zipSysUserResponse = new ZipSysUserResponse();
        if(null!=zipManageUser){
            TransferUtil.transfer(zipManageUser,zipSysUserResponse);
        }
        return ResultResponse.success(zipSysUserResponse);
    }

}
