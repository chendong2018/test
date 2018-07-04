package com.jianwu.service;


import com.jianwu.domain.request.LoginRequest;
import com.jianwu.domain.request.ZipSysUserRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;

public interface UserService {

    /**
     * @ClassName: UserService
     * @Description:
     * @Author: chenDong
     * @Date: 2018/2/5 14:38
     * @Remark: 登录接口
     */
    ResultResponse login(LoginRequest loginRequest);

    /**
     * @ClassName: UserService
     * @Description: 分页获取用户列表
     * @Author: chenDong
     * @Date: 2018/2/5 15:41
     * @Remark:
     */
    ResultResponse list(Integer userId,Page page);

    /**
     * @ClassName: UserService
     * @Description: 新增或修改
     * @Author: chenDong
     * @Date: 2018/2/5 15:42
     * @Remark:
     */
    ResultResponse addOrUpdate(ZipSysUserRequest userRequest);

    /**
     * 修改登录密码
     *
     * @param oldPwd      旧密码
     * @param newPwd      新密码
     * @return
     */
    ResultResponse editPwd(Integer userId,String oldPwd, String newPwd);

    /**
     * @ClassName: UserService
     * @Description: 获取登录用户的信息
     * @Author: chenDong
     * @Date: 2018/2/6 9:45
     * @Remark:
     */
    ResultResponse getUserResponse(Integer userId);

    ResultResponse delete(Integer id);

    /*
    按条件查询后台登陆用户信息
     */
    ResultResponse queryLike(Page page, String name, String phone);

}
