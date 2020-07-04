package com.info.preserve.contoller;

import com.alibaba.fastjson.JSONObject;
import com.info.service.preserve.BoardUsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName Login
 * @Description 登录consumer
 * @author: cdf
 * @Date: 2020-06-19 15:42
 **/
@RestController
public class PerserveController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BoardUsingService boardUsingService;

    @RequestMapping("/boardusing")
    public String boardUsing(){
        return boardUsingService.boardUsing();
    }

    @RequestMapping("/subscribe")
    public String  subscribe(@RequestBody JSONObject jsonObject){
        return boardUsingService.subscribe(jsonObject);
    }

    @RequestMapping("/ttl")
    public String ttl(@RequestBody JSONObject jsonObject){
        return boardUsingService.ttl(jsonObject);
    }
}
