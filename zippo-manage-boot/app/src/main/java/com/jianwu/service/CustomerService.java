package com.jianwu.service;


import com.jianwu.domain.request.ZipCustomerServiceUserRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;

/**
 * @Author:lijin
 * @Date:15:24 2018/6/1
 * @Remark:
 */
public interface CustomerService {

    /*
    分页模糊查询客服列表
     */
    ResultResponse list(Page page, String customerServiceName, String wechatNumber, Integer status);

    /*
    新增修改
     */
    ResultResponse addOrUpdate(ZipCustomerServiceUserRequest zipCustomerServiceUserRequest);

    /*
    删除
     */
    ResultResponse delete(Integer id);

    /*
    获取单条详情
     */
    ResultResponse getInfo(Integer id);

    /*
  启用禁用
   */
    ResultResponse enableDisable(Integer status, Integer id);

}
