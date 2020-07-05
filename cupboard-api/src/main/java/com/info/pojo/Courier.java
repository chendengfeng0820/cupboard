package com.info.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName Courier
 * @Description 快递员
 * @author: cdf
 * @Date: 2020-07-05 14:33
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Courier {

    private Long courier_id;

    private String courier_name;

    private String courier_password;

    private String courier_telephone;

    private String courier_area;

}
