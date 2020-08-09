package com.info.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName User
 * @Description 用户
 * @author: cdf
 * @Date: 2020-06-12 15:41
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)  //链式写法 .set .set
public class User implements Serializable {

	private Long user_id;
	private String user_telephone;
	private String user_password;
	private String user_username;
	private String user_faceinfo;
	private String user_coordinate;
	private Date user_createtime;


}
