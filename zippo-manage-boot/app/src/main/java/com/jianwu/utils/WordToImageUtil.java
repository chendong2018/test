package com.jianwu.utils;

import sun.font.FontDesignMetrics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 文字生成图片
 *
 * @Author chendong
 * @Create 2018/5/31
 * @Time 17:24
 **/
public class WordToImageUtil {
    public static void main(String[] args) throws Exception {
        String s = "A1";

        System.out.println(  s.substring(1));
        Integer size = 100;
        int faceType = 1;
        int faceDetailType = 5;
        String color="#a1b4cc";
        String old = "C:\\Users\\jianwu\\Desktop\\wxfc11f07da4ef209f.o6zAJsywEqMNnvT5_edKB89lzRLE.l4m8RUFp9Jrce483c07ff1c8904ec117b2185543715b.png";
        String outFile = "C:\\Users\\jianwu\\Desktop\\222.png";
        String outFile2 = "C:\\Users\\jianwu\\Desktop\\333.png";
        BufferedImage image = loadImageLocal("C:\\Users\\jianwu\\Desktop\\未标题-1.png");
//        BufferedImage word  = createImage("ssss", new Font("楷体", Font.PLAIN, size),faceType,faceDetailType,color);
//        ImageIO.write(word,"png",new File(outFile2));
//        BufferedImage imgToImg = modifyImagetogeter(word,image,10);
//        ImageIO.write(imgToImg,"png",new File(outFile));
        BufferedImage image2 = loadImageLocal(old);
        BufferedImage imgToImg2 = modifyImagetogeter(image2,image,faceDetailType);
        ImageIO.write(imgToImg2,"png",new File(outFile));

    }

    /**
     * @ClassName: WordToImageUtil
     * @Description:  根据str,font的样式以及输出文件目录 文字转图片
     * @Author: chenDong
     * @Date: 2018/6/4 10:46
     * @Remark:
     */
    public static BufferedImage createImage(String str, Font font,Integer faceType, Integer faceDetailType,String color) throws Exception {
        System.out.println(str.length());
        // 创建图片
        Integer width = 0;
        Integer height = 0;
        if(faceType.equals(1) || faceType.equals(2)){
            width = 412;
            height = 648;
        }
        if(faceType.equals(3)||faceType.equals(4)){
            width = 648;
            height = 140;
        }
        if(faceType.equals(5) ){
            width = 140;
            height = 412;
        }
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_BGR);
        Graphics2D g = image.createGraphics();
        // 增加下面代码使得背景透明
        image = g.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g.dispose();
        g = image.createGraphics();
        // 背景透明代码结束
        Integer num = Integer.valueOf(color.replace("#",""),16);
        g.setStroke(new BasicStroke(1f));
        Color color1 = new Color(num);
        g.setColor(color1);// 在换成黑色
        g.setFont(font);// 设置画笔字体
        /** 用于获得垂直居中y */
        FontMetrics fm = g.getFontMetrics(font);
        int ascent = fm.getAscent();
        int descent = fm.getDescent();
        int length = getWordWidth(font, str);//计算图片的宽
        int size = fm.getHeight();//计算高
        System.out.println("length--"+str.getBytes("UTF-8").length);
        //偏移10
        int space = 40;
        int heigthTop = 260;
        int heigthLast = 390;
        //向下偏移15
        int offset = 3;
        int x = 0;
        int y = 0;
        switch (faceDetailType){
            case 0 :
                x = width/2 - (length)/2;
                y = (height - (length)) / 2 + size/2+offset;
                break;
            case 1 :
                x = space;
                y =(size)/2+space+offset;
                break;
            case 2 :
                x = width -(length)-space;
                y = ( size)/2+space+offset;
                break;
            case 3 :
                x = space;
                y = heigthTop-(size)/2+offset;
                break;
            case 4 :
                x = width -(length)-space;
                y = heigthTop-(size)/2+offset;
                break;
            case 5 :
                x = width/2 - (length)/2;
                y = heigthTop/ 2 + (size)/2-space+offset  ;
                break;
            case 6 :
                x = space;
                y = heigthTop +offset + (size)/2;
                break;
            case 7 :
                x = width -(length)-space;
                y = heigthTop +offset + (size)/2;
                break;
            case 8 :
                x = space;
                y = height - (size)/2 -offset;
                break;
            case 9 :
                x = width -(length)-space;
                y = height - (size)/2 -offset;
                break;
            case 10 :
                x = width/2 - (length)/2;
                y = heigthTop +(heigthLast - (length)) / 2 + (length)/2;
                break;
            default: break;
        }
        System.out.println("x=="+x+"y=="+y);
        g.drawString(str, x, y);// 画出字符串
//        g.drawString("-----------", 1, 259+offset);
        g.dispose();
//        ImageIO.write(image, "png", outFile);// 输出png图片
        return image;
    }

    public static int getWordWidth(Font font, String content) {
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        int width = 0;
        for (int i = 0; i < content.length(); i++) {
            width += metrics.charWidth(content.charAt(i));
        }
        return width;
    }

    public static void write(BufferedImage bufferedImage, String target) throws IOException {
        File file = new File(target);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try (OutputStream os = new FileOutputStream(target)) {
            ImageIO.write(bufferedImage, "PNG", os);
        }
    }

    /**
     * @ClassName: WordToImageUtil
     * @Description: 生成zip 大小的图片
     * @Author: chenDong
     * @Date: 2018/6/1 9:40
     * @Remark:
     */
    public static BufferedImage createImage2(  Font font,Integer faceType) throws Exception {
        // 创建图片
        Integer width = 0;
        Integer height = 0;
        if(faceType.equals(1) || faceType.equals(2)){
            width = 412;
            height = 648;
        }
        if(faceType.equals(3)||faceType.equals(4)){
            width = 648;
            height = 140;
        }
        if(faceType.equals(5) ){
            width = 140;
            height = 412;
        }
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_BGR);
        Graphics2D g = image.createGraphics();
        // 增加下面代码使得背景透明
        image = g.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g.dispose();
        g = image.createGraphics();
        // 背景透明代码结束
        g.setStroke(new BasicStroke(1f));
        g.setColor(Color.red);// 在换成黑色
        g.setFont(font);// 设置画笔字体
