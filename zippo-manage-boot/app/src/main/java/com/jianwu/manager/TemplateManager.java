package com.jianwu.manager;

import com.jianwu.domain.request.ImageRequest;
import com.jianwu.domain.request.ZipTemplateRequest;
import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName: TemplateManager
 * @Description: 模块业务逻辑接口
 * @Author: chenDong
 * @Date: 2018/6/1 14:34
 * @Remark:
 */
public interface TemplateManager {

    /**
     * @ClassName: TemplateManager
     * @Description: 获取列表 模板 分页
     * @Author: chenDong
     * @Date: 2018/6/1 15:05
     * @Remark:
     */
    ResultResponse list(Page page, String templateOrder, String templateName, Integer module, Integer status);

    /**
     * @param zipTemplateRequest
     * @return
     */
    ResultResponse add(ZipTemplateRequest zipTemplateRequest);

    /**
     * @ClassName: TemplateManager
     * @Description: 修改模板
     * @Author: chenDong
     * @Date: 2018/6/1 15:10
     * @Remark:
     */
    ResultResponse update(ZipTemplateRequest zipTemplateRequest);

    /**
     * @ClassName: TemplateManager
     * @Description: 删除模板
     * @Author: chenDong
     * @Date: 2018/6/1 15:10
     * @Remark:
     */
    ResultResponse delete(Integer id);

    /**
     * @ClassName: TemplateManager
     * @Description: 修改模板
     * @Author: chenDong
     * @Date: 2018/6/1 15:11
     * @Remark:
     */
    ResultResponse editTemplate(Integer id, Integer status);


    ResultResponse findTemplate(Integer id);

    /**
     * @ClassName: TemplateManager
     * @Description: 小程序获取模板列表
     * @Author: chenDong
     * @Date: 2018/6/4 9:27
     * @Remark:
     */
    ResultResponse findTemplateList();

    /**
     * @ClassName: TemplateManager
     * @Description: 文字转图片
     * @Author: chenDong
     * @Date: 2018/6/4 10:45
     * @Remark:
     */
    void wordToImg(ImageRequest imageRequest, HttpServletRequest request, HttpServletResponse response);

    /**
     * @ClassName: TemplateManager
     * @Description: 图片合成
     * @Author: chenDong
     * @Date: 2018/6/4 11:17
     * @Remark:
     */
    void imgToImg(String openId,String templateNum, Integer templateDetailId, HttpServletResponse response, HttpServletRequest request,String url);

    /**
     * @ClassName: TemplateManager
     * @Description: 图片合成
     * @Author: chenDong
     * @Date: 2018/6/12 11:02
     * @Remark:
     */
    void getImg(String key,HttpServletResponse response);

    /**
     * @ClassName: TemplateManager
     * @Description:
     * @Author: chenDong
     * @Date: 2018/6/12 17:54
     * @Remark:
     */
    ResultResponse imgToUpload(String openId,String templateNum, Integer templateDetailId, HttpServletResponse response, HttpServletRequest request);
}
