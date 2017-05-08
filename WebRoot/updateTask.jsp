<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>音乐推荐</title>
		<link rel="stylesheet" type="text/css" href="../css/icon.css">
		<link rel="stylesheet" type="text/css" href="../css/button.css">
		<link rel="stylesheet" type="text/css" href="../js/fancybox.css" />
		<link rel="stylesheet" type="text/css" href="../css/default/easyui.css">
		<style type="text/css">
body {
	font: normal 11px auto "Trebuchet MS", Verdana, Arial, Helvetica,
		sans-serif;
	color: #4f6b72;
	background: url(../css/images/b2.jpg) no-repeat center center;
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

table {
	margin-bottom: 45px;
	margin-top: 25px;
	border: 1px solid #E4E4E4;
	background: #F7F7F7;
	padding: 25px 15px 25px 10px;
	font: 12px Georgia, "Times New Roman", Times, serif;
	color: #888;
	text-shadow: 1px 1px 1px #FFF;
}
</style>
		<script type="text/javascript" src="../js/jquery.js"></script>
		<script type="text/javascript" src="../js/jquery.fancybox-1.3.1.pack.js"></script>
		<script type="text/javascript" src="../js/easy-ui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="../js/main/updateTask.js"></script>
		<script type="text/javascript">
	$(document).ready(function() {
		$("#hour").val("${requestScope.job.hour}");
		$("#min").val("${requestScope.job.min}");
		$("#isSend").val("${requestScope.job.isSend}");
		
   $("#update").click( function () {
	    $.ajax({
		    url: "${pageContext.request.contextPath}/user/updateTask?userId="+"${requestScope.job.id}",
		    type: "POST",
		    data:$('#form').serialize(),
		    success: function(data) {
		        if(data=='"success"'){
		            alert("修改成功");
		        }else{
		            alert("修改失败");
		        }
		    }
		});
   });
});
</script>
		<body>
			<h1 align="center" style="padding-top: 250">
				欢迎使用音乐推荐
			</h1>
			<form id="form">
				<table align="center" width="350px" height="116px" bgcolor="#FFFAFA">
					<tr>
						<td>
							<font style="font-weight: bold">电子邮件:</font>
						</td>
						<td>
							<input class="easyui-validatebox textbox" type="text" id="email" data-options="missingMessage:'请输入邮箱号',invalidMessage:'邮箱格式不正确',required:true,validType:'email'"
							 value="${requestScope.job.email}"	name="email"></input>
						</td>
					</tr>
					<tr>
						<td>
							<font style="font-weight: bold">任务名:</font>
						</td>
						<td>
							<input class="easyui-validatebox textbox" type="text" id="jobName" data-options="missingMessage:'请输入任务名',required:true"
							 value="${requestScope.job.jobName}"	name="jobName"></input>
						</td>
					</tr>
					<tr>
						<td>
							<font style="font-weight: bold">发送时间:</font>
						</td>
						<td>
							<select class="textbox" id="hour" name="hour"></select>
							:
							<select class="textbox" id="min" name="min"></select>
						</td>
					</tr>
					<tr>
						<td>
							<font style="font-weight: bold">是否发送:</font>
						</td>
						<td>
							<select class="textbox" id="isSend" name="isSend">
							    <option value="0">是</option>
							    <option value="1">否</option>
							</select>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<a id="update" href="#"
								class="button button-primary button-pill button-tiny">修改</a>
						</td>
					</tr>
				</table>
			</form>
		</body>
</html>