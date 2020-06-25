package com;

import com.info.register_login.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName RedisTest
 * @Description TODO
 * @author: cdf
 * @Date: 2020-06-26 03:27
 **/
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtil redisUtil;
    
    @Test
    public void redisTest(){
        redisUtil.set("name" ,"陈登峰",100);
        
    }
}
