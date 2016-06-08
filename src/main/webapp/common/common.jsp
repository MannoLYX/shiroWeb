<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path_common = request.getContextPath();
	String basePath_common = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path_common+"/";
	
	request.setAttribute("home",path_common);
	request.setAttribute("baseHome",basePath_common);
%>
<script src="<%=path_common%>/static/js/jquery-1.9.0.min.js"></script>
<script src="<%=path_common%>/static/js/jBox/jquery.jBox-2.3.min.js"></script>
<script src="<%=path_common%>/static/js/jBox/jquery.jBox-zh-CN.min.js"></script>
<script src="<%=path_common%>/static/js/alerts/jquery.alerts.js"></script>

<link rel="stylesheet" href="<%=path_common%>/static/js/alerts/jquery.alerts.css" type="text/css"></link>
<link rel="stylesheet" href="<%=path_common%>/static/js/jBox/jbox.css" type="text/css"></link>

<script type="text/javascript">
var home = "${home}";
var baseHome = "${baseHome}";
</script>