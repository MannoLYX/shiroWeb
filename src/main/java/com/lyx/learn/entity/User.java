package com.lyx.learn.entity;

/**
 * 文件名：User.java
 * 说明：
 * 作者： 刘育新
 * 创建时间：2016-6-8
 * 版权所有：LYX
 */
public class User
{
	private Long id;
	private String userName;
	private String password;
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
}
