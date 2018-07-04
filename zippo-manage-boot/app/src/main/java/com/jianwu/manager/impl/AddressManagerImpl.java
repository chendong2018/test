package com.jianwu.manager.impl;


import com.jianwu.dao.ZipAddressDao;
import com.jianwu.domain.ZipAddress;
import com.jianwu.domain.ZipWechatUser;

import com.jianwu.domain.enums.AddressStatus;
import com.jianwu.domain.request.ZipAddressRequest;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.domain.result.ZipAddressResponse;
import com.jianwu.example.ZipAddressExample;
import com.jianwu.manager.AddressManager;
import com.jianwu.manager.ClientManager;

import com.jianwu.utils.TransferUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class AddressManagerImpl implements AddressManager {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ZipAddressDao zipAddressDao;

    @Autowired
    ClientManager clientManager;

    @Override
    public ResultResponse findAddressByWeChatUserId(String weChatId) {
        if(weChatId == null){
            return ResultResponse.error("500","参数错误");
        }
        ZipWechatUser zipWechatUser = clientManager.findWeChatUserByOpenId(weChatId);
        if(zipWechatUser == null){
            return ResultResponse.error("500","用户不存在");
        }
        ZipAddressExample example = new ZipAddressExample();
        example.or().andUserIdEqualTo(zipWechatUser.getId()).andStatusNotEqualTo(AddressStatus.DELETE.getStatus());
        example.setOrderByClause("status desc");
        List<ZipAddress> list = zipAddressDao.selectByExample(example);
        List<ZipAddressResponse> responseList = new ArrayList<>();
        if(CollectionUtils.isEmpty(list)){
            return ResultResponse.success(responseList);
        }
        for(ZipAddress zipAddress :list){
            ZipAddressResponse response = new ZipAddressResponse();
            TransferUtil.transfer(zipAddress,response);
            response.setAddress("收货地址:"+zipAddress.getRegion()+zipAddress.getDetailAddress());
            responseList.add(response);
        }
        return ResultResponse.success(responseList);
    }

    @Override
    @Transactional(readOnly = false)
    public ResultResponse addAddress(ZipAddressRequest zipAddressRequest) {
        if(zipAddressRequest == null){
            return ResultResponse.error("500","参数为空");
        }
        if(StringUtils.isEmpty(zipAddressRequest.getConsignee())){
            return ResultResponse.error("500","收货人为空");
        }
        if(StringUtils.isEmpty(zipAddressRequest.getMobilePhone() ) ){
            return ResultResponse.error("500","手机号码为空");
        }
        if(StringUtils.isEmpty(zipAddressRequest.getRegion() ) ){
            return ResultResponse.error("500","地区为空");
        }
        if(StringUtils.isEmpty(zipAddressRequest.getDetailAddress() ) ){
            return ResultResponse.error("500","详细地址为空");
        }
        if(StringUtils.isEmpty(zipAddressRequest.getOpenId())){
            return ResultResponse.error("500","用户信息错误");
        }
        ZipWechatUser zipWechatUser = clientManager.findWeChatUserByOpenId(zipAddressRequest.getOpenId());
        if(zipWechatUser == null){
            logger.info("------------------用户不存在");
            return ResultResponse.error("500","用户不存在");
        }
        //获取当前用户是否存在默认地址
        ZipAddressExample example = new ZipAddressExample();
        example.or().andUserIdEqualTo(zipWechatUser.getId());
        List<ZipAddress> list = zipAddressDao.selectByExample(example);
        if(!CollectionUtils.isEmpty(list)){
            ZipAddress zipAddress = new ZipAddress();
            TransferUtil.transfer(zipAddressRequest,zipAddress);
            zipAddress.setStatus(AddressStatus.DEFAULT.getStatus());
            zipAddress.setId(list.get(0).getId());
            zipAddressDao.updateByPrimaryKeySelective(zipAddress);
            return ResultResponse.success("新增地址成功");
        }
        ZipAddress zipAddress = new ZipAddress();
        TransferUtil.transfer(zipAddressRequest,zipAddress);
        zipAddress.setUserId(zipWechatUser.getId());
        zipAddressDao.insertSelective(zipAddress);
        return ResultResponse.success("新增地址成功");
    }

    @Override
    @Transactional(readOnly = false)
    public ResultResponse updateAddress(ZipAddressRequest zipAddressRequest) {
        if(zipAddressRequest == null){
            return ResultResponse.error("500","参数为空");
        }
        if(StringUtils.isEmpty(zipAddressRequest.getConsignee())){
            return ResultResponse.error("500","收货人为空");
        }
        if(StringUtils.isEmpty(zipAddressRequest.getMobilePhone() ) ){
            return ResultResponse.error("500","手机号码为空");
        }
        if(StringUtils.isEmpty(zipAddressRequest.getRegion() ) ){
            return ResultResponse.error("500","地区为空");
        }
        if(StringUtils.isEmpty(zipAddressRequest.getDetailAddress() ) ){
            return ResultResponse.error("500","详细地址为空");
        }
        if(zipAddressRequest.getId() == null  ){
            return ResultResponse.error("500","用户信息错误");
        }
        ZipAddress zipAddress = zipAddressDao.selectByPrimaryKey(zipAddressRequest.getId());
        if(zipAddress == null|| zipAddress.getStatus().equals(AddressStatus.DELETE.getStatus())){
            return ResultResponse.error("500","地址不存在或已经删除");
        }

//        if(Objects.equals(zipAddressRequest.getStatus(),AddressStatus.DEFAULT.getStatus())){
//            //获取当前用户是否存在默认地址
//            ZipAddressExample example = new ZipAddressExample();
//            example.or().andStatusEqualTo(AddressStatus.DEFAULT.getStatus()).andUserIdEqualTo(zipAddress.getUserId());
//            List<ZipAddress> list = zipAddressDao.selectByExample(example);
//            if(!CollectionUtils.isEmpty(list)){
//                for(ZipAddress zipAddress2 : list){
//                    if(Objects.equals(zipAddress2.getId(), zipAddressRequest.getId())){
//                        continue;
//                    }
//                    zipAddress2.setStatus(AddressStatus.NORMAL.getStatus());
//                    zipAddressDao.updateByPrimaryKeySelective(zipAddress2);
//                }
//            }
//        }
        TransferUtil.transfer(zipAddressRequest,zipAddress);
        zipAddressDao.updateByPrimaryKeySelective(zipAddress);
        return ResultResponse.success("修改地址成功");
    }

    @Override
    @Transactional(readOnly = false)
    public ResultResponse deleteAddress(Integer id, Integer status) {
        if(id == null){
            return ResultResponse.error("500","参数为空");
        }
        if(status == null){
            return ResultResponse.error("500","参数为空");
        }
        ZipAddress zipAddress = zipAddressDao.selectByPrimaryKey(id);
        if(zipAddress == null|| zipAddress.getStatus().equals(AddressStatus.DELETE.getStatus())){
            return ResultResponse.error("500","地址不存在或已经删除");
        }
        if(Objects.equals(status,AddressStatus.DEFAULT.getStatus())){
            //获取当前用户是否存在默认地址
            ZipAddressExample example = new ZipAddressExample();
            example.or().andStatusEqualTo(AddressStatus.DEFAULT.getStatus()).andUserIdEqualTo(zipAddress.getUserId());
            List<ZipAddress> list = zipAddressDao.selectByExample(example);
            if(!CollectionUtils.isEmpty(list)){
                for(ZipAddress zipAddress2 : list){
                    if(Objects.equals(zipAddress2.getId(), id)){
                        continue;
                    }
                    zipAddress2.setStatus(AddressStatus.NORMAL.getStatus());
                    zipAddressDao.updateByPrimaryKeySelective(zipAddress2);
                }
            }
        }
        zipAddress.setStatus(status);
        zipAddressDao.updateByPrimaryKeySelective(zipAddress);
        return ResultResponse.success("操作成功");
    }
}
