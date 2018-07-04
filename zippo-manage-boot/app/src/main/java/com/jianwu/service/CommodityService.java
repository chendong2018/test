package com.jianwu.service;


import com.jianwu.domain.request.ZipCommodityRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;

/**
 * @Author:lijin
 * @Date:16:10 2018/6/1
 * @Remark:
 */
public interface CommodityService {

    /*
    分页模糊查询商品列表
     */
    ResultResponse list(Page page, String commodityOrder, String commodityName, Integer status, Integer commodityStatus);

    /*
    新增修改
     */
    ResultResponse addOrUpdate(ZipCommodityRequest zipCommodityRequest);

    /*
    删除
     */
    ResultResponse delete(Integer id);

    /*
    获取单条详情
     */
    ResultResponse getInfo(Integer id);

}
