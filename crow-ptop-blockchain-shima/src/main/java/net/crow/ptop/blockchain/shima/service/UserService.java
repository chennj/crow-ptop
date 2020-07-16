package net.crow.ptop.blockchain.shima.service;

import net.crow.ptop.blockchain.shima.dto.user.UserDto;

public interface UserService {

	long queryUserSize();
    UserDto queryUserByUserName(String userName);
    void addUser(UserDto userDto);
    void updateUser(UserDto userDto);
}
