package com.info.register_login.config;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.info.register_login.utils.CodeUtil;
import com.info.register_login.utils.RedisUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SendSmsConfig
 * @Description 发送验证码到用户
 * @author: cdf
 * @Date: 2020-06-16 22:30
 **/
@Component
public class SendSmsConfig {

	private static Logger logger = Logger.getLogger(SendSmsConfig.class);

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private CodeUtil codeUtil;

	public String sendSms(String telephonenumber) {
		String code = codeUtil.generateCode();
		System.out.println(code);
		if (redisUtil.hashKey(telephonenumber)) {
			int times = (int) redisUtil.getHash(telephonenumber, "times");
			if (times < 3) {
				redisUtil.hset(telephonenumber,"code",code);
				redisUtil.haIncr(telephonenumber, "times", 1);
			} else if(times==3){
				logger.warn("key为："+telephonenumber+"用户连续超过三次");
				redisUtil.expire(telephonenumber,TimeUnit.HOURS.toHours(2));
				return JSON.toJSONString("对不起，您已连续发送超过三次，请五分钟后再试");
			}
		}else {
			redisUtil.hset(telephonenumber, "code", code, TimeUnit.SECONDS.toSeconds(300));
			redisUtil.hset(telephonenumber, "times", 0);
		}
		CommonResponse response=null;
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FdXFKaU53RaBJaq8naC", "LcQNfrwpgBjgoCis32GkGXIx3TImlv");
		IAcsClient client = new DefaultAcsClient(profile);
		CommonRequest request = new CommonRequest();
		request.setMethod(MethodType.POST);
		request.setDomain("dysmsapi.aliyuncs.com");
		request.setVersion("2017-05-25");
		request.setAction("SendSms");
		request.putQueryParameter("RegionId", "cn-hangzhou");
		request.putQueryParameter("PhoneNumbers", telephonenumber);
		request.putQueryParameter("SignName", "移动储物柜");
		request.putQueryParameter("TemplateCode", "SMS_193237932");
		request.putQueryParameter("TemplateParam", "{\"code\":" + code + "}");
		try {
			 response = client.getCommonResponse(request);
			System.out.println(response.getData());
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(response);
	}
}
