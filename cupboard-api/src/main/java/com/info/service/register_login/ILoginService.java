package com.info.service.register_login;


import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient (value = "REGISTER-LOGIN")
public interface ILoginService {

    /**
     * 查找全部
     * @return
     */
    @RequestMapping("/findAll")
    public String getAll();

    /**
     * 登录
     * @param jsonObject
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestBody JSONObject jsonObject);
}