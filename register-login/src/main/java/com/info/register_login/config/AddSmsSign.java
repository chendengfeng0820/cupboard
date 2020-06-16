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


	public static void main(String[] args) {




	}
//		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FdXFKaU53RaBJaq8naC", "LcQNfrwpgBjgoCis32GkGXIx3TImlv");
//		IAcsClient client = new DefaultAcsClient(profile);
//		CommonRequest request = new CommonRequest();
//		request.setMethod(MethodType.POST);
//		request.setDomain("dysmsapi.aliyuncs.com");
//		request.setVersion("2017-05-25");
//		request.setAction("SendSms");
//		request.putQueryParameter("RegionId", "cn-hangzhou");
//		request.putQueryParameter("PhoneNumbers", "17695754820");
//		request.putQueryParameter("SignName", "付艳玲小仙女专属柜子");
//		request.putQueryParameter("TemplateCode", "SMS_193230139");
////		request.putQueryParameter("TemplateCode", "SMS_183263164");
//		request.putQueryParameter("TemplateParam", "{\"code\" : \"Iloveyou\"}");
//		try {
//			CommonResponse response = client.getCommonResponse(request);
//			System.out.println(response.getData());
//		} catch (ServerException e) {
//			e.printStackTrace();
//		} catch (ClientException e) {
//			e.printStackTrace();
//		}
//	}
}



