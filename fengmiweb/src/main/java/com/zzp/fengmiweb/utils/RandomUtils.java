package com.zzp.fengmiweb.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

public class RandomUtils {
    //生成激活码
    public static String createActive(){
        return getTime() + Integer.toHexString(new Random().nextInt(900) + 100);
    }
    //设置时间戳
    public static String getTime() {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
    }
    //生成订单号
    public static String createOrderId(){
        return getTime() + UUID.randomUUID().toString();
    }
}
