<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <link rel="stylesheet" type="text/css" href="./js/main.css" />
	<link rel="stylesheet" type="text/css" href="./css/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="./css/icon.css">
	<link rel="stylesheet" type="text/css" href="./css/button.css">
	<style type="text/css">
		    body {  
    font: normal 11px auto "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;  
    color: #4f6b72;  
}  
  
a {  
    color: #c75f3e;  
}  

#mytable {  
    width: 700px;  
    padding: 0;  
    margin: 0;  
}  
  
caption {  
    padding: 0 0 5px 0;  
    width: 700px;      
    font: italic 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;  
    text-align: right;  
}  
  
th {  
    font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;  
    color: #4f6b72;  
    border-left: 1px solid #C1DAD7;  
    border-right: 1px solid #C1DAD7;  
    border-bottom: 1px solid #C1DAD7;  
    border-top: 1px solid #C1DAD7;  
    letter-spacing: 2px;  
    text-transform: uppercase;  
    text-align: center;  
    padding: 6px 6px 6px 12px;  
    background: #CAE8EA url(images/bg_header.jpg) no-repeat;  
}  
  
th.nobg {  
    border-top: 0;  
    border-left: 0;  
    border-right: 1px solid #C1DAD7;  
    background: none;  
}  
  
td {  
    border-left: 1px solid #C1DAD7;  
    border-right: 1px solid #C1DAD7;  
    border-top: 1px solid #C1DAD7;
    border-bottom: 1px solid #C1DAD7;  
    background: #fff;  
    padding: 6px 6px 6px 12px;  
    color: #4f6b72;  
}  
  
  
td.alt {  
    background: #F5FAFA;  
    color: #797268;  
}  
  
th.spec {  
    border-left: 1px solid #C1DAD7;  
    border-top: 0;  
    background: #fff url(images/bullet1.gif) no-repeat;  
    font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;  
}  
  
th.specalt {  
    border-left: 1px solid #C1DAD7;  
    border-top: 0;  
    background: #f5fafa url(images/bullet2.gif) no-repeat;  
    font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;  
    color: #797268;  
}
	</style>
	<head>
    <script type="text/javascript" src="./js/jquery.js"></script>
    <script type="text/javascript" src="./js/jquery.fancybox-1.3.1.pack.js"></script>
    <script type="text/javascript" src="./js/easy-ui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="./js/main/login.js"></script>
	<script type="text/javascript"> 
$(document).ready(function() {
	$('#login').click(function(){
	   $.ajax({
	         url: "${pageContext.request.contextPath}/admin/login",
	         type: "POST",
	         data:$('#ff').serialize(),
	         async:false,
	         success: function(data) {
	         if(data!=""){
	           var obj = eval("("+data+")");
	           alert(obj);
	           $('#vali').val("");
	           flushValidateCode($("#rand")[0]);
	          }else{
	              window.parent.window.location.href ="/main.jsp";
	          }
	       }
	    });
	});
});
</script>
	</head>
	<body>
		<div  align="left">
		<font color="red" style="position: relative;left: 50" id="message"></font>
	    <form id="ff">
	    	<table>
	    		<tr>
	    			<td>用户名:</td>  
	    			<td><input class="easyui-validatebox textbox" type="text" name="adminName" ></input></td>
	    		</tr>
	    		<tr>
	    			<td>密码:</td>
	    			<td><input class="easyui-validatebox textbox" type="password" name="adminPassword" ></input></td>
	    		</tr>
	    		<tr>
	    			<td>验证码:</td>
	    			<td><input class="easyui-validatebox textbox" id="vali" type="text" name="rand" ></input></td>
	    		</tr>
	    		<tr>
						<td colspan="2">
							<img id="rand" src="/admin/rand" onclick="flushValidateCode(this)"
								style="cursor: pointer;width: 100%;height: 30" />
						</td>
				</tr>
				<tr>
						<td colspan="2" align="center">
						    <a id="login" href="#" class="button button-primary button-pill button-tiny">登陆</a>
						</td>
					</tr>
	    	</table>
	    </form>
	    </div>
	</body>
</html>

