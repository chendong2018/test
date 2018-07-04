package com.jianwu.manager.impl;

import com.jianwu.dao.ZipTextureDao;
import com.jianwu.domain.ZipTexture;
import com.jianwu.domain.enums.ZippoStatus;
import com.jianwu.domain.request.ZipTextureRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.PageResult;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.domain.result.ZipTextureResponse;
import com.jianwu.example.ZipTextureExample;
import com.jianwu.manager.TextureManager;
import com.jianwu.service.QiNiuCloudService;
import com.jianwu.utils.TransferUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:lijin
 * @Date:17:05 2018/6/1
 * @Remark:
 */
@Service
@Transactional(readOnly = true)
public class TextureManagerImpl implements TextureManager {

    @Autowired
    ZipTextureDao zipTextureDao;

    @Autowired
    QiNiuCloudService qiNiuCloudService;

    @Override
    public ResultResponse list(Page page, String textureOrder, String textureName, Integer status) {
        PageResult pageResult = new PageResult();
        Integer start = page.getStart();
        Integer end = page.getPageSize();
        List<ZipTextureResponse> list = new ArrayList<>();
        if(null==status){
            status =2;
        }
        int total = zipTextureDao.queryLikeCount(textureOrder,textureName,status);
        if(total>0){
            list = zipTextureDao.queryLike(start,end,textureOrder,textureName,status);
        }
        pageResult.setSuccess(true);
        pageResult.setTotal(total);
        pageResult.setData(list);
        return ResultResponse.success(pageResult);
    }

    @Override
    @Transactional(readOnly = false)
    public ResultResponse addOrUpdate(ZipTextureRequest zipTextureRequest) {
        if(null==zipTextureRequest){
            return ResultResponse.error("500","参数为空");
        }

        if(null==zipTextureRequest.getTextureName()){
            return ResultResponse.error("500","材质名称为空");
        }
        if(null==zipTextureRequest.getTextureOrder()){
            return ResultResponse.error("500","材质编号为空");
        }
        if(null==zipTextureRequest.getStatus()){
            return ResultResponse.error("500","材质状态为空");
        }

        int num = 0;
        ZipTexture zipTexture = new ZipTexture();
        if(null == zipTextureRequest.getId()){//新增
            TransferUtil.transfer(zipTextureRequest,zipTexture);
            num = zipTextureDao.insertSelective(zipTexture);
        }else {//修改
            ZipTextureExample example = new ZipTextureExample();
            example.or().andIdEqualTo(zipTextureRequest.getId());
            List<ZipTexture> list = zipTextureDao.selectByExample(example);
            if(list.get(0).getStatus()==0&&zipTextureRequest.getStatus()==0){
                return ResultResponse.error("500","已上架材质，不能修改");
            }
            TransferUtil.transfer(zipTextureRequest,zipTexture);
            num = zipTextureDao.updateByPrimaryKeySelective(zipTexture);
        }
        if(num>0){
            return ResultResponse.success("操作成功");
        }
        return ResultResponse.error("500","操作失败");
    }

    @Override
    @Transactional(readOnly = false)
    public ResultResponse delete(Integer id) {
        if(null!=id){
            ZipTexture zipTexture = new ZipTexture();
            zipTexture.setId(id);
            zipTexture.setStatus(ZippoStatus.DELETE.getStatus());
            return ResultResponse.success(zipTextureDao.updateByPrimaryKeySelective(zipTexture));
        }
        return ResultResponse.error("500","参数为空");
    }

    @Override
    public ResultResponse getInfo(Integer id) {
        if(null==id){
            return ResultResponse.error("500","参数为空");
        }
        ZipTextureExample zipTextureExample = new ZipTextureExample();
        zipTextureExample.or().andIdEqualTo(id);
        List<ZipTexture> list = zipTextureDao.selectByExample(zipTextureExample);
        ZipTextureResponse zipTextureResponse = new ZipTextureResponse();
        if(list.size()>0){
            ZipTexture zipTexture = list.get(0);
            TransferUtil.transfer(zipTexture,zipTextureResponse);
        }
        return ResultResponse.success(zipTextureResponse);
    }

    @Override
    public ResultResponse findTextureList() {
        ZipTextureExample example = new ZipTextureExample();
        //0上架 1下架
        example.or().andStatusEqualTo(0);
        List<ZipTexture> list = zipTextureDao.selectByExample(example);
        List<ZipTextureResponse> responses = new ArrayList<>();
        if(CollectionUtils.isEmpty(list)){
            return ResultResponse.success(responses);
        }
        responses = TransferUtil.transferList(list,ZipTextureResponse.class);
        return ResultResponse.success(responses);
    }
}
