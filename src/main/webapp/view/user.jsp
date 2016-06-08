<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>用户界面</title>
</head>

<body>
	<h1> ${user.userName},欢迎您！</h1>
	<br>
	
	<ul>
		<shiro:hasPermission name="配置管理">
			<li>
				<a href="module/toConfig" target="_blank">配置管理</a>
			</li>
		</shiro:hasPermission>
		<shiro:hasPermission name="资源管理">
			<li>
				<a href="module/toResource" target="_blank">资源管理</a>
			</li>
		</shiro:hasPermission>
		<shiro:hasPermission name="系统管理">
			<li>
				<a href="module/toSystem" target="_blank">系统管理</a>
			</li>
		</shiro:hasPermission>
	</ul>
</body>
</html>
