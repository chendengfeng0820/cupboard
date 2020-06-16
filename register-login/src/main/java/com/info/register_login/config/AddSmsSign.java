package com.info.register_login.config;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.info.register_login.pojo.User;

/**
 * @ClassName AddSmsSign
 * @Description TODO
 * @author: cdf
 * @Date: 2020-06-13 01:06
 **/
public class AddSmsSign {

	public static void sendSms(User user) {
		String user_telephone = user.getUser_telephone();
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FdXFKaU53RaBJaq8naC", "LcQNfrwpgBjgoCis32GkGXIx3TImlv");
		IAcsClient client = new DefaultAcsClient(profile);
		CommonRequest request = new CommonRequest();
		request.setMethod(MethodType.POST);
		request.setDomain("dysmsapi.aliyuncs.com");
		request.setVersion("2017-05-25");
		request.setAction("SendSms");
		request.putQueryParameter("RegionId", "cn-hangzhou");
		request.putQueryParameter("PhoneNumbers", user_telephone);
		request.putQueryParameter("SignName", "高级营养师");
		request.putQueryParameter("TemplateCode", "SMS_183263164");
		request.putQueryParameter("TemplateParam", "{\"code\" : \"1111\"}");
		try {
			CommonResponse response = client.getCommonResponse(request);
			System.out.println(response.getData());
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}
}



