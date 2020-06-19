package com.info.register_login.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName User
 * @Description TODO
 * @author: cdf
 * @Date: 2020-06-12 15:41
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {

	private int user_id;
	private String user_telephone;
	private String user_password;
	private String user_username;
	private String user_faceinfo;
	private Date user_createtime;

}
