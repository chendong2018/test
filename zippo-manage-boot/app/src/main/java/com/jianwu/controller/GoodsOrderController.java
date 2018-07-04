package com.jianwu.controller;

import com.jianwu.comm.aop.LoggerManage;
import com.jianwu.domain.request.Order;

import com.jianwu.domain.result.ResultResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zip/goodsOrder")
public class GoodsOrderController extends BaseController {


    @RequestMapping(value = "/saveByCart", method = RequestMethod.POST)
    @LoggerManage(description = "下单")
    public ResultResponse saveByCart(String openId) {
        Order order=new Order();

        order.setTradeNo(System.currentTimeMillis()+"");
        return ResultResponse.success(order );
    }

}
