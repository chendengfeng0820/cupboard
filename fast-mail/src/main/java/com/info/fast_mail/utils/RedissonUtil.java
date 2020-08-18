package com.info.fast_mail.utils;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RedissonUtil
 * @Description TODO
 * @author: cdf
 * @Date: 2020-08-18 14:16
 **/

@Configuration
public class RedissonUtil {

	@Bean("redisson1")
	public Redisson getRedisson(){
		//单击模式
		Config config = new Config();
		config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);
		return (Redisson) Redisson.create(config);
	}
}
