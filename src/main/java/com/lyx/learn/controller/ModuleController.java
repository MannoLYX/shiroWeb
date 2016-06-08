package com.lyx.learn.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 文件名：ModuleController.java
 * 说明：
 * 作者： 刘育新
 * 创建时间：2016-6-8
 * 版权所有：LYX
 */
@Controller
@RequestMapping("/module")
public class ModuleController
{
	/**
	 * @param request
	 * @return
	 */
	@RequestMapping("/toConfig")
	@RequiresPermissions("配置管理")
	public String toConfig(HttpServletRequest request)
	{
		return "view/config";
	}
	
	/**
	 * @param request
	 * @return
	 */
	@RequestMapping("/toSystem")
	@RequiresPermissions("系统管理")
	public String toSystem(HttpServletRequest request)
	{
		return "view/system";
	}
	
	/**
	 * @param request
	 * @return
	 */
	@RequestMapping("/toResource")
	@RequiresPermissions("资源管理")
	public String toResource(HttpServletRequest request)
	{
		return "view/resource";
	}
	
}
