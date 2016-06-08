package com.lyx.learn.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.lyx.learn.entity.User;
import com.lyx.learn.shiro.token.UserToken;

public class UserUtil
{
	private static final Logger LOGGER = Logger.getLogger(UserUtil.class);
	
	
	
	
	
	/**
	 * 从shiro中获取用户信息
	 * @return
	 */
	public static User getUserInfoByShiro()
	{
		SecurityUtils.getSubject().getPrincipal();
		
		return null;
	}
	
	
	/**
	 * 根据key获取session的value值
	 * @param key
	 * @return
	 */
	public static Object getSessionValue(String key)
	{
		Subject subject = SecurityUtils.getSubject();
		return subject.getSession().getAttribute(key);
	}
	
	/**
	 * 设置session的值
	 * @param key
	 * @param Value
	 */
	public static void setSession(Object key , Object value)
	{
		Subject currentSubject = SecurityUtils.getSubject();
		
		if(null != currentSubject)
		{
			Session session = currentSubject.getSession();
			LOGGER.info("session默认的超时时间为："+session.getTimeout()+"毫秒！");
			
			if(null != session)
			{
				session.setAttribute(key, value);
			}
		}
		
	}
	
	
	/**
	 * 打印用户信息
	 * @param userToken
	 * @return
	 */
	public static String printUserInfo(UserToken userToken)
	{
		if(null == userToken)
		{
			return "登录用户信息为空！";
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("登录用户信息如下：");
		sb.append(";用户姓名："+userToken.getUser().getUserName());
		sb.append(";用户密码："+userToken.getUser().getPassword());
		sb.append(";用户ID："+userToken.getUser().getId());
		sb.append(";登录ip："+userToken.getHost());
		sb.append(";是否记住密码："+userToken.isRememberMe());
		
		return sb.toString();
	}

}
