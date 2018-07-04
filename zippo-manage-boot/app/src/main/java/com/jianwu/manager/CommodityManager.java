package com.jianwu.manager;


import com.jianwu.domain.ZipCommodity;
import com.jianwu.domain.request.ZipCommodityRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;

/**
 * @Author:lijin
 * @Date:16:10 2018/6/1
 * @Remark:
 */
public interface CommodityManager {

    /*
    分页模糊查询商品列表
     */
    ResultResponse list(Page page, String commodityOrder, String commodityName, Integer status, Integer commodityStatus);

    /*
    新增修改
     */
    ResultResponse addOrUpdate(ZipCommodityRequest zipCommodityRequest);

    /**
     * @ClassName: CommodityManager
     * @Description: 购买自定义商品的时候 保存自定义商品
     * @Author: chenDong
     * @Date: 2018/6/12 18:25
     * @Remark:
     */
    ResultResponse buyCommodity(ZipCommodityRequest zipCommodityRequest);
    /*
    删除
     */
    ResultResponse delete(Integer id);

    /**
     * @ClassName: CommodityManager
     * @Description: 获取商品详情通过id
     * @Author: chenDong
     * @Date: 2018/6/2 11:54
     * @Remark:
     */
    ResultResponse findCommodityById(Integer id);

    /**
     * @ClassName: CommodityManager
     * @Description: 获取商品信息
     * @Author: chenDong
     * @Date: 2018/6/13 9:28
     * @Remark:
     */
    ZipCommodity findCommodityById2(Integer id);

}
