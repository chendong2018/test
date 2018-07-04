package com.jianwu.manager.impl;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.jianwu.dao.ZipTemplateDao;
import com.jianwu.dao.ZipTemplateDetailsDao;
import com.jianwu.domain.ZipTemplate;
import com.jianwu.domain.ZipTemplateCustom;
import com.jianwu.domain.ZipTemplateDetails;
import com.jianwu.domain.enums.TemplateStatus;
import com.jianwu.domain.request.ImageRequest;
import com.jianwu.domain.request.ZipTemplateDetailsRequest;
import com.jianwu.domain.request.ZipTemplateRequest;
import com.jianwu.domain.result.*;
import com.jianwu.example.ZipTemplateDetailsExample;
import com.jianwu.example.ZipTemplateExample;
import com.jianwu.manager.QiNiuManager;
import com.jianwu.manager.TemplateCustomManager;
import com.jianwu.manager.TemplateManager;
import com.jianwu.manager.TextureManager;
import com.jianwu.utils.*;
import com.sun.javafx.iio.common.ImageTools;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.List;

import static java.lang.Thread.sleep;

@Service
@Transactional(readOnly = true)
public class TemplateManagerImpl implements TemplateManager {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    ZipTemplateDao zipTemplateDao;

    @Autowired
    ZipTemplateDetailsDao zipTemplateDetailsDao;

    @Autowired
    TemplateCustomManager templateCustomManager;

    @Autowired
    TextureManager textureManager;

    @Autowired
    QiNiuManager qiNiuManager;

    @Value("${imageTempUrl}")
    String realPath ;

    @Value("${tUrl}")
    String tUrl ;

    @Value("${qiniu.token.url}")
    String qnUrl;

    @Override
    public ResultResponse list(Page page, String templateOrder, String templateName, Integer module, Integer status) {
        //获取模板数量
        Integer total = zipTemplateDao.countTemplate(templateOrder,templateName,module,status);
        PageResult pageResult = null;
        List<ZipTemplateResponse> list = new ArrayList<>();
        if(total == null || total.equals(0)){
            pageResult = new PageResult();
            pageResult.setTotal(0);
            pageResult.setSuccess(true);
            pageResult.setData(list);
            return ResultResponse.success(pageResult);
        }
        Integer start = (page.getPage() - 1) * page.getPageSize();
        Integer end = page.getPageSize();
        List<ZipTemplate> zipTemplates = zipTemplateDao.searchZipTempLateByPage(templateOrder,templateName,module,status,start,end);
        list = TransferUtil.transferList(zipTemplates,ZipTemplateResponse.class);
        pageResult = new PageResult();
        pageResult.setTotal(total);
        pageResult.setSuccess(true);
        pageResult.setData(list);
        return ResultResponse.success(pageResult);
    }

    @Override
    @Transactional(readOnly = false)
    public ResultResponse add(ZipTemplateRequest zipTemplateRequest) {
        if(zipTemplateRequest == null){
            return ResultResponse.error("500","参数错误");
        }
        if(zipTemplateRequest.getModule() == null){
            return ResultResponse.error("500","请选择模板面");
        }
        if(StringUtils.isEmpty(zipTemplateRequest.getTemplateOrder())){
            return ResultResponse.error("500","模板编号不为空");
        }
        if(StringUtils.isEmpty(zipTemplateRequest.getTemplateName())){
            return ResultResponse.error("500","模板名称不为空");
        }
        if(StringUtils.isEmpty(zipTemplateRequest.getPicture())){
            return ResultResponse.error("500","缩略图不为空");
        }
        if(CollectionUtils.isEmpty(zipTemplateRequest.getList())){
            return ResultResponse.error("500","模板详细内容不能为空");
        }
        ZipTemplate zipTemplate = new ZipTemplate();
        TransferUtil.transfer(zipTemplateRequest,zipTemplate);
        zipTemplateDao.insertSelective(zipTemplate);

        for(ZipTemplateDetailsRequest zipTemplateDetailsRequest : zipTemplateRequest.getList()){
            ZipTemplateDetails zipTemplateDetails = new ZipTemplateDetails();
            TransferUtil.transfer(zipTemplateDetailsRequest,zipTemplateDetails);
            zipTemplateDetails.setTemplateId(zipTemplate.getId());
            zipTemplateDetailsDao.insertSelective(zipTemplateDetails);
        }
        return ResultResponse.success("");
    }

