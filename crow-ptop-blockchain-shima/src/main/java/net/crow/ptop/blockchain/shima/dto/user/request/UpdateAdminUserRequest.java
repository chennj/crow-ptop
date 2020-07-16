package net.crow.ptop.blockchain.shima.dto.user.request;

import net.crow.ptop.blockchain.shima.dto.user.UserDto;

public class UpdateAdminUserRequest {

	private UserDto userDto;
	
	public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
