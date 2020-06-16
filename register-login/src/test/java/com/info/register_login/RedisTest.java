package com.info.register_login;

import com.info.register_login.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;

/**
 * @ClassName RedisTest
 * @Description TODO
 * @author: cdf
 * @Date: 2020-06-16 18:49
 **/
@SpringBootTest
public class RedisTest {

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void test(){
		User user=new User(2,"18937663963","付艳玲","123",new Date());

		redisTemplate.opsForValue().set("test",user);
		System.out.println(redisTemplate.opsForValue().get("test"));
	}

}