    @Override
    @Transactional(readOnly = false)
    public ResultResponse update(ZipTemplateRequest zipTemplateRequest) {
        if(zipTemplateRequest == null){
            return ResultResponse.error("500","参数错误");
        }
        if(zipTemplateRequest.getModule() == null){
            return ResultResponse.error("500","请选择模板面");
        }
        if(StringUtils.isEmpty(zipTemplateRequest.getTemplateOrder())){
            return ResultResponse.error("500","模板编号不为空");
        }
        if(StringUtils.isEmpty(zipTemplateRequest.getTemplateName())){
            return ResultResponse.error("500","模板名称不为空");
        }
        if(StringUtils.isEmpty(zipTemplateRequest.getPicture())){
            return ResultResponse.error("500","缩略图不为空");
        }
        if(CollectionUtils.isEmpty(zipTemplateRequest.getList())){
            return ResultResponse.error("500","模板详细内容不能为空");
        }
        if(zipTemplateRequest.getId() == null){
            return ResultResponse.error("500","参数错误2");
        }
        //获取之前的模板信息
        ZipTemplate zipTemplate = zipTemplateDao.selectByPrimaryKey(zipTemplateRequest.getId());
        if(zipTemplate == null || Objects.equals(zipTemplate.getStatus(), TemplateStatus.DELETE.getStatus())){
            return ResultResponse.error("500","模板不存在");
        }
        // 模板上将当中不给修改
        if(Objects.equals(zipTemplate.getStatus(), TemplateStatus.PUTAWAY.getStatus())){
            return ResultResponse.error("500","模板上架中不能修改");
        }
        TransferUtil.transfer(zipTemplateRequest,zipTemplate);
        zipTemplateDao.updateByPrimaryKey(zipTemplate);
        //修改详细信息
        //删除之前的详细信息
        ZipTemplateDetailsExample zipTemplateDetailsExample = new ZipTemplateDetailsExample();
        zipTemplateDetailsExample.or().andTemplateIdEqualTo(zipTemplate.getId());
        zipTemplateDetailsDao.deleteByExample(zipTemplateDetailsExample);
        for(ZipTemplateDetailsRequest zipTemplateDetailsRequest : zipTemplateRequest.getList()){
            ZipTemplateDetails zipTemplateDetails = new ZipTemplateDetails();
            TransferUtil.transfer(zipTemplateDetailsRequest,zipTemplateDetails);
            zipTemplateDetails.setTemplateId(zipTemplate.getId());
            zipTemplateDetailsDao.insertSelective(zipTemplateDetails);
        }
        return ResultResponse.success("修改成功");
    }

    @Override
    @Transactional(readOnly = false)
    public ResultResponse delete(Integer id) {
        if(id == null){
            return ResultResponse.error("500","参数错误");
        }
        ZipTemplate zipTemplate = zipTemplateDao.selectByPrimaryKey(id);
        if(zipTemplate == null || Objects.equals(zipTemplate.getStatus(), TemplateStatus.DELETE.getStatus())){
            return ResultResponse.error("500","模板不存在");
        }
        zipTemplate.setStatus(TemplateStatus.DELETE.getStatus());
        zipTemplateDao.updateByPrimaryKey(zipTemplate);
        return ResultResponse.success("删除成功");
    }

    @Override
    @Transactional(readOnly = false)
    public ResultResponse editTemplate(Integer id, Integer status) {
        if(id == null){
            return ResultResponse.error("500","参数错误");
        }
        ZipTemplate zipTemplate = zipTemplateDao.selectByPrimaryKey(id);
        if(zipTemplate == null || Objects.equals(zipTemplate.getStatus(), TemplateStatus.DELETE.getStatus())){
            return ResultResponse.error("500","模板不存在");
        }
        zipTemplate.setStatus(status);
        zipTemplateDao.updateByPrimaryKey(zipTemplate);
        return ResultResponse.success("操作成功");
    }

