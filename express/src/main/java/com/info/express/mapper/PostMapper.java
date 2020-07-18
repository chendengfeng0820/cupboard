package com.info.express.mapper;

import com.info.pojo.Board;
import com.info.pojo.Courier;
import com.info.pojo.Package;
import com.info.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Mapper
@Repository
public interface PostMapper {

    public Long insertPackage(Package pack);

    public User getUserInfo(Long user_id);

    public Board getBoardInfo(Long board_id);

    public Courier getCourierInfo(Long courier_id);

    public void finishPackage(Date finishtime);

    public void insertUser_Package(@Param("user_id")Long user_id, @Param("package_id")Long package_id);

    public void insertCourier_Package(@Param("courier_id")Long courier_id,@Param("package_id")Long package_id);

    public void insertCourier_Board(@Param("courier_id")Long courier_id,@Param("board_id")Long board_id);
}
