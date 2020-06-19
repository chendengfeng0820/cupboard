package com.info.register_login;

import com.info.register_login.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;


/**
 * @ClassName SptringbootTest
 * @Description TODO
 * @author: cdf
 * @Date: 2020-06-16 17:34
 **/
@SpringBootTest
public class SptringbootTest {

	@Autowired
//	@Qualifier("redisTemplate")
	private RedisTemplate redisTemplate;

//	@Autowired
//	private RedisUtil redisUtil;

	@Test
	public void test(){
//		System.out.println(redisTemplate.opsForValue().get("name"));
//		System.out.println(redisUtil.get("name"));
	}

	@Test
	public void test1(){
		System.out.println(redisTemplate.opsForValue().get("name"));
	}
}
