package com.info.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Cost
 * @Description 花费
 * @author: cdf
 * @Date: 2020-06-28 13:58
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors (chain = true)
public class Cost implements Serializable {

    private Long cost_id ;

    private Date cost_createtime ;

    private Date cost_finishtime ;

    private String cost_money ;
}
