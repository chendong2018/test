package com.jianwu.service.impl;


import com.jianwu.domain.request.ZipCommodityRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.manager.CommodityManager;
import com.jianwu.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:lijin
 * @Date:16:10 2018/6/1
 * @Remark:
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    CommodityManager commodityManager;

    @Override
    public ResultResponse list(Page page, String commodityOrder, String commodityName, Integer status, Integer commodityStatus) {
        return commodityManager.list(page,commodityOrder,commodityName,status,commodityStatus);
    }

    @Override
    public ResultResponse addOrUpdate(ZipCommodityRequest zipCommodityRequest) {
        return commodityManager.addOrUpdate(zipCommodityRequest);
    }

    @Override
    public ResultResponse delete(Integer id) {
        return commodityManager.delete(id);
    }

    @Override
    public ResultResponse getInfo(Integer id) {
        return commodityManager.findCommodityById(id);
    }

}
