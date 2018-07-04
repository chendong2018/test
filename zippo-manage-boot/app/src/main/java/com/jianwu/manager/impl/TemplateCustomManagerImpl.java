package com.jianwu.manager.impl;/**
 * User:chendong
 * Date: 2018/6/6
 * Time: ${Time}
 * Remark:
 */

import com.jianwu.dao.ZipTemplateCustomDao;
import com.jianwu.domain.ZipTemplate;
import com.jianwu.domain.ZipTemplateCustom;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.domain.result.ZipTextureResponse;
import com.jianwu.example.ZipTemplateCustomExample;
import com.jianwu.manager.TemplateCustomManager;
import com.jianwu.manager.TemplateManager;
import com.jianwu.manager.TextureManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 自定义模板临时数据
 *
 * @Author chendong
 * @Create 2018/6/6
 * @Time 18:50
 **/
@Service
@Transactional(readOnly = true)
public class TemplateCustomManagerImpl implements TemplateCustomManager  {

    @Autowired
    ZipTemplateCustomDao zipTemplateCustomDao;

    @Autowired
    TextureManager textureManager;

    @Override
    public ZipTemplateCustom findTemplateCustomList(String openId, String templateNum,
                                                          Integer templateDetailsId, Integer templateId) {
        if(StringUtils.isEmpty(openId) || templateNum == null || templateDetailsId == null || templateId == null){
            return null;
        }
        ZipTemplateCustomExample example = new ZipTemplateCustomExample();
        example.or().andOpenIdEqualTo(openId).andTemplateDetailsIdEqualTo(templateDetailsId).andTemplateIdEqualTo(templateId).andTemplateNumEqualTo(templateNum);
        List<ZipTemplateCustom> list = zipTemplateCustomDao.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

    @Override
    @Transactional(readOnly = false)
    public Integer updateTemplateCusstom(ZipTemplateCustom zipTemplateCustom) {
        if(zipTemplateCustom == null){
            return 0;
        }
        return  zipTemplateCustomDao.updateByPrimaryKeySelective(zipTemplateCustom);
    }

    @Override
    @Transactional(readOnly = false)
    public Double insertTemplateCusstom(String openId, Integer templateId, Integer templateDetailsId, String imgUrl, String content, String templateNum,Integer textureId) {
        if(StringUtils.isEmpty(openId) || StringUtils.isEmpty(templateNum)  ){
            return 0.0;
        }
        //返回材质的价格
        ZipTextureResponse zipTextureResponse = new ZipTextureResponse();
        if(textureId != null){
            //新增材质临时图片
            ResultResponse response = textureManager.getInfo(textureId);
            zipTextureResponse = (ZipTextureResponse)response.getData();
            ZipTemplateCustom zipTemplateCustomTexture  = findTemplateCusstomTexture(openId,templateNum);
            if(zipTemplateCustomTexture != null && !zipTemplateCustomTexture.getTextureId().equals(textureId)){
                zipTemplateCustomTexture.setTextureId(textureId);
                return zipTextureResponse.getPrice();
            }
        }
        ZipTemplateCustom zipTemplateCustom = new ZipTemplateCustom();
        zipTemplateCustom.setOpenId(openId);
        zipTemplateCustom.setTemplateId(templateId);
        zipTemplateCustom.setTemplateDetailsId(templateDetailsId);
        zipTemplateCustom.setContent(content);
        zipTemplateCustom.setImgUrl(imgUrl);
        zipTemplateCustom.setTemplateNum(templateNum);
        zipTemplateCustom.setTextureId(textureId);
        zipTemplateCustomDao.insertSelective(zipTemplateCustom);
        return zipTextureResponse.getPrice();
    }

    @Override
    public ZipTemplateCustom findTemplateCusstomTexture(String openId, String templateNum) {
        if(StringUtils.isEmpty(openId) || StringUtils.isEmpty(templateNum) ){
            return null;
        }
        ZipTemplateCustomExample example = new ZipTemplateCustomExample();
        example.or().andOpenIdEqualTo(openId).andTemplateNumEqualTo(templateNum).andTextureIdIsNotNull();
        List<ZipTemplateCustom> list = zipTemplateCustomDao.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ZipTemplateCustom> findAllTemplateList(String openId, String templateNum, Integer templateId, Integer templateDetailsId) {
        if(StringUtils.isEmpty(openId) || StringUtils.isEmpty(templateNum) || templateId == null  ){
            return null;
        }
        ZipTemplateCustomExample example = new ZipTemplateCustomExample();
        if(templateDetailsId != null) {
            example.or().andOpenIdEqualTo(openId).andTemplateNumEqualTo(templateNum).andTemplateIdEqualTo(templateId).andTemplateDetailsIdNotEqualTo(templateDetailsId);
        }else{
            example.or().andOpenIdEqualTo(openId).andTemplateNumEqualTo(templateNum).andTemplateIdEqualTo(templateId);
        }
        List<ZipTemplateCustom> list = zipTemplateCustomDao.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list;
    }

    @Override
    public List<ZipTemplateCustom> findCustomList(String openId, String templateNum, Integer templateId) {
        if(StringUtils.isEmpty(openId) || StringUtils.isEmpty(templateNum) || templateId == null  ){
            return null;
        }
        ZipTemplateCustomExample example = new ZipTemplateCustomExample();
        example.or().andOpenIdEqualTo(openId).andTemplateNumEqualTo(templateNum).andTemplateIdEqualTo(templateId).andTextureIdIsNull();
        List<ZipTemplateCustom> list = zipTemplateCustomDao.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list;
    }
}
