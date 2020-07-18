package com.info.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @ClassName Package
 * @Description TODO
 * @author: cdf
 * @Date: 2020-07-18 17:43
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Package {

    private Long package_id;
    private String package_from;
    private String package_to;
    private Date package_createtime;
    private Date package_finishtime;

}
