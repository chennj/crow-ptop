package net.crow.ptop.blockchain.shima.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import net.crow.ptop.blockchain.shima.entity.UserEntity;

@Mapper
@Component
public interface UserDao {

	void addUser(UserEntity userEntity);

    UserEntity queryUserByUserName(@Param("userName") String userName);

    void updateUser(UserEntity userEntity);

    long queryUserSize();
}
