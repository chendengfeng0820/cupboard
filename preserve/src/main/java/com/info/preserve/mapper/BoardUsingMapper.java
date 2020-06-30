package com.info.preserve.mapper;

import com.info.pojo.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardUsingMapper {

    public List available();

}
