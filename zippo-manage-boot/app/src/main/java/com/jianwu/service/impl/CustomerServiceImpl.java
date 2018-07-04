package com.jianwu.service.impl;


import com.jianwu.domain.request.ZipCustomerServiceUserRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.manager.CustomerManager;
import com.jianwu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:lijin
 * @Date:15:25 2018/6/1
 * @Remark:
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerManager customerManager;

    @Override
    public ResultResponse list(Page page, String customerServiceName, String wechatNumber, Integer status) {
        return customerManager.list(page,customerServiceName,wechatNumber,status);
    }

    @Override
    public ResultResponse addOrUpdate(ZipCustomerServiceUserRequest zipCustomerServiceUserRequest) {
        return customerManager.addOrUpdate(zipCustomerServiceUserRequest);
    }

    @Override
    public ResultResponse delete(Integer id) {
        return customerManager.delete(id);
    }

    @Override
    public ResultResponse getInfo(Integer id) {
        return customerManager.getInfo(id);
    }

    @Override
    public ResultResponse enableDisable(Integer status, Integer id) {
        if(null==status||null==id){
            return ResultResponse.error("500","参数为空");
        }
        return customerManager.enableDisable(status,id);
    }

}
