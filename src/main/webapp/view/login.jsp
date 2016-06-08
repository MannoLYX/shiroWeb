<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户登录</title>
<%@ include file="../common/common.jsp" %>
<link rel="shortcut icon" href="${home}/static/images/star.png" />
<link rel="stylesheet" href="${home}/static/login/login.css" type="text/css"></link>
<script type="text/javascript" src="${home}/static/login/prefixfree.min.js"></script></head>
<body>
	<div class="content">
		<form action="${home}/main/login" method="post" class="login-form">
			<div class="username">
				<input type="text" name="userName" placeholder="username" autocomplete="on" /> <span class="user-icon icon">u</span>
			</div>
			<div class="password">
				<input type="password" name="password" placeholder="password" /> <span class="password-icon icon">p</span>
			</div>
			<div class="account-control">
				<input type="checkbox" name="rememberMe" id="rememberMe" value="true" checked="checked" />
				<label for="Remember me" data-on="c" class="check"></label> 
				<label for="Remember me" class="info">Remember me</label>
				<button type="submit">Login</button>
			</div>
			<p class="not-registered">
				没有注册<a href="#"> 立即注册 </a>
			</p>
		</form>
	</div>
</body>
</html>
