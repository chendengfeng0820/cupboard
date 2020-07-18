package com.info.service.express;


import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "EXPRESS")
public interface PostService {

    @RequestMapping("/post")
    public String post(@RequestBody JSONObject jsonObject);

}
