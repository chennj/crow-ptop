package net.crow.ptop.blockchain.shima.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

import net.crow.ptop.blockchain.shima.dto.common.ServiceResult;
import net.crow.ptop.blockchain.shima.dto.user.UserApiRoute;
import net.crow.ptop.blockchain.shima.dto.user.UserDto;
import net.crow.ptop.blockchain.shima.dto.user.request.LoginRequest;
import net.crow.ptop.blockchain.shima.dto.user.request.QueryLoginUserInfoRequest;
import net.crow.ptop.blockchain.shima.dto.user.request.UserExitRequest;
import net.crow.ptop.blockchain.shima.dto.user.response.LoginResponse;
import net.crow.ptop.blockchain.shima.dto.user.response.QueryLoginUserInfoResponse;
import net.crow.ptop.blockchain.shima.dto.user.response.UserExitResponse;
import net.crow.ptop.blockchain.shima.service.UserService;
import net.crow.ptop.blockchain.shima.util.SessionUtil;

@Controller
@RequestMapping
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
    private UserService userService;

	/**
     * 登录
     */
    @ResponseBody
    @RequestMapping(value = UserApiRoute.LOGIN,method={RequestMethod.GET,RequestMethod.POST})
    public ServiceResult<LoginResponse> login(@RequestBody LoginRequest request, HttpServletRequest httpServletRequest){
        try {
            if(request.getUserDto() == null || Strings.isNullOrEmpty(request.getUserDto().getUserName())){
                return ServiceResult.createFailServiceResult("登录失败，用户名不能为空");
            }
            if(Strings.isNullOrEmpty(request.getUserDto().getPassword())){
                return ServiceResult.createFailServiceResult("登录失败，密码不能为空");
            }
            UserDto userDto = userService.queryUserByUserName(request.getUserDto().getUserName());
            if(userDto == null){
                return ServiceResult.createFailServiceResult("登录失败，请检查用户名与密码");
            }
            if(!userDto.getPassword().equals(request.getUserDto().getPassword())){
                return ServiceResult.createFailServiceResult("登录失败，请检查用户名与密码");
            }
            LoginResponse response = new LoginResponse();
            LoginResponse.LoginUserDto loginUserDto = new LoginResponse.LoginUserDto();
            loginUserDto.setUserId(userDto.getUserId());
            loginUserDto.setUserName(userDto.getUserName());
            response.setUserDto(loginUserDto);
            SessionUtil.saveAdminUser(httpServletRequest,userDto);
            return ServiceResult.createSuccessServiceResult("登录成功",response);
        } catch (Exception e){
            String message = "登录失败";
            logger.error(message,e);
            return ServiceResult.createFailServiceResult(message);
        }
    }
    
    /**
     * 获取登录信息
     */
    @ResponseBody
    @RequestMapping(value = UserApiRoute.QUERY_LOGIN_USER_INFO,method={RequestMethod.GET,RequestMethod.POST})
    public ServiceResult<QueryLoginUserInfoResponse> queryLoginUserInfo(@RequestBody QueryLoginUserInfoRequest request, HttpServletRequest httpServletRequest){
        try {
            UserDto userDto = SessionUtil.getAdminUser(httpServletRequest);
            if(userDto == null){
                return ServiceResult.createFailServiceResult("获取登录信息失败，用户未登录。");
            }
            QueryLoginUserInfoResponse response = new QueryLoginUserInfoResponse();
            QueryLoginUserInfoResponse.LoginUserDto loginUserDto = new QueryLoginUserInfoResponse.LoginUserDto();
            loginUserDto.setUserId(userDto.getUserId());
            loginUserDto.setUserName(userDto.getUserName());
            response.setUserDto(loginUserDto);
            return ServiceResult.createSuccessServiceResult("获取登录信息成功",response);
        } catch (Exception e){
            String message = "获取登录信息失败";
            logger.error(message,e);
            return ServiceResult.createFailServiceResult(message);
        }
    }
    
    /**
     * 用户退出
     */
    @ResponseBody
    @RequestMapping(value = UserApiRoute.USER_EXIT,method={RequestMethod.GET,RequestMethod.POST})
    public ServiceResult<UserExitResponse> userExit(@RequestBody UserExitRequest request, HttpServletRequest httpServletRequest){
        try {
            SessionUtil.clearAdminUser(httpServletRequest);
            UserExitResponse response = new UserExitResponse();
            return ServiceResult.createSuccessServiceResult("用户退出成功",response);
        } catch (Exception e){
            String message = "用户退出失败";
            logger.error(message,e);
            return ServiceResult.createFailServiceResult(message);
        }
    }
}
