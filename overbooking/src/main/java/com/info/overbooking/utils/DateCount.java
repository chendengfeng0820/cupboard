package com.info.overbooking.utils;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName DateCount
 * @Description TODO
 * @author: cdf
 * @Date: 2020-07-04 13:55
 **/
public class DateCount {

    public static void main(String[] args) {


        String timeStr1=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String timeStr2=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("当前时间为:"+timeStr1);
        System.out.println("当前时间为:"+timeStr2);
        System.out.println();


    }
}
