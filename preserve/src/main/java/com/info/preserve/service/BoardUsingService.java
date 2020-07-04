package com.info.preserve.service;

import com.info.pojo.Board;

import java.util.List;

public interface BoardUsingService {

    public List available();

    public String getlocation(Long board_id);

}
