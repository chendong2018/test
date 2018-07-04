package com.jianwu.service.impl;


import com.jianwu.domain.request.ZipTextureRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.manager.TextureManager;
import com.jianwu.service.TextureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:lijin
 * @Date:17:04 2018/6/1
 * @Remark:
 */
@Service
public class TextureServiceImpl implements TextureService {

    @Autowired
    TextureManager textureManager;

    @Override
    public ResultResponse list(Page page, String textureOrder, String textureName, Integer status) {
        return textureManager.list(page,textureOrder,textureName,status);
    }

    @Override
    public ResultResponse addOrUpdate(ZipTextureRequest zipTextureRequest) {
        return textureManager.addOrUpdate(zipTextureRequest);
    }

    @Override
    public ResultResponse delete(Integer id) {
        return textureManager.delete(id);
    }

    @Override
    public ResultResponse getInfo(Integer id) {
        return textureManager.getInfo(id);
    }

}
