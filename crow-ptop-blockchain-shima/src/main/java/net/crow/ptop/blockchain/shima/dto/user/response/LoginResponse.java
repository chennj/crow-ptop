package net.crow.ptop.blockchain.shima.dto.user.response;

public class LoginResponse {

	private LoginUserDto userDto;
	
	public static class LoginUserDto {
        private int userId;
        private String userName;
        
        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
	}
	
	public LoginUserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(LoginUserDto userDto) {
        this.userDto = userDto;
    }
}
