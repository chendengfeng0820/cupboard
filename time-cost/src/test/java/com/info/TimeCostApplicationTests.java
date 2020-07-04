package com.info;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class TimeCostApplicationTests {

    @Test
    void contextLoads() {
        //        Timestamp timestamp = new Timestamp();
        Date date = new Date();
        String simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
        String simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

        System.out.println(simpleDateFormat);
        System.out.println(simpleDateFormat1);

        Timestamp timeStamp = new Timestamp(date.getTime());     //   讲日期时间转换为数据库中的timestamp类型
        System.out.println(timeStamp);
    }

}
