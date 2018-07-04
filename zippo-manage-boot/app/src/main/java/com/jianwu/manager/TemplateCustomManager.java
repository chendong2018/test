package com.jianwu.manager;

import com.jianwu.domain.ZipTemplateCustom;
import com.jianwu.domain.request.ImageRequest;
import com.jianwu.domain.request.ZipTemplateRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: TemplateManager
 * @Description: 模板自定义临时数据接口
 * @Author: chenDong
 * @Date: 2018/6/1 14:34
 * @Remark:
 */
public interface TemplateCustomManager {

    /**
     * @ClassName: TemplateCustomManager
     * @Description: 更具 微信号id 位置id 唯一标识获取当前模板图片临时信息
     * @Author: chenDong
     * @Date: 2018/6/6 18:53
     * @Remark:
     */
     ZipTemplateCustom findTemplateCustomList(String openId, String templateNum,
                                                   Integer templateDetailsId,Integer templateId);

    /**
     * @ClassName: TemplateCustomManager
     * @Description: 更新覆盖之前的临时图片或文字信息
     * @Author: chenDong
     * @Date: 2018/6/7 9:52
     * @Remark:
     */
    Integer updateTemplateCusstom(ZipTemplateCustom zipTemplateCustom);

    /**
     * @ClassName: TemplateCustomManager
     * @Description: 新增自定义临时数据
     * @Author: chenDong
     * @Date: 2018/6/7 10:36
     * @Remark:
     */
    Double insertTemplateCusstom(String openId,Integer templateId,Integer templateDetailsId,String imgUrl,String content,String templateNum, Integer textureId);

    /**
     * @ClassName: TemplateCustomManager
     * @Description:  获取自定义材料信息
     * @Author: chenDong
     * @Date: 2018/6/7 10:55
     * @Remark:
     */
    ZipTemplateCustom findTemplateCusstomTexture(String openId, String templateNum);

    /**
     * @ClassName: TemplateCustomManager
     * @Description: 获取所有的自定义信息
     * @Author: chenDong
     * @Date: 2018/6/7 11:04
     * @Remark:
     */
    List<ZipTemplateCustom> findAllTemplateList(String openId, String templateNum,Integer templateId,Integer templateDetailsId);

    /**
     * @ClassName: TemplateCustomManager
     * @Description: 获取所有模板信息
     * @Author: chenDong
     * @Date: 2018/6/13 15:34
     * @Remark:
     */
    List<ZipTemplateCustom>  findCustomList(String openId, String templateNum,Integer templateId);

}
