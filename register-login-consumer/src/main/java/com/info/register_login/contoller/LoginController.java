package com.info.register_login.contoller;

import com.alibaba.fastjson.JSONObject;
import com.info.pojo.User;
import com.info.pojo.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @ClassName Login
 * @Description 登录consumer
 * @author: cdf
 * @Date: 2020-06-19 15:42
 **/
@RestController
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ILoginService loginService;

    public static final String REST_URL_PREFIX = "http://REGISTER-LOGIN";


//	@RequestMapping("/findAll")
//	public String getAll() {
//		return restTemplate.getForObject(REST_URL_PREFIX+"/findAll",String.class);
//	}

    //	@RequestMapping("/login")
//	public String login(@RequestBody JSONObject jsonObject) {
//		return restTemplate.getForObject(REST_URL_PREFIX+"/login",String.class);
//	}
    @RequestMapping("/login")
    public String login(@RequestBody JSONObject jsonObject) {
        return loginService.login(jsonObject);
    }


    @RequestMapping("/findAll")
    public String getAll1() {
        return loginService.getAll();
    }

}
