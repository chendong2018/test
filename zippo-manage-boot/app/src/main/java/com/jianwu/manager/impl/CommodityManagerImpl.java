package com.jianwu.manager.impl;

import com.jianwu.dao.ZipCommodityDao;
import com.jianwu.domain.ZipCommodity;
import com.jianwu.domain.ZipTexture;
import com.jianwu.domain.enums.CommodityStatus;
import com.jianwu.domain.enums.CommodityType;
import com.jianwu.domain.enums.ZippoStatus;
import com.jianwu.domain.request.ZipCommodityRequest;
import com.jianwu.domain.result.*;
import com.jianwu.manager.CommodityManager;
import com.jianwu.manager.OrderFormManager;
import com.jianwu.manager.TextureManager;
import com.jianwu.utils.TransferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author:lijin
 * @Date:16:11 2018/6/1
 * @Remark:
 */
@Service
@Transactional(readOnly = true)
public class CommodityManagerImpl implements CommodityManager {

    @Autowired
    ZipCommodityDao zipCommodityDao;

    @Autowired
    TextureManager  textureManager;

    @Autowired
    OrderFormManager orderFormManager;

    @Override
    public ResultResponse list(Page page, String commodityOrder, String commodityName, Integer status, Integer commodityStatus) {
        PageResult pageResult = new PageResult();
        Integer start = page.getStart();
        Integer end = page.getPageSize();
        List<ZipCommodityResponse> list = new ArrayList<>();
        if(null==status){
            status = 2;
        }
        if(null==commodityStatus){
            commodityStatus=1;
        }
        int total = zipCommodityDao.queryLikeCount(commodityOrder,commodityName,status,commodityStatus);
        if(total>0){
            list = zipCommodityDao.queryLike(start,end,commodityOrder,commodityName,status,commodityStatus);
        }
        pageResult.setSuccess(true);
        pageResult.setTotal(total);
        pageResult.setData(list);
        return ResultResponse.success(pageResult);
    }

    @Override
    @Transactional(readOnly = false)
    public ResultResponse addOrUpdate(ZipCommodityRequest zipCommodityRequest) {
        if(null==zipCommodityRequest){
            return ResultResponse.error("500","参数为空");
        }
        if(null==zipCommodityRequest.getCommodityName()){
            return ResultResponse.error("500","商品标题为空");
        }
        if(null==zipCommodityRequest.getCommodityOrder()){
            return ResultResponse.error("500","商品编号为空");
        }
        if(null==zipCommodityRequest.getPrice()){
            return ResultResponse.error("500","商品价格为空");
        }
        if(null==zipCommodityRequest.getSort()){
            return ResultResponse.error("500","商品顺序为空");
        }
        if(null==zipCommodityRequest.getTechnology()){
            return ResultResponse.error("500","商品工艺为空");
        }
        if(null==zipCommodityRequest.getTextureId()){
            return ResultResponse.error("500","商品材质id为空");
        }
        if(null==zipCommodityRequest.getStatus()){
            return ResultResponse.error("500","商品状态为空");
        }
        int num = 0;
        ZipCommodity zipCommodity = new ZipCommodity();
        if(null == zipCommodityRequest.getId()){//新增
            TransferUtil.transfer(zipCommodityRequest,zipCommodity);
            num = zipCommodityDao.insertSelective(zipCommodity);
        }else {//修改
            TransferUtil.transfer(zipCommodityRequest,zipCommodity);
            if(zipCommodity.getExpressType()==1){
                zipCommodity.setExpress(0d);
            }
            num = zipCommodityDao.updateByPrimaryKeySelective(zipCommodity);
        }
        if(num>0){
            return ResultResponse.success("操作成功");
        }
        return ResultResponse.error("500","操作失败");
    }

    @Override
    @Transactional(readOnly = false)
    public ResultResponse buyCommodity(ZipCommodityRequest zipCommodityRequest) {
        if(null==zipCommodityRequest){
            return ResultResponse.error("500","参数为空");
        }
        if(null==zipCommodityRequest.getTechnology()){
            return ResultResponse.error("500","商品工艺为空");
        }
        if(null==zipCommodityRequest.getTextureId()){
            return ResultResponse.error("500","商品材质id为空");
        }
        int num = 0;
        ZipCommodity zipCommodity = new ZipCommodity();
        if(null != zipCommodityRequest.getId()){
            //非自定义商品
            return ResultResponse.success(zipCommodityRequest.getId() );
        }
        //新增自定义商品
        TransferUtil.transfer(zipCommodityRequest,zipCommodity);
        //获取材质信息
        ResultResponse response = textureManager.getInfo(zipCommodityRequest.getTextureId());
        ZipTextureResponse zipTextureResponse = (ZipTextureResponse)response.getData();
        zipCommodity.setCommodityName(zipTextureResponse.getTextureName()+"彩印");
        zipCommodity.setCommodityOrder("zdy"+System.currentTimeMillis());
        zipCommodity.setExpress(0.0);
        zipCommodity.setExpressType(1);
        zipCommodity.setPrice(zipTextureResponse.getPrice().floatValue());
        zipCommodity.setTextureId(zipTextureResponse.getId());
        zipCommodity.setTechnology(1);
        zipCommodity.setStatus(CommodityStatus.PUTAWAY.getStatus());
        zipCommodity.setSort(999999);
        zipCommodity.setCommodityStatus(CommodityType.CUSTOM.getStatus());
        num = zipCommodityDao.insertSelective(zipCommodity);
        if(num>0){
            return ResultResponse.success(zipCommodity.getId());
        }
        return ResultResponse.error("500","操作失败");
    }

    @Override
    @Transactional(readOnly = false)
    public ResultResponse delete(Integer id) {
        if(null!=id){
            ZipCommodity zipCommodity = new ZipCommodity();
            zipCommodity.setId(id);
            zipCommodity.setStatus(ZippoStatus.DELETE.getStatus());
            return ResultResponse.success(zipCommodityDao.updateByPrimaryKeySelective(zipCommodity));
        }
        return ResultResponse.error("500","参数为空");
    }

    @Override
    public ResultResponse findCommodityById(Integer id) {
        if( id == null){
           return ResultResponse.error("500","参数错误");
        }
        ZipCommodity zipCommodity = zipCommodityDao.selectByPrimaryKey(id);
        if(zipCommodity == null){
            return ResultResponse.error("500","商品不存在");
        }
        if(zipCommodity.getStatus().equals(CommodityStatus.DELETE.getStatus()) ||
                Objects.equals(zipCommodity.getStatus(),CommodityStatus.SOLDOUT.getStatus()) ){
            return ResultResponse.error("500","商品已经下架或删除");
        }
        ZipCommodityResponse response = new ZipCommodityResponse();
        TransferUtil.transfer(zipCommodity,response);
        //获取商品材质信息
        ResultResponse resultResponse = textureManager.getInfo(response.getTextureId());
        if(!"200".equals(resultResponse.getCode())){
            return ResultResponse.error("","材质为空");
        }
        ZipTextureResponse zipTexture = (ZipTextureResponse)resultResponse.getData();
        response.setTextureName(zipTexture.getTextureName());
        //获取此商品的购买人数
        Integer num = orderFormManager.countNumByCommodityId(response.getId());
        response.setShopNum(num+"人");
        return ResultResponse.success(response);
    }

    @Override
    public ZipCommodity findCommodityById2(Integer id) {
        if( id == null){
            return null;
        }
        ZipCommodity zipCommodity = zipCommodityDao.selectByPrimaryKey(id);
        return zipCommodity;
    }
}
