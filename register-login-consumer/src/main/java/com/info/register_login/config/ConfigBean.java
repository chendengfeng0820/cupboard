package com.info.register_login.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: cdf
 * @create: 2020-05-31 15:26
 **/
@Configuration
public class ConfigBean {

	@Bean
	@LoadBalanced  //负载均衡
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
}
