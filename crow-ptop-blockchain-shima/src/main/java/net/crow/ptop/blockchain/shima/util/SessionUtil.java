package net.crow.ptop.blockchain.shima.util;

import javax.servlet.http.HttpServletRequest;

import net.crow.ptop.blockchain.shima.dto.user.UserDto;

public class SessionUtil {

	private final static String ADMIN_USER_KEY = "ADMIN_USER";

    public static UserDto getAdminUser(HttpServletRequest httpServletRequest){
        UserDto userDto = (UserDto) httpServletRequest.getSession().getAttribute(ADMIN_USER_KEY);
        return userDto;
    }

    public static void saveAdminUser(HttpServletRequest httpServletRequest, UserDto userDto) {
        httpServletRequest.getSession().setAttribute(ADMIN_USER_KEY,userDto);
    }

    public static void clearAdminUser(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().removeAttribute(ADMIN_USER_KEY);
        httpServletRequest.getSession().invalidate();
    }
}
