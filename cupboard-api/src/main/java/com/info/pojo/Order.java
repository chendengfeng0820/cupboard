package com.info.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.xml.soap.SAAJResult;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Order
 * @Description 订单
 * @author: cdf
 * @Date: 2020-06-28 13:51
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors (chain = true)
public class Order implements Serializable {

    private Long order_id;

    private String order_telephone ;

    private String order_username ;

    private Date order_createtime ;

    private String order_location ;
}
