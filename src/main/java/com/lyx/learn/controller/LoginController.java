package com.lyx.learn.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lyx.learn.common.exception.BizException;
import com.lyx.learn.entity.User;

/**
 * 文件名：LoginController.java
 * 说明：
 * 作者： 刘育新
 * 创建时间：2016-6-7
 * 版权所有：LYX
 */
@Controller
@RequestMapping("main")
public class LoginController
{
	private static final Logger LOGGER = Logger.getLogger(LoginController.class);
	
	/**
	 * @param request
	 * @return
	 */
	@RequestMapping("/toLogin")
	public String toLogin(HttpServletRequest request)
	{
		return "view/login";
	}
	
	/**
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request,User user)
	{
		
		if(null != user)
		{
			//验证用户登录信息是否正确
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
			token.setRememberMe(true);
			
			Subject subject = SecurityUtils.getSubject();
			//由subject.login(token)进入MyRealm的doGetAuthenticationInfo方法进行身份验证
			subject.login(token);
		
			/*
			 * subject.isAuthenticated()表示用户进行了身份验证登录的，即有Subject.login进行了登录；
			 * subject.isRemembered()：表示用户是通过记住我登录的，此时可能并不是真正的你（如你的朋友使用你的电脑，或者你的cookie被窃取）在访问的；
			 * 且两者二选一，即subject.isAuthenticated()==true，则subject.isRemembered()==false；反之一样。
			 */
			
			//即通过身份验证登录成功或是记住我登录成功都可
			if(subject.isAuthenticated() == true || subject.isRemembered() == true)
			{
				request.setAttribute("user",user);
				return "view/user";
			}
			/*Session session = subject.getSession();*/
		}
		
		BizException exception =  new BizException("登录用户信息为空！");
		LOGGER.info(exception.getMessage());
		
		return "view/login";
	}
}