    @Override
    public ResultResponse findTemplate(Integer id) {
        if(id == null){
            return ResultResponse.error("500","参数错误");
        }
        ZipTemplate zipTemplate = zipTemplateDao.selectByPrimaryKey(id);
        if(zipTemplate == null || Objects.equals(zipTemplate.getStatus(), TemplateStatus.DELETE.getStatus())){
            return ResultResponse.error("500","模板不存在");
        }
        ZipTemplateResponse zipTemplateResponse = new ZipTemplateResponse();
        TransferUtil.transfer(zipTemplate,zipTemplateResponse);
        ZipTemplateDetailsExample example = new ZipTemplateDetailsExample();
        example.or().andTemplateIdEqualTo(zipTemplate.getId());
        List<ZipTemplateDetails> list = zipTemplateDetailsDao.selectByExample(example);
        List<ZipTemplateDetailsResponse> zipTemplateDetailsResponses = new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)){
            zipTemplateDetailsResponses = TransferUtil.transferList(list,ZipTemplateDetailsResponse.class);
        }
        zipTemplateResponse.setList(zipTemplateDetailsResponses);
        return ResultResponse.success(zipTemplateResponse);
    }

    @Override
    public ResultResponse findTemplateList() {
        ZipTemplateExample zipTemplateExample = new ZipTemplateExample();
        zipTemplateExample.or().andStatusEqualTo(TemplateStatus.PUTAWAY.getStatus());
        List<ZipTemplate> list = zipTemplateDao.selectByExample(zipTemplateExample);
        if(CollectionUtils.isEmpty(list)){
            return ResultResponse.success(new ArrayList<>());
        }
        List<Integer> templateIds = Lists.transform(list, new Function<ZipTemplate, Integer>() {
            @Override
            public Integer apply(ZipTemplate zipTemplate) {
                return zipTemplate.getId();
            }
        });
        List<Integer> moduleList = Lists.transform(list, new Function<ZipTemplate, Integer>() {
            @Override
            public Integer apply(ZipTemplate zipTemplate) {
                return zipTemplate.getModule();
            }
        });
        ZipTemplateDetailsExample e = new ZipTemplateDetailsExample();
        e.or().andTemplateIdIn(templateIds);
        List<ZipTemplateDetails> templateDetails = zipTemplateDetailsDao.selectByExample(e);
        Map<Integer,List<ZipTemplateDetails>> map = null;
        if(!CollectionUtils.isEmpty(templateDetails)){
            Integer key = 0;
            map = new HashMap<>();
            for(ZipTemplateDetails zipTemplateDetails : templateDetails){
                key = zipTemplateDetails.getTemplateId();
                if(map.containsKey(key)){
                    continue;
                }
                List<ZipTemplateDetails> zipTemplateDetailsList = new ArrayList<>();
                for(ZipTemplateDetails zipTemplateDetails2 : templateDetails) {
                    if(zipTemplateDetails2.getTemplateId().equals(key)){

                        zipTemplateDetailsList.add(zipTemplateDetails2);
                    }
                }
                map.put(key,zipTemplateDetailsList);
            }
        }
        List<ZipTemplateResponse> responses = new ArrayList<>();
        for(ZipTemplate zipTemplate :list){
            ZipTemplateResponse zipTemplateResponse = new ZipTemplateResponse();
            TransferUtil.transfer(zipTemplate,zipTemplateResponse);
            switch (zipTemplate.getModule()){
                case 1: zipTemplateResponse.setTexture("frontPicture");
                     break;
                case 2: zipTemplateResponse.setTexture("backPicture");
                    break;
                case 3: zipTemplateResponse.setTexture("sidePicture");
                break;
                case 4: zipTemplateResponse.setTexture("nosidePicture");
                    break;
                case 5: zipTemplateResponse.setTexture("topPicture");
                    break;
                default:break;
            }
            if(map.isEmpty()){
                responses.add(zipTemplateResponse);
                continue;
            }
            List<ZipTemplateDetails> zipTemplateDetails = map.get(zipTemplate.getId());
            List<ZipTemplateDetailsResponse> zipTemplateDetailsResponses = new ArrayList<>();
            if(CollectionUtils.isEmpty(zipTemplateDetails)){
                continue;
            }
            for(ZipTemplateDetails zipTemplateDetails1 : zipTemplateDetails){
                ZipTemplateDetailsResponse response = new ZipTemplateDetailsResponse();
                TransferUtil.transfer(zipTemplateDetails1,response);
                if(zipTemplateDetails1.getType().equals(2)){
                    int num = zipTemplateDetails1.getLength()/Integer.valueOf(zipTemplateDetails1.getSizePlugIn());
                    response.setMaxLength(num);
                }
                zipTemplateDetailsResponses.add(response);
            }
            zipTemplateResponse.setList(zipTemplateDetailsResponses);
            responses.add(zipTemplateResponse);
        }
        return ResultResponse.success(responses);
    }


    @Override
    @Transactional(readOnly = false)
    public void wordToImg(ImageRequest imageRequest, HttpServletRequest request, HttpServletResponse response) {
        logger.info("参数-----------------={}",imageRequest.toString());
        if(imageRequest == null || StringUtils.isEmpty(imageRequest.getOpenId()) || StringUtils.isEmpty(imageRequest.getTemplateNum())
                || imageRequest.getTemplateDetailId() == null){
            logger.info("参数不能为空-----------------");
            return ;
        }
        ZipTemplateDetails zipTemplateDetails = zipTemplateDetailsDao.selectByPrimaryKey(imageRequest.getTemplateDetailId());
        if (zipTemplateDetails == null) {
            logger.info("zipTemplateDetails-----------------=null");
            return ;
        }
        ZipTemplate zipTemplate = zipTemplateDao.selectByPrimaryKey(zipTemplateDetails.getTemplateId());
        if (zipTemplate == null) {
            logger.info("zipTemplate-----------------=null");
            return ;
        }
        BufferedImage image = null;
        //获取材料信息
        ZipTemplateCustom zipTemplateCustomTexture = templateCustomManager.findTemplateCusstomTexture(imageRequest.getOpenId(),imageRequest.getTemplateNum());
        if(zipTemplateCustomTexture == null){
            logger.info("error--------------自定义模板未选材料-------------------");
            return ;
        }
        //获取材料图片地址
        ResultResponse resultResponse = textureManager.getInfo(zipTemplateCustomTexture.getTextureId());
        if(!"200".equals(resultResponse.getCode())){
            logger.info("error--------------未查到自定义模板-------------------");
            return ;
        }
        ZipTextureResponse zipTexture = (ZipTextureResponse)resultResponse.getData();
        String url = "";
        String lucencyUrl = "";
        switch (zipTemplate.getModule()){
            case 1: url = zipTexture.getFrontPicture();
                lucencyUrl = zipTexture.getLucencyPicture();
                break;
            case 2: url = zipTexture.getBackPicture();
                lucencyUrl = zipTexture.getLucencyPicture();
                break;
            case 3: url = zipTexture.getSidePicture() ;
                break;
            case 4: url = zipTexture.getNoSidePicture() ;
                break;
            case 5: url = zipTexture.getTopPicture() ;
                break;
            default:break;
        }

        try {
            URL imaUrl = null;
            BufferedImage textureBufferImage = null;
            //获取材料图片
            imaUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)imaUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5*1000);
            InputStream inStream = conn.getInputStream();
            textureBufferImage = ImageIO.read(inStream);

            //获取材料透明图片
            URL lucencymaUrl = null;
            BufferedImage textureBufferImage1 = null;
            //获取透明材料图片
            logger.info("lucencyUrl:{}",lucencyUrl);
            lucencymaUrl = new URL(lucencyUrl);
            HttpURLConnection conn1 = (HttpURLConnection)lucencymaUrl.openConnection();
            conn1.setRequestMethod("GET");
            conn1.setConnectTimeout(5*1000);
            InputStream inStream1 = conn1.getInputStream();
            textureBufferImage1 = ImageIO.read(inStream1);

            //压缩下模板图片
            //textureBufferImage = ImgTools.thumbnail_w_hImage(textureBufferImage,width,height);
            String imgUrl = null;
            Font font = null;
            List<ZipTemplateCustom> list = templateCustomManager.findAllTemplateList(imageRequest.getOpenId(),imageRequest.getTemplateNum(),zipTemplate.getId(),zipTemplateDetails.getId());

            Random random = new Random();
            String imgName = System.currentTimeMillis()+""+random.nextInt(10)+".png";
            imgUrl = realPath+imgName;

            if(!StringUtils.isEmpty(imageRequest.getContent())){
                font = new Font(zipTemplateDetails.getFontPlugIn(), Font.PLAIN, Integer.parseInt(zipTemplateDetails.getSizePlugIn()));
                logger.info("生成图片位置；{},wenzi:{}",zipTemplateDetails.getPlace(),imageRequest.getContent());
                image = WordToImageUtil.createImage(imageRequest.getContent(),font,zipTemplate.getModule(),Integer.parseInt(zipTemplateDetails.getPlace().substring(1)),zipTemplateDetails.getColorPlugIn());
                BufferedImage bufferedImage = WordToImageUtil.modifyImagetogeter(image,textureBufferImage, 0);
                image = bufferedImage;
                // 输出png图片 到指定地址
//                ImageIO.write(image, "png", new File("C:\\Users\\jianwu\\Desktop\\ces.png"));
            }else{
                logger.info("error--------------文字为空-------------------");
                return;
            }
            ZipTemplateCustom custom = templateCustomManager.findTemplateCustomList(imageRequest.getOpenId(),imageRequest.getTemplateNum(),
                    imageRequest.getTemplateDetailId(),zipTemplate.getId());
            if(custom == null){
                //不存在此自定义模板 则新增自定义模板
                templateCustomManager.insertTemplateCusstom(imageRequest.getOpenId(),zipTemplate.getId(),
                        imageRequest.getTemplateDetailId(),imgUrl,imageRequest.getContent(),imageRequest.getTemplateNum(),null);
            }else{
                if(!imageRequest.getContent().equals(custom.getContent())){
                    custom.setContent(imageRequest.getContent());
                    custom.setImgUrl(imgUrl);
                    templateCustomManager.updateTemplateCusstom(custom);
                }
            }
            if(CollectionUtils.isEmpty(list)){
                response.setContentType("image/png");
                //之前没有自定义信息
                OutputStream out = response.getOutputStream();
                ImageIO.write(image,"png",out);
                out.flush();
                out.close();
            }else {
                int num = list.size();
                for (int n = 0; n < num; n++) {
                    ZipTemplateCustom imageRequestOld = list.get(n);
                    ZipTemplateDetails zipTemplateDetail = zipTemplateDetailsDao.selectByPrimaryKey(imageRequestOld.getTemplateDetailsId());
                    if(!StringUtils.isEmpty(imageRequestOld.getContent())){
                        //将之前的文字贴到材料上
                        Font fonts = new Font(zipTemplateDetail.getFontPlugIn(), Font.PLAIN, Integer.parseInt(zipTemplateDetail.getSizePlugIn()));
                        if(image == null){
//                            ImageIO.write(image, "png", new File("C:\\Users\\jianwu\\Desktop\\ces2.png"));
                            image = WordToImageUtil.createImage(imageRequestOld.getContent(),fonts,zipTemplate.getModule(),Integer.parseInt(zipTemplateDetail.getPlace().substring(1)),zipTemplateDetail.getColorPlugIn());
                            BufferedImage bufferedImage = WordToImageUtil.modifyImagetogeter(image,textureBufferImage, 0);
                            image = bufferedImage;
                            // 输出png图片 到指定地址
//                            ImageIO.write(image, "png", new File("C:\\Users\\jianwu\\Desktop\\ces3.png"));
                        }else{
//                            ImageIO.write(image, "png", new File("C:\\Users\\jianwu\\Desktop\\ces4.png"));
                            //生成文字图片
                            BufferedImage bufferedImage =  WordToImageUtil.createImage(imageRequestOld.getContent(),fonts,zipTemplate.getModule(),Integer.parseInt(zipTemplateDetail.getPlace().substring(1)),zipTemplateDetail.getColorPlugIn());
                            BufferedImage bufferedImageNew = WordToImageUtil.modifyImagetogeter(bufferedImage,image, 0);
                            image = bufferedImageNew;
                            // 输出png图片 到指定地址
//                            ImageIO.write(image, "png", new File("C:\\Users\\jianwu\\Desktop\\ces5.png"));
                        }
                    }else{
                        //将之前的图片贴到材料上
                        //BufferedImage newImg = WordToImageUtil.loadImageLocal(imageRequestOld.getImgUrl());
                        BufferedImage newImg = this.comm(imageRequestOld.getImgUrl(),zipTemplateDetail.getWidth(),zipTemplateDetail.getHeight());
                        logger.info("zipTemplateDetail--->{}",zipTemplateDetail.toString());
                        BufferedImage bufferedImage = WordToImageUtil.modifyImagetogeter(newImg,image, Integer.parseInt(zipTemplateDetail.getPlace().substring(1)));
                        image = bufferedImage;
                    }
                }
                BufferedImage bufferedImage2 = WordToImageUtil.modifyImagetogeter(textureBufferImage1,image, 0);
                image = bufferedImage2;
                response.setContentType("image/png");
                //之前没有自定义信息
                OutputStream out = response.getOutputStream();
                // 输出png图片
                ImageIO.write(image, "png", out);
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void imgToImg(String openId,String templateNum, Integer templateDetailId, HttpServletResponse response, HttpServletRequest request,String qiniuUrl) {
        logger.info("qiniuUrl----------------------->{}",qiniuUrl);
        if( StringUtils.isEmpty(openId) || StringUtils.isEmpty(templateNum)
                || templateDetailId == null){
            logger.info("参数不能为空-----------------openId={},templateNum={},templateDetailId={}",openId,templateNum,templateDetailId);
            return ;
        }
        ZipTemplateDetails zipTemplateDetails = zipTemplateDetailsDao.selectByPrimaryKey(templateDetailId);
        if (zipTemplateDetails == null) {
            logger.info("zipTemplateDetails-----------------=null");
            return ;
        }
        ZipTemplate zipTemplate = zipTemplateDao.selectByPrimaryKey(zipTemplateDetails.getTemplateId());
        if (zipTemplate == null) {
            logger.info("zipTemplate-----------------=null");
            return ;
        }
        BufferedImage image = null;
        //获取材料信息
        ZipTemplateCustom zipTemplateCustomTexture = templateCustomManager.findTemplateCusstomTexture(openId,templateNum);
        if(zipTemplateCustomTexture == null){
            logger.info("error--------------自定义模板未选材料-------------------");
            return ;

        }
        //获取材料图片地址
        ResultResponse resultResponse = textureManager.getInfo(zipTemplateCustomTexture.getTextureId());
        if(!"200".equals(resultResponse.getCode())){
            logger.info("error--------------未查到自定义模板-------------------");
            return ;
        }
        //材质
        ZipTextureResponse zipTexture = (ZipTextureResponse)resultResponse.getData();
        String url = "";
        String lucencyUrl = "";
        switch (zipTemplate.getModule()){
            case 1: url = zipTexture.getFrontPicture();
                    lucencyUrl = zipTexture.getLucencyPicture();
                break;
            case 2: url = zipTexture.getBackPicture();
                    lucencyUrl = zipTexture.getLucencyPicture();
                break;
            case 3: url = zipTexture.getSidePicture() ;
                break;
            case 4: url = zipTexture.getNoSidePicture() ;
                break;
            case 5: url = zipTexture.getTopPicture() ;
                break;
            default:break;
        }
        try {
            URL imaUrl = null;
            BufferedImage textureBufferImage = null;
            //获取材料图片
            imaUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)imaUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5*1000);
            InputStream inStream = conn.getInputStream();
            textureBufferImage = ImageIO.read(inStream);

            //获取材料透明图片
            URL lucencymaUrl = null;
            BufferedImage textureBufferImage1 = null;
            //获取透明材料图片
            logger.info("lucencyUrl:{}",lucencyUrl);
            lucencymaUrl = new URL(lucencyUrl);
            HttpURLConnection conn1 = (HttpURLConnection)lucencymaUrl.openConnection();
            conn1.setRequestMethod("GET");
            conn1.setConnectTimeout(5*1000);
            InputStream inStream1 = conn1.getInputStream();
            textureBufferImage1 = ImageIO.read(inStream1);
            //压缩下模板图片
//            textureBufferImage = ImgTools.thumbnail_w_hImage(textureBufferImage,width,height);

            String imgUrl = null;
            Font font = null;
            List<ZipTemplateCustom> list = templateCustomManager.findAllTemplateList(openId,templateNum,zipTemplate.getId(),zipTemplateDetails.getId());
            Random random = new Random();
            String imgName = System.currentTimeMillis()+""+random.nextInt(10)+".png";
            imgUrl = realPath+imgName;
            //todo 获取七牛地址的图片 qiniuUrl 指定大小图片
            BufferedImage imageNew = this.comm(qiniuUrl,zipTemplateDetails.getWidth(), zipTemplateDetails.getHeight());
//            ImageIO.write(imageNew, "png", new File("C:\\Users\\jianwu\\Desktop\\cesimageNew.png"));
            //压缩图片
            if(zipTemplateDetails.getWidth() == null || zipTemplateDetails.getWidth() == 0 ||
                    zipTemplateDetails.getHeight() == null || zipTemplateDetails.getHeight() == 0){
                return ;
            }
            //合成图片
            BufferedImage bufferedImage = WordToImageUtil.modifyImagetogeter(imageNew,textureBufferImage, Integer.parseInt(zipTemplateDetails.getPlace().substring(1)));
            image = bufferedImage;
//            ImageIO.write(image, "png", new File("C:\\Users\\jianwu\\Desktop\\cesimageNewbufferedImage.png"));

            BufferedImage bufferedImage1 = WordToImageUtil.modifyImagetogeter(textureBufferImage1,image, 0);
            image = bufferedImage1;
//            ImageIO.write(image, "png", new File("C:\\Users\\jianwu\\Desktop\\cesimageNewbufferedImage1.png"));

            ZipTemplateCustom custom = templateCustomManager.findTemplateCustomList(openId,templateNum,
                    templateDetailId,zipTemplate.getId());
            if(custom == null){
                //不存在此自定义模板 则新增自定义模板
                templateCustomManager.insertTemplateCusstom(openId,zipTemplate.getId(),
                        templateDetailId,qiniuUrl,null,templateNum,null);
            }else{
                custom.setImgUrl(imgUrl);
                templateCustomManager.updateTemplateCusstom(custom);
            }

            if(CollectionUtils.isEmpty(list)){
                //之前没有自定义信息
                response.setContentType("image/png");
                //之前没有自定义信息
                OutputStream out = response.getOutputStream();
                ImageIO.write(image,"png",out);
                out.flush();
                out.close();
            }else {
                int num = list.size();
                for (int n = 0; n < num; n++) {
                    ZipTemplateCustom imageRequestOld = list.get(n);
                    ZipTemplateDetails zipTemplateDetail = zipTemplateDetailsDao.selectByPrimaryKey(imageRequestOld.getTemplateDetailsId());
                    if(!StringUtils.isEmpty(imageRequestOld.getContent())){
                        //将之前的文字贴到材料上
                        Font fonts = new Font(zipTemplateDetail.getFontPlugIn(), Font.PLAIN, Integer.parseInt(zipTemplateDetail.getSizePlugIn()));
                        if(image == null){
//                            ImageIO.write(image, "png", new File("C:\\Users\\jianwu\\Desktop\\ces2.png"));
                            image = WordToImageUtil.createImage(imageRequestOld.getContent(),fonts,zipTemplate.getModule(),Integer.parseInt(zipTemplateDetail.getPlace().substring(1)),zipTemplateDetail.getColorPlugIn());
                            BufferedImage bufferedImages = WordToImageUtil.modifyImagetogeter(image,textureBufferImage, 0);
                            image = bufferedImages;
                            // 输出png图片 到指定地址
//                            ImageIO.write(image, "png", new File("C:\\Users\\jianwu\\Desktop\\ces3.png"));
                        }else{
//                            ImageIO.write(image, "png", new File("C:\\Users\\jianwu\\Desktop\\ces4.png"));
                            //生成文字图片
                            BufferedImage bufferedImages =  WordToImageUtil.createImage(imageRequestOld.getContent(),fonts,zipTemplate.getModule(),Integer.parseInt(zipTemplateDetail.getPlace().substring(1)),zipTemplateDetail.getColorPlugIn());
                            BufferedImage bufferedImageNew = WordToImageUtil.modifyImagetogeter(bufferedImages,image, 0);
                            image = bufferedImageNew;
                            // 输出png图片 到指定地址
//                            ImageIO.write(image, "png", new File("C:\\Users\\jianwu\\Desktop\\ces5.png"));
                        }
                    }else{
                        //将之前的图片贴到材料上
                        //todo 获取七牛地址的图片 qiniuUrl 指定大小图片
                        //BufferedImage newImg = WordToImageUtil.loadImageLocal(imageRequestOld.getImgUrl());
                        BufferedImage newImg = this.comm(imageRequestOld.getImgUrl(),zipTemplateDetail.getWidth(), zipTemplateDetail.getHeight());
                        BufferedImage bufferedImages = WordToImageUtil.modifyImagetogeter(newImg,image, Integer.parseInt(zipTemplateDetail.getPlace().substring(1)));
                        image = bufferedImages;
//                        ImageIO.write(image, "png", new File("C:\\Users\\jianwu\\Desktop\\ces6.png"));
                    }
                }
                BufferedImage bufferedImage2 = WordToImageUtil.modifyImagetogeter(textureBufferImage1,image, 0);
                image = bufferedImage2;
                //之前没有自定义信息
//                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.setContentType("image/png");
                //之前没有自定义信息
                OutputStream out = response.getOutputStream();
                // 输出png图片
                ImageIO.write(image, "png", out);
                String key=UUID.randomUUID().toString();
//                s = tUrl+key;
//                logger.info("图片缓存地址s------------>{}",s);
//                cache.put(key,out.toByteArray());
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return ResultResponse.success(s);
    }

    public BufferedImage comm(String qiniuUrl,Integer width,Integer height) throws IOException {
        //URL urls = new URL(qiniuUrl+"?imageView2/1/w/"+width+"/h/"+height);
        URL urls = new URL(qiniuUrl+"?imageMogr2/thumbnail/"+width+"x"+height+"");
        logger.info("qiniuUrl"+qiniuUrl+"?imageMogr2/thumbnail/"+width+"x"+height+"");
        HttpURLConnection connection =  (HttpURLConnection) urls.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        InputStream is = connection.getInputStream();

        BufferedImage imageNew = ImageIO.read(is);
        return imageNew;
    }

    @Override
    public void getImg(String key,HttpServletResponse response) {
//        logger.info("key ---------------------->{}",key);
//        if(StringUtils.isEmpty(key)){
//            return ;
//        }
//        byte [] bytes = cache.get(key);
//        response.setContentType("image/png");
//        //之前没有自定义信息
//        OutputStream out = null;
//        try {
//            if(bytes==null || bytes.length==0){//根据byte数组长度为0判断
//                logger.info("------bytes数组为空-----------");
//                return ;
//            }
//            out = response.getOutputStream();
//            ByteArrayInputStream in = new ByteArrayInputStream(bytes);    //将b作为输入流；
//            BufferedImage bufferedImage = ImageIO.read(in);
//            // 输出png图片
//            ImageIO.write( bufferedImage, "png", out);
//            out.flush();
//            out.close();
//            cache.remove(key);
//            logger.info("------cache的大小-----------{}",cache.size());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public ResultResponse imgToUpload(String openId, String templateNum, Integer templateDetailId, HttpServletResponse response, HttpServletRequest request) {
        if( StringUtils.isEmpty(openId) || StringUtils.isEmpty(templateNum)
                || templateDetailId == null){
            logger.info("参数不能为空-----------------openId={},templateNum={},templateDetailId={}",openId,templateNum,templateDetailId);
            return ResultResponse.error("500","参数不能为空");
        }

        ZipTemplateDetails zipTemplateDetails = zipTemplateDetailsDao.selectByPrimaryKey(templateDetailId);
        if (zipTemplateDetails == null) {
            logger.info("zipTemplateDetails-----------------=null");
            return ResultResponse.error("500","zipTemplateDetails参数不能为空");
        }
        ZipTemplate zipTemplate = zipTemplateDao.selectByPrimaryKey(zipTemplateDetails.getTemplateId());
        if (zipTemplate == null) {
            logger.info("zipTemplate-----------------=null");
            return ResultResponse.error("500","zipTemplate参数不能为空");
        }

        BufferedImage image = null;
        //获取材料信息
        ZipTemplateCustom zipTemplateCustomTexture = templateCustomManager.findTemplateCusstomTexture(openId,templateNum);
        if(zipTemplateCustomTexture == null){
            logger.info("error--------------自定义模板未选材料-------------------");
            return ResultResponse.error("500","自定义模板未选材料");
        }
        //获取材料图片地址
        ResultResponse resultResponse = textureManager.getInfo(zipTemplateCustomTexture.getTextureId());
        if(!"200".equals(resultResponse.getCode())){
            logger.info("error--------------未查到自定义模板-------------------");
            return ResultResponse.error("500","未查到自定义模板");
        }
        ZipTextureResponse zipTexture = (ZipTextureResponse)resultResponse.getData();
        String url = "";
        String lucencyUrl = "";
        switch (zipTemplate.getModule()){
            case 1: url = zipTexture.getFrontPicture();
                    lucencyUrl = zipTexture.getLucencyPicture();
                break;
            case 2: url = zipTexture.getBackPicture();
                lucencyUrl = zipTexture.getLucencyPicture();
                break;
            case 3: url = zipTexture.getSidePicture() ;
                break;
            case 4: url = zipTexture.getNoSidePicture() ;
                break;
            case 5: url = zipTexture.getTopPicture() ;
                break;
            default:break;
        }

        Random random = new Random();
        String imgName = "zippo"+System.currentTimeMillis()+""+random.nextInt(10)+".png";
        String imgUrl = realPath+imgName;
        String resultUrl = "http://"+qnUrl+"/"+imgName;
        File imagFile = new File(imgUrl);
        try {
            URL imaUrl = null;
            BufferedImage textureBufferImage = null;
            //获取材料图片
            imaUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)imaUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5*1000);
            InputStream inStream = conn.getInputStream();
            textureBufferImage = ImageIO.read(inStream);

            //获取材料透明图片
            URL lucencymaUrl = null;
            BufferedImage textureBufferImage1 = null;
            //获取透明材料图片
            logger.info("lucencyUrl:{}",lucencyUrl);
            lucencymaUrl = new URL(lucencyUrl);
            HttpURLConnection conn1 = (HttpURLConnection)lucencymaUrl.openConnection();
            conn1.setRequestMethod("GET");
            conn1.setConnectTimeout(5*1000);
            InputStream inStream1 = conn1.getInputStream();
            textureBufferImage1 = ImageIO.read(inStream1);

            //压缩下模板图片
            List<ZipTemplateCustom> customs = templateCustomManager.findCustomList(openId,templateNum, zipTemplate.getId());
            if(CollectionUtils.isEmpty(customs)){
                image = textureBufferImage;
                //写入临时文件
                ImageIO.write(image, "png", imagFile);
                sleep(1000);
                // 上传七牛图片
                boolean result = qiNiuManager.upload(imgUrl,imgName);
                if(result){
                    //判断文件是否存在
                    if (imagFile.exists()) {
                         imagFile.delete();
                    }
                    return ResultResponse.success(resultUrl);
                }else{
                    //判断文件是否存在
                    if (imagFile.exists()) {
                        imagFile.delete();
                    }
                    return ResultResponse.error("500","上传失败");
                }
            }
            for(ZipTemplateCustom custom : customs){
                ZipTemplateDetails zipTemplateDetail = zipTemplateDetailsDao.selectByPrimaryKey(custom.getTemplateDetailsId());
                if(!StringUtils.isEmpty(custom.getContent())){
                    Font fonts = new Font(zipTemplateDetail.getFontPlugIn(), Font.PLAIN, Integer.parseInt(zipTemplateDetail.getSizePlugIn()));
                    if(image == null){
                        //文字合成 和模板图片
                        //将之前的文字贴到材料上
                        image = WordToImageUtil.createImage(custom.getContent(),fonts,zipTemplate.getModule(),Integer.parseInt(zipTemplateDetail.getPlace().substring(1)),zipTemplateDetails.getColorPlugIn());

                        BufferedImage bufferedImages = WordToImageUtil.modifyImagetogeter(image,textureBufferImage, 0);
                        image = bufferedImages;
                    }else{

                        BufferedImage imageNew = WordToImageUtil.createImage(custom.getContent(),fonts,zipTemplate.getModule(),Integer.parseInt(zipTemplateDetail.getPlace().substring(1)),zipTemplateDetails.getColorPlugIn());
                        BufferedImage bufferedImages = WordToImageUtil.modifyImagetogeter(imageNew,image,0);
                        image = bufferedImages;
                    }

                }else{
                    if(image == null){
                        //图片合成和模板图片
                        //将之前的图片贴到材料上
                        //todo 获取七牛地址的图片   指定大小图片 custom.getImgUrl()
                        BufferedImage newImg = this.comm(custom.getImgUrl(),zipTemplateDetail.getWidth(), zipTemplateDetail.getHeight());
                        //BufferedImage newImg = WordToImageUtil.loadImageLocal(custom.getImgUrl());
                        BufferedImage bufferedImages = WordToImageUtil.modifyImagetogeter(newImg,textureBufferImage, Integer.parseInt(zipTemplateDetail.getPlace().substring(1)));
                        image = bufferedImages;
                    }else{
                        //将之前的图片贴到材料上
                        //todo 获取七牛地址的图片   指定大小图片 custom.getImgUrl()
                        BufferedImage newImg = this.comm(custom.getImgUrl(),zipTemplateDetail.getWidth(), zipTemplateDetail.getHeight());
                       // BufferedImage newImg = WordToImageUtil.loadImageLocal(custom.getImgUrl());
                        BufferedImage bufferedImages = WordToImageUtil.modifyImagetogeter(newImg,image, Integer.parseInt(zipTemplateDetail.getPlace().substring(1)));
                        image = bufferedImages;
                    }
                }
            }
            BufferedImage bufferedImage2 = WordToImageUtil.modifyImagetogeter(textureBufferImage1,image, 0);
            image = bufferedImage2;
            //写入临时文件
            ImageIO.write(image, "png", imagFile);
            sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 上传七牛图片
        boolean result = qiNiuManager.upload(imgUrl,imgName);
        if(result){
            //判断文件是否存在
            if (imagFile.exists()) {
                imagFile.delete();
            }
            return ResultResponse.success(resultUrl);
        }else{
            //判断文件是否存在
            if (imagFile.exists()) {
                imagFile.delete();
            }
            return ResultResponse.error("500","上传失败");
        }
    }
}
