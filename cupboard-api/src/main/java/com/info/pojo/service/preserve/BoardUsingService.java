package com.info.pojo.service.preserve;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "PRESERVE")
public interface BoardUsingService {

	@RequestMapping("/boardusing")
	public String boardUsing();

	@RequestMapping("/subscribe")
	public String  subscribe(@RequestBody JSONObject jsonObject);

	@RequestMapping("/ttl")
	public String ttl(@RequestBody JSONObject jsonObject);
}
