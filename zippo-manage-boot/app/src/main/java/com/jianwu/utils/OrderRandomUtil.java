package com.jianwu.utils;

import java.util.Random;

/**
 * 订单编号随机数
 *
 * @Author chendong
 * @Create 2018/6/5
 * @Time 17:08
 **/
public class OrderRandomUtil {
    public static String orderRandom() {
        Random random = new Random();
        String base = "0123456789";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 19; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return System.currentTimeMillis()+sb.toString();
    }
}
