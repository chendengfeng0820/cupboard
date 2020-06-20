package com.info.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName TelephoneNumber
 * @Description TODO
 * @author: cdf
 * @Date: 2020-06-16 22:28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelephoneNumber implements Serializable {

	private String telephonenumber;
}
