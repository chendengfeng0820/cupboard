package com.info.register_login.utils;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @ClassName CodeUtil
 * @Description 生成四位随机验证码
 * @author: cdf
 * @Date: 2020-06-16 23:33
 **/
@Component
public class CodeUtil {
	private static Logger logger = Logger.getLogger(CodeUtil.class);

	public String generateCode() {
		Random random = new Random();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			stringBuilder = stringBuilder.append(random.nextInt(9));
		}
		logger.info("LTAI4FdXFKaU53RaBJaq8naC  LcQNfrwpgBjgoCis32GkGXIx3TImlv");
		return JSON.toJSONString(stringBuilder);
	}
}
