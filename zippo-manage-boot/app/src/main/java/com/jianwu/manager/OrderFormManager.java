package com.jianwu.manager;


import com.jianwu.domain.ZipOrderForm;
import com.jianwu.domain.request.ZipOrderFormRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;

/**
 * @Author:lijin
 * @Date:11:46 2018/6/2
 * @Remark:
 */
public interface OrderFormManager {

    /*
    分页模糊查询订单列表
     */
    ResultResponse list(Page page, String startTime, String endTime, Integer status, Integer wechatUserId,
                        String wechatUserName, String orderNumber, String openId);

    /*
    新增修改
     */
    ResultResponse addOrUpdate(ZipOrderFormRequest zipOrderFormRequest);

    /*
   提交订单
    */
    ResultResponse submitOrder(ZipOrderFormRequest zipOrderFormRequest);

    /*
    获取单条详情
     */
    ResultResponse getInfo(Integer id);

    /*
    获取各种订单状态的数量
     */
    Integer getOrderSum(Integer userId, Integer status);

    /**
     * @ClassName: OrderFormManager
     * @Description: 获取订单数量 小程序各种状态订单数量
     * @Author: chenDong
     * @Date: 2018/6/4 17:17
     * @Remark:
     */
    ResultResponse getOrderSumByOpenId(String openId);

    /*
    删除订单，确认收货订单
     */
    ResultResponse operationOrder(Integer orderId, Integer status);

    /**
     * @ClassName: OrderFormManager
     * @Description:  获取商品的购买数量
     * @Author: chenDong
     * @Date: 2018/6/7 14:29
     * @Remark:
     */
    Integer countNumByCommodityId(Integer commodityId);

    ResultResponse getInfoByOrderNo(String orderNo);

    /**
     * @ClassName: OrderFormManager
     * @Description: 修改订单状态
     * @Author: chenDong
     * @Date: 2018/6/12 16:17
     * @Remark:
     */
    Integer updateOrderForm(ZipOrderForm zipOrderForm);
}
