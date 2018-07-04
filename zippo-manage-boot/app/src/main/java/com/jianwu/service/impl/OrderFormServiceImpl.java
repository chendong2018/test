package com.jianwu.service.impl;

import com.jianwu.domain.ZipOrderForm;
import com.jianwu.domain.request.ZipOrderFormRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.manager.OrderFormManager;
import com.jianwu.service.OrderFormService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:lijin
 * @Date:11:46 2018/6/2
 * @Remark:
 */
@Service
public class OrderFormServiceImpl implements OrderFormService {

    @Autowired
     OrderFormManager orderFormManager;

    @Override
    public ResultResponse list(Page page, String startTime, String endTime, Integer status, Integer wechatUserId, String wechatUserName, String orderNumber, String openId) {
        return orderFormManager.list(page,startTime,endTime,status,wechatUserId,wechatUserName,orderNumber,openId);
    }

    @Override
    public ResultResponse addOrUpdate(ZipOrderFormRequest zipOrderFormRequest) {
        return orderFormManager.addOrUpdate(zipOrderFormRequest);
    }

    @Override
    public ResultResponse getInfo(Integer id) {
        return orderFormManager.getInfo(id);
    }

    @Override
    public ResultResponse getInfoByOrderNo(String orderNo) {
        return orderFormManager.getInfoByOrderNo(orderNo);
    }

    @Override
    public ResultResponse updateOrderForm(String orderNumber, Integer status) {
        ResultResponse resultResponse = orderFormManager.getInfoByOrderNo(orderNumber);
        ZipOrderForm zipOrderForm = new ZipOrderForm();
        if(resultResponse.getCode().equals("200")){
            zipOrderForm = (ZipOrderForm)resultResponse.getData();
        }else{
            return resultResponse;
        }
        zipOrderForm.setStatus(status);
        orderFormManager.updateOrderForm(zipOrderForm);
        return ResultResponse.success("");
    }
}