//        g.drawString("-----------", 1, 259+15);
        g.dispose();
//        ImageIO.write(image, "png", outFile);// 输出png图片
        return image;
    }

    /**
     * @ClassName: WordToImageUtil
     * @Description: 合并两张图片
     * @Author: chenDong
     * @Date: 2018/6/1 9:34
     * @Remark:
     */
    public static BufferedImage  modifyImagetogeter (BufferedImage b, BufferedImage d ,int faceDetailType)throws Exception  {
        try {
            int w = b.getWidth();
            int h = b.getHeight();
            int width = d.getWidth();
            int height = d.getHeight();
            Graphics2D g = d.createGraphics();
            //偏移10
            int space = 40;
            int heigthTop = 260;
            int heigthLast = 390;
            //向下偏移15
            int offset = 0;
            int x = 0;
            int y = 0;
            switch (faceDetailType){
                case 0 :
                    x = width/2 - w/2 ;
                    y = height / 2 +offset - h/2;
                    break;
                case 1 :
                    x = space;
                    y = space+offset;
                    break;
                case 2 :
                    x = width - w - space;
                    y = space + offset;
                    break;
                case 3 :
                    x = space;
                    y = heigthTop - h - space + offset;
                    break;
                case 4 :
                    x = width - w - space;
                    y = heigthTop - h - space + offset;
                    break;
                case 5 :
                    x = width/2 - w/2 ;
                    y = heigthTop / 2 +offset - h/2;
                    break;
                case 6 :
                    x = space;
                    y = heigthTop + space+offset ;
                    break;
                case 7 :
                    x = width - w - space;
                    y = heigthTop + space+offset ;
                    break;
                case 8 :
                    x = space;
                    y = height - space+offset - h;
                    break;
                case 9 :
                    x = width - w - space;
                    y = height - space+offset - h;
                    break;
                case 10 :
                    x = width/2 - w/2 ;
                    y = heigthTop+(heigthLast/2)+offset-h/2;
                    break;
                default: break;
            }
            g.drawImage(b, x, y, w, h, null);
            g.dispose();
//            ImageIO.write(d, "png", new File(outFile));// 输出png图片
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return d;
    }

    /**
     * @ClassName: WordToImageUtil
     * @Description: 导入图片
     * @Author: chenDong
     * @Date: 2018/6/1 9:56
     * @Remark:
     */
    public static BufferedImage loadImageLocal(String imgName) {
        try {
            return ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
