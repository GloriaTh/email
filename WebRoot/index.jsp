<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>音乐推荐</title>
		<link rel="stylesheet" type="text/css" href="/css/icon.css">
		<link rel="stylesheet" type="text/css" href="/css/button.css">
		<link rel="stylesheet" type="text/css" href="/js/fancybox.css"/>
		<style type="text/css">
body {
	font: normal 11px auto "Trebuchet MS", Verdana, Arial, Helvetica,
		sans-serif;
	color: #4f6b72;
	background: url(/css/images/b2.jpg) no-repeat center center;
}

h1 {
	color: #fff;
	font-weight: 100;
	font-size: 40px;
	margin: 0 auto;
	padding-bottom: 20px;
	border-bottom: 2px solid rgba(255, 255, 255, .2);
	max-width: 620px;
	line-height: 1.25;
}

h3 {
	color: rgba(255, 255, 255, .8);
	font-size: 16px;
	margin-bottom: 45px;
	margin-top: 25px;
	font-weight: 400;
}
</style>
		<script type="text/javascript" src="/js/jquery.js"></script>
		<script type="text/javascript" src="/js/jquery.fancybox-1.3.1.pack.js"></script>
		<script type="text/javascript" src="/js/main/index.js"></script>
		<script type="text/javascript">
	
</script>
		<body>
			<c:if test="${sessionScope.admin.adminName ==null}">
				<h1 align="center" style="padding-top: 250">
					欢迎使用音乐推荐
				</h1>
				<h3 align="center">
					您现在尚未登陆
				</h3>
				<div align="center">
					<a
						class="button button-glow button-rounded button-raised button-primary"
						id="login">登陆</a>
				</div>
			</c:if>
			<c:if test="${sessionScope.admin.adminName !=null}">
				<h1 align="center" style="padding-top: 250">
					欢迎回来
				</h1>
				<h3 align="center">
					您已经登录,${sessionScope.admin.adminName}
				</h3>
				<div align="center">
					<a href="/main.jsp"
						class="button button-glow button-rounded button-raised button-primary"
						id="entry">进入</a>
				</div>
			</c:if>
		</body>
</html>