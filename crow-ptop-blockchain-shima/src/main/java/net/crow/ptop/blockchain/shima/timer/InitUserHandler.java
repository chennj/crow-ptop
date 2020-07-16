package net.crow.ptop.blockchain.shima.timer;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import net.crow.ptop.blockchain.shima.dto.user.UserDto;
import net.crow.ptop.blockchain.shima.service.UserService;

/**
 * 初始化用户，每次启动系统时，会校验数据库中是否存在用户。
 * 如果不存在用户，自动生成一个用户，并把用户写入外部文件供系统使用者查看生成的用户名与密码。
 * 
 * @author chenn
 *
 */
public class InitUserHandler {

	String DEFAULT_USER_NAME = "admin_chennj";
    String DEFAULT_PASSWORD = "Uskini123!";
    
    @Autowired
    private UserService userService;
    
    @PostConstruct
    private void startThread() throws IOException {
        long userSize = userService.queryUserSize();
        if(userSize>0){
            return;
        }

        UserDto userDto = new UserDto();
        userDto.setUserName(DEFAULT_USER_NAME);
        userDto.setPassword(DEFAULT_PASSWORD);
        userService.addUser(userDto);
    }

}
