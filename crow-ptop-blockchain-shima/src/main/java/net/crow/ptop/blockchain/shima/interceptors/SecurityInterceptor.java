package net.crow.ptop.blockchain.shima.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import net.crow.ptop.blockchain.shima.dto.user.UserDto;
import net.crow.ptop.blockchain.shima.util.SessionUtil;

@Component
public class SecurityInterceptor implements HandlerInterceptor{

	private Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
		
		UserDto userDto = SessionUtil.getAdminUser(httpServletRequest);
		if(userDto == null){
			logger.debug("用户未登录，无操作权限，请登录!");
			return false;
		}
		return true;
	}
}
