package com.info.preserve.utils;

import org.springframework.stereotype.Component;

/**
 * @ClassName TimeShift
 * @Description 将秒转换为分钟秒(时间转换类)
 * @author: cdf
 * @Date: 2020-06-30 22:50
 **/
@Component
public class TimeShift {

	public String conversion(Long ttl){
		if (ttl > 3600) {
			ttl = ttl- 3600 ;
			Long minutes = ttl / 60;
			Long seconds = ttl % 60;
			return "1小时" + minutes + "分" + seconds + "秒";
		}else {
			Long minutes = ttl / 60;
			Long seconds = ttl % 60;
			return minutes + "分" + seconds + "秒";
		}
	}
}
