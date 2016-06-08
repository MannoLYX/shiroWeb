package com.lyx.learn.shiro.token;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

import com.lyx.learn.entity.User;

/**
 * 文件名：UserToken.java
 * 说明：
 * 作者： 刘育新
 * 创建时间：2016-6-8
 * 版权所有：LYX
 */
public class UserToken implements RememberMeAuthenticationToken,HostAuthenticationToken
{
	private static final long serialVersionUID = 7044109370205363902L;
	
	private User user;
	private boolean isRememberMe = false;
	private String host;
	
	
	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public void setRememberMe(boolean isRememberMe)
	{
		this.isRememberMe = isRememberMe;
	}
	
	public void setHost(String host)
	{
		this.host = host;
	}

	//---------------------
	@Override
	public Object getPrincipal()
	{
		return this.user;
	}

	@Override
	public Object getCredentials()
	{
		return this.user;
	}

	@Override
	public boolean isRememberMe()
	{
		return this.isRememberMe;
	}

	@Override
	public String getHost()
	{
		return this.host;
	}
	
}
