package com.lyx.learn.common.exception;

/**
 * 文件名：BizException.java
 * 说明：自定义业务异常类
 * 作者： 刘育新
 * 创建时间：2016-6-8
 * 版权所有：LYX
 */
public class BizException extends Exception
{
	private static final long serialVersionUID = -418127976932890940L;

	public BizException()
	{
		super();
	}
	
	public BizException(String message)
	{
		super(message);
	}
	
	public BizException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public BizException(Throwable cause)
	{
		super(cause);
	}
}
