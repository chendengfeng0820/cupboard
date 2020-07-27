package com.info.preserve.service;

import com.info.pojo.Board;

import java.util.List;

public interface BoardUsingService {

    //查找数据库中状态为 可用柜子
    public List<Board> available();

    //查看柜子的位置
    public String getlocation(Long board_id);

}
