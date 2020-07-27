package com.info.preserve.mapper;

import com.info.pojo.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardUsingMapper {

    //查找数据库中状态为 可用柜子
    public List<Board> available();

    //查看柜子的位置
    public String getlocation(Long board_id);

}
