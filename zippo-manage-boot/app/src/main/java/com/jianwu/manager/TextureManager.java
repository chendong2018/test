package com.jianwu.manager;


import com.jianwu.domain.request.ZipTextureRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;

/**
 * @Author:lijin
 * @Date:17:05 2018/6/1
 * @Remark:
 */
public interface TextureManager {

    /*
    分页模糊查询材质列表
     */
    ResultResponse list(Page page, String textureOrder, String textureName, Integer status);

    /*
    新增修改
     */
    ResultResponse addOrUpdate(ZipTextureRequest zipTextureRequest);

    /*
    删除
     */
    ResultResponse delete(Integer id);

    /*
    获取单条详情
     */
    ResultResponse getInfo(Integer id);


    /**
     * @ClassName: TextureManager
     * @Description: 获取材质列表
     * @Author: chenDong
     * @Date: 2018/6/4 10:25
     * @Remark:
     */
    ResultResponse findTextureList();
}
