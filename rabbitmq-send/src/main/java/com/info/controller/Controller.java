package com.info.controller;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.alibaba.fastjson.JSONObject;
import com.info.service.SendService;
import com.info.service.express.PostService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName Controller
 * @Description TODO
 * @author: cdf
 * @Date: 2020-07-05 21:22
 **/
@RestController
public class Controller {

    @Autowired
    private SendService sendService;

    @Autowired
    private PostService postService;

    @RequestMapping("/post")
    public String post(@RequestBody JSONObject jsonObject){
        return postService.post(jsonObject);
    }

    @RequestMapping("/send")
    public String sendRabbitMessage(@RequestBody JSONObject jsonObject) {
        String result = postService.post(jsonObject);
        System.out.println(result);
        sendService.sendMessage(result);
        return "ok";
    }


    @RequestMapping("/send1")
    public String sendMessage() {
        sendService.sendMessage("你好");
        return "ok";
    }


    @RequestMapping("/sendfanout")
    public String sendFanoutMessage() {
        sendService.sendFanoutMessage("你好");
        return "ok";
    }


    @RequestMapping("/sendtopic")
    public String sendTopicMessage() {
        sendService.sendtopicMessage("你好sendtopic");
        return "ok";
    }

}
