package com.info.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName Board
 * @Description TODO
 * @author: cdf
 * @Date: 2020-06-28 23:15
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Board {

    private Long board_id ;

    private int using;  //是否正在使用

    private Location location ;
}
