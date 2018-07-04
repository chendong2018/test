package com.jianwu.manager.impl;

import com.jianwu.dao.ZipAddressDao;
import com.jianwu.dao.ZipCommodityDao;
import com.jianwu.dao.ZipOrderFormDao;
import com.jianwu.dao.ZipTextureDao;
import com.jianwu.domain.*;
import com.jianwu.domain.enums.OrderStatus;
import com.jianwu.domain.enums.ZippoStatus;
import com.jianwu.domain.request.OrderNum;
import com.jianwu.domain.request.ZipOrderFormRequest;
import com.jianwu.domain.result.*;
import com.jianwu.example.ZipAddressExample;
import com.jianwu.example.ZipCommodityExample;
import com.jianwu.example.ZipOrderFormExample;
import com.jianwu.example.ZipTextureExample;
import com.jianwu.manager.ClientManager;
import com.jianwu.manager.CommodityManager;
import com.jianwu.manager.OrderFormManager;

import com.jianwu.manager.TextureManager;
import com.jianwu.utils.DateUtils;
import com.jianwu.utils.OrderRandomUtil;
import com.jianwu.utils.StringUtil;
import com.jianwu.utils.TransferUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:lijin
 * @Date:11:47 2018/6/2
 * @Remark:
 */
@Service
@Transactional(readOnly = true)
public class OrderFormManagerImpl implements OrderFormManager {

    @Autowired
    ZipOrderFormDao zipOrderFormDao;

    @Autowired
    ZipCommodityDao zipCommodityDao;

    @Autowired
    ZipAddressDao zipAddressDao;

    @Autowired
    ZipTextureDao zipTextureDao;

    @Autowired
    ClientManager clientManager;

    @Autowired
    CommodityManager commodityManager;

    @Autowired
    TextureManager textureManager;

