package com.jianwu.manager;

import com.jianwu.domain.ZipManageUser;
import com.jianwu.domain.request.ZipSysUserRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;

import java.util.List;

/**
 * 用户业务逻辑接口
 *
 * @Author chendong
 * @Create 2018/3/5
 * @Time 14:14
 **/
public interface UserManager {

    /**
     * @ClassName: UserManager
     * @Description:
     * @Author: chenDong
     * @Date: 2018/2/5 15:16
     * @Remark: 获取用户信息根据账号
     */
    ZipManageUser findUserByPhone(String phone);

    /**
     * @ClassName: UserManager
     * @Description:
     * @Author: chenDong
     * @Date: 2018/2/5 15:16
     * @Remark: 验证密码是否正确
     */
    Boolean checkPassword(String password, String targetPassword);


    /**
     * @ClassName: UserManager
     * @Description: 新增用户信息
     * @Author: chenDong
     * @Date: 2018/2/5 16:21
     * @Remark:
     */
    Integer add(ZipSysUserRequest zipSysUserRequest);

    /**
     * @ClassName: UserManager
     * @Description: 修改用户信息
     * @Author: chenDong
     * @Date: 2018/2/5 16:22
     * @Remark:
     */
    Integer update(ZipSysUserRequest rowSysUserRequest);

    /**
     * @ClassName: UserManager
     * @Description: 获取用户信息 通过id
     * @Author: chenDong
     * @Date: 2018/2/5 16:22
     * @Remark:
     */
    ZipManageUser findUserById(Integer userId);

    /**
     * @ClassName: UserManager
     * @Description: 获取用户信息数量
     * @Author: chenDong
     * @Date: 2018/2/5 16:32
     * @Remark:
     */
    int countUser();

    /**
     * @ClassName: UserManager
     * @Description: 分页获取用户信息
     * @Author: chenDong
     * @Date: 2018/2/5 16:32
     * @Remark:
     */
    List<ZipManageUser> findUserByPage(Integer start, Integer end);

    /**
     * @ClassName: UserManager
     * @Description: 验证账户属性是否存在
     * @Author: chenDong
     * @Date: 2018/2/6 14:47
     * @Remark:
     */
    Boolean checkUserAttribute(String phone);

    /*
    删除
     */
    Integer delete(Integer id);

    /*
    按条件查询后台登陆用户信息
     */
    ResultResponse queryLike(Page page, String name, String phone);

}
