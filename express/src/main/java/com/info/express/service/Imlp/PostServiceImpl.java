package com.info.express.service.Imlp;

import com.info.express.mapper.PostMapper;
import com.info.express.service.PostService;
import com.info.pojo.Board;
import com.info.pojo.Courier;
import com.info.pojo.Package;
import com.info.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName PostServiceImpl
 * @Description TODO
 * @author: cdf
 * @Date: 2020-07-18 18:07
 **/
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    public PostMapper postMapper;

    @Override
    public Long insertPackage(Package pack) {
        return postMapper.insertPackage(pack);
    }


    @Override
    public User getUserInfo(Long user_id) {
        return postMapper.getUserInfo(user_id);
    }

    @Override
    public Courier getCourierInfo(Long courier_id) {
        return postMapper.getCourierInfo(courier_id);
    }

    @Override
    public Board getBoardInfo(Long board_id) {
        return postMapper.getBoardInfo(board_id);
    }

    @Override
    public void finishPackage(Date finishtime) {
        postMapper.finishPackage(finishtime);
    }

    @Override
    public void insertUser_Package(Long user_id, Long package_id) {
        postMapper.insertUser_Package(user_id,package_id);
    }

    @Override
    public void insertCourier_Package(Long courier_id, Long package_id) {
        postMapper.insertCourier_Package(courier_id, package_id);
    }

    @Override
    public void insertCourier_Board(Long courier_id, Long board_id) {
        postMapper.insertCourier_Board(courier_id, board_id);
    }
}
