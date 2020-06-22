package com.info.pojo.service;


import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient (value = "REGISTER-LOGIN")
public interface ILoginService {

    @RequestMapping("/findAll")
    public String getAll();

    @RequestMapping("/login")
    public String login(@RequestBody JSONObject jsonObject);
}