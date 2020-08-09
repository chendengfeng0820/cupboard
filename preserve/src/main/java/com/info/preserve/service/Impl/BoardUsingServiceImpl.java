package com.info.preserve.service.Impl;

import com.info.pojo.Board;
import com.info.preserve.mapper.BoardUsingMapper;
import com.info.preserve.service.BoardUsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName BoardUsingServiceImpl
 * @Description TODO
 * @author: cdf
 * @Date: 2020-06-28 23:19
 **/
@Service
public class BoardUsingServiceImpl implements BoardUsingService {

    @Autowired
    private BoardUsingMapper boardUsingMapper;


    @Override
    public List available() {
        return boardUsingMapper.available();
    }

    @Override
    public String getlocation(Long board_id) {
        return boardUsingMapper.getlocation(board_id);
    }

    @Override
    public String getCoordinate(Long board_id) {
        return boardUsingMapper.getCoordinate(board_id);
    }
}