    @Override
    public ResultResponse list(Page page, String startTime, String endTime, Integer status, Integer wechatUserId, String wechatUserName, String orderNumber, String openId) {
        PageResult pageResult = new PageResult();
        Integer start = page.getStart();
        Integer end = page.getPageSize();
        Date sTime = null;
        Date eTime = null;
        try {
            if(StringUtils.isNotBlank(startTime)){
                sTime = DateUtils.formatStringToDate(startTime);
            }
            if(StringUtils.isNotBlank(endTime)){
                eTime = DateUtils.formatStringToDate(endTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ZipWechatUser user = clientManager.findWeChatUserByOpenId(openId);
        List<ZipOrderFormResponse> list = new ArrayList<>();
        Integer userId = null;
        if(null!=user){
            userId = user.getId();
        }
        if(0==status||5==status){
            status=null;
        }
        int total = zipOrderFormDao.queryLikeCount(sTime,eTime,status,userId,wechatUserName,orderNumber);
        if(total>0){
            list = zipOrderFormDao.queryLike(start,end,sTime,eTime,status,userId,wechatUserName,orderNumber);
            for (ZipOrderFormResponse zipOrderFormResponse : list){
                if(null!=zipOrderFormResponse.getTextureId()){
                    ZipTextureExample example = new ZipTextureExample();
                    example.or().andIdEqualTo(zipOrderFormResponse.getTextureId());
                    List<ZipTexture> list1 = zipTextureDao.selectByExample(example);
                    if(list1.size()>0){
                        zipOrderFormResponse.setTextureName(list1.get(0).getTextureName());
                    }
                }
                if(zipOrderFormResponse.getCommodityId() != null){
                    ZipCommodity zipCommodity = commodityManager.findCommodityById2(zipOrderFormResponse.getCommodityId());
                    zipOrderFormResponse.setCommodityStatus(zipCommodity.getCommodityStatus());
                    zipOrderFormResponse.setCommodityType(zipCommodity.getStatus());
                }else{
                    zipOrderFormResponse.setCommodityStatus(2);
                    zipOrderFormResponse.setCommodityType(1);
                }
                if(zipOrderFormResponse.getAddressId()!=null&&StringUtils.isBlank(zipOrderFormResponse.getAddress())){
                    ZipAddressExample example = new ZipAddressExample();
                    example.or().andIdEqualTo(zipOrderFormResponse.getAddressId());
                    List<ZipAddress> list1 = zipAddressDao.selectByExample(example);
                    if(list1.size()>0){
                        zipOrderFormResponse.setAddress(list1.get(0).getRegion()+" "+list1.get(0).getDetailAddress());
                    }
                }
            }
        }
        pageResult.setSuccess(true);
        pageResult.setTotal(total);
        pageResult.setData(list);
        return ResultResponse.success(pageResult);
    }

    @Override
    @Transactional(readOnly = false)
    public ResultResponse addOrUpdate(ZipOrderFormRequest zipOrderFormRequest) {
        if(null==zipOrderFormRequest){
            return ResultResponse.error("500","参数为空");
        }
        if(null==zipOrderFormRequest.getOrderDate()){
            return ResultResponse.error("500","订单日期为空");
        }
        if(StringUtils.isEmpty(zipOrderFormRequest.getOpenId())){
            return ResultResponse.error("500","客户id为空");
        }
        if(StringUtils.isBlank(zipOrderFormRequest.getOrderNumber())){
            return ResultResponse.error("500","订单编号为空");
        }
        if(StringUtils.isBlank(zipOrderFormRequest.getTrackingNumber())){
            return ResultResponse.error("500","快递单号为空");
        }
        ZipWechatUser zipWechatUser = clientManager.findWeChatUserByOpenId(zipOrderFormRequest.getOpenId());
        if(zipWechatUser == null){
            return ResultResponse.error("500","用户不存在");
        }
        int num = 0;
        ZipOrderForm zipOrderForm = new ZipOrderForm();
        TransferUtil.transfer(zipOrderFormRequest,zipOrderForm);
        //订单编号时间戳加随机数
        if(StringUtils.isEmpty(zipOrderForm.getOrderNumber())){
            zipOrderForm.setOrderNumber(OrderRandomUtil.orderRandom());
        }
        zipOrderForm.setWechatUserId(zipWechatUser.getId());
        if(null!=zipOrderFormRequest.getAddressId()){
            ZipAddressExample example = new ZipAddressExample();
            example.or().andIdEqualTo(zipOrderForm.getAddressId());
            List<ZipAddress> addressList = zipAddressDao.selectByExample(example);
            if(addressList.size()>0){
                zipOrderForm.setAddress(addressList.get(0).getDetailAddress());
            }
        }
        if(null!=zipOrderFormRequest.getCommodityId()){
            ZipCommodityExample example1 = new ZipCommodityExample();
            example1.or().andIdEqualTo(zipOrderForm.getCommodityId());
            List<ZipCommodity> commodityList = zipCommodityDao.selectByExample(example1);
            if(commodityList.size()>0){
                zipOrderForm.setCommodityPicture(commodityList.get(0).getPicture());
            }
        }

        try {
            zipOrderForm.setOrderDate(DateUtils.formatStringToDate(zipOrderFormRequest.getOrderDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(zipOrderForm.getStatus()==2){//已经付款
            zipOrderForm.setPayTime(new Date());
        }
        if(zipOrderForm.getStatus()==3){//已经发货
            zipOrderForm.setSentTime(new Date());
        }
        if(null == zipOrderFormRequest.getId()){//新增
            num = zipOrderFormDao.insertSelective(zipOrderForm);
        }else {//修改
            if(zipOrderForm.getExpressType()==1){
                zipOrderForm.setExpress(0d);
            }
            num = zipOrderFormDao.updateByPrimaryKeySelective(zipOrderForm);
        }
        if(num>0){
            return ResultResponse.success(zipOrderForm.getOrderNumber());
        }
        return ResultResponse.error("500","操作失败");
    }

    @Override
    @Transactional(readOnly = false)
    public ResultResponse submitOrder(ZipOrderFormRequest zipOrderFormRequest) {
        if(null==zipOrderFormRequest){
            return ResultResponse.error("500","参数为空");
        }
        if(zipOrderFormRequest.getTotalAmount()== null){
            return ResultResponse.error("500","金额不能为空");
        }
        if(StringUtils.isEmpty(zipOrderFormRequest.getOpenId())){
            return ResultResponse.error("500","微信用户id不能为空");
        }
        if(zipOrderFormRequest.getAddressId() == null){
            return ResultResponse.error("500","地址不能为空");
        }
        if(zipOrderFormRequest.getNumber() == null || zipOrderFormRequest.getNumber() == 0){
            return ResultResponse.error("500","订单数量不能为空");
        }
        //获取微信id
        ZipWechatUser zipWechatUser = clientManager.findWeChatUserByOpenId(zipOrderFormRequest.getOpenId());
        if(zipWechatUser == null){
            return ResultResponse.error("500","用户不存在");
        }
        //获取商品信息
        ZipCommodityResponse response = null;
        if(zipOrderFormRequest.getCommodityId() != null){
            ResultResponse resultResponse = commodityManager.findCommodityById(zipOrderFormRequest.getCommodityId());
            response = (ZipCommodityResponse)resultResponse.getData();

        }
        ZipOrderForm zipOrderForm = new ZipOrderForm();
        TransferUtil.transfer(zipOrderFormRequest,zipOrderForm);
        zipOrderForm.setWechatUserId(zipWechatUser.getId());
        zipOrderForm.setOrderDate(new Date());
        if(response != null){
            zipOrderForm.setTextureId(response.getTextureId());
            zipOrderForm.setTechnology(response.getTechnology());
            zipOrderForm.setPrice(response.getPrice().doubleValue());
            zipOrderForm.setCommodityPicture(response.getPicture());
            zipOrderForm.setCommodityName(response.getCommodityName());
        }else{
            //获取材质信息
            ResultResponse resultResponse = textureManager.getInfo(response.getTextureId());
            ZipTextureResponse zipTextureResponse = (ZipTextureResponse)resultResponse.getData();
            zipOrderForm.setCommodityName(zipTextureResponse.getTextureName()+ "彩印");
        }
        //订单编号时间戳加随机数
        if(StringUtils.isEmpty(zipOrderForm.getOrderNumber())){
            zipOrderForm.setOrderNumber(OrderRandomUtil.orderRandom());
        }
        //默认包邮
        zipOrderForm.setExpressType(1);
        zipOrderForm.setStatus(1);
        zipOrderFormDao.insertSelective(zipOrderForm);
        ZipOrderFormResponse zipOrderFormResponse = new ZipOrderFormResponse();
        zipOrderFormResponse.setCommodityName(zipOrderForm.getCommodityName());
        zipOrderFormResponse.setPrice(zipOrderForm.getTotalAmount());
        zipOrderFormResponse.setOrderNumber(zipOrderForm.getOrderNumber());
        return ResultResponse.success(zipOrderFormResponse);
    }

    @Override
    public ResultResponse getInfo(Integer id) {
        if(null==id){
            return ResultResponse.error("500","参数为空");
        }
        ZipOrderFormExample zipOrderFormExample = new ZipOrderFormExample();
        zipOrderFormExample.or().andIdEqualTo(id);
        List<ZipOrderForm> list = zipOrderFormDao.selectByExample(zipOrderFormExample);
        ZipOrderFormResponse zipOrderFormResponse = new ZipOrderFormResponse();
        if(list.size()>0){
            ZipOrderForm zipOrderForm = list.get(0);
            TransferUtil.transfer(zipOrderForm,zipOrderFormResponse);
        }
        return ResultResponse.success(zipOrderFormResponse);
    }

    @Override
    public Integer getOrderSum(Integer userId, Integer status) {
        Integer sum = null;
        if(null==status){
            return sum;
        }
        ZipOrderFormExample example = new ZipOrderFormExample();
        example.or().andStatusEqualTo(status).andWechatUserIdEqualTo(userId);
        return zipOrderFormDao.countByExample(example);
    }

    @Override
    public ResultResponse getOrderSumByOpenId(String openId) {
        if(StringUtils.isEmpty(openId)){
            return ResultResponse.error("500","参数错误");
        }
        ZipWechatUser zipWechatUser = clientManager.findWeChatUserByOpenId(openId);
        if(zipWechatUser == null){
            return ResultResponse.error("500","用户为空");
        }
        OrderNum orderNum = new OrderNum();
        Integer num = this.getOrderSum(zipWechatUser.getId(), OrderStatus.TOPAY.getStatus());
        orderNum.setUnpaid(num);
        num = this.getOrderSum(zipWechatUser.getId(), OrderStatus.TOPAID.getStatus());
        orderNum.setToSend(num);
        num = this.getOrderSum(zipWechatUser.getId(), OrderStatus.TORECEIVE.getStatus());
        orderNum.setToReceive(num);
        return ResultResponse.success(orderNum);
    }

    @Override
    @Transactional(readOnly = false)
    public ResultResponse operationOrder(Integer orderId, Integer status) {
        if(orderId == null || status == null){
            return ResultResponse.error("500","参数错误");
        }
        ZipOrderFormExample example = new ZipOrderFormExample();
        example.or().andIdEqualTo(orderId);
        List<ZipOrderForm> list = zipOrderFormDao.selectByExample(example);
        if(list.size()<=0){
            return ResultResponse.error("500","没有此订单");
        }
        ZipOrderForm zipOrderForm = new ZipOrderForm();
        zipOrderForm.setStatus(status);
        zipOrderForm.setId(orderId);
        return ResultResponse.success(zipOrderFormDao.updateByPrimaryKeySelective(zipOrderForm));
    }

    @Override
    public Integer countNumByCommodityId(Integer commodityId) {
        if(commodityId == null){
            return 0;
        }
        ZipOrderFormExample example = new ZipOrderFormExample();
        example.or().andCommodityIdEqualTo(commodityId).andStatusNotEqualTo(OrderStatus.TOPAY.getStatus());
        return zipOrderFormDao.countByExample(example);
    }

    @Override
    public ResultResponse getInfoByOrderNo(String orderNo) {
        if(StringUtils.isEmpty(orderNo)){
            return ResultResponse.error("500","参数错误");
        }
        ZipOrderFormExample example = new ZipOrderFormExample();
        example.or().andOrderNumberEqualTo(orderNo);
        List<ZipOrderForm> zipOrderFormList=zipOrderFormDao.selectByExample(example);
        if (!CollectionUtils.isEmpty(zipOrderFormList)) {
            return ResultResponse.success(zipOrderFormList.get(0));
        }else{
            return ResultResponse.error("500","未找到相关订单信息");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public Integer updateOrderForm(ZipOrderForm zipOrderForm) {
        if(zipOrderForm == null){
            return 0;
        }
        return zipOrderFormDao.updateByPrimaryKeySelective(zipOrderForm);
    }
}
