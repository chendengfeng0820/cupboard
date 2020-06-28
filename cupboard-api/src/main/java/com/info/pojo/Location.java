package com.info.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName Location
 * @Description TODO
 * @author: cdf
 * @Date: 2020-06-28 14:12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Location {

    private String provice ;

    private String city ;

    private String area ;
}
