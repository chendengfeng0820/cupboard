package com.info.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName AllBean
 * @Description TODO
 * @author: cdf
 * @Date: 2020-07-05 00:56
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllBean {

    private Long order_cost_id;
    private Long user_order_id;
    private Long user_table_id;
}
