package com.jianwu.service;


import com.jianwu.domain.request.ZipOrderFormRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;

/**
 * @Author:lijin
 * @Date:11:45 2018/6/2
 * @Remark:
 */
public interface OrderFormService {

    /*
    分页模糊查询订单列表
     */
    ResultResponse list(Page page, String startTime, String endTime, Integer status, Integer wechatUserId, String wechatUserName, String orderNumber, String openId);

    /*
    新增修改
     */
    ResultResponse addOrUpdate(ZipOrderFormRequest zipOrderFormRequest);

    /*
    获取单条信息接口
     */
    ResultResponse getInfo(Integer id);

    ResultResponse getInfoByOrderNo(String orderNo);

    /**
     * @ClassName: OrderFormService
     * @Description: 更新订单状态
     * @Author: chenDong
     * @Date: 2018/6/12 16:11
     * @Remark:
     */
    ResultResponse updateOrderForm(String orderNumber, Integer status);
}
