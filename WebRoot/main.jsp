<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<link rel="stylesheet" type="text/css" href="./js/main.css" />
	<link rel="stylesheet" type="text/css" href="./css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="./css/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="./css/icon.css">
	<link rel="stylesheet" type="text/css" href="./js/fancybox.css" />
	<link rel="stylesheet" type="text/css" href="./css/button.css" />
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
		<script type="text/javascript" language="javascript"
			src="/js/jquery.js"></script>
		<script type="text/javascript" src="./js/jquery-ui.js"></script>
		<script type="text/javascript" src="./js/easy-ui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="./js/jquery.fancybox-1.3.1.pack.js"></script>
		<script type="text/javascript" src="./js/main/main.js"></script>
	<script type="text/javascript">
$(document).ready(function() {
	function getTask(thisp){
		$.ajax({
			url: "${pageContext.request.contextPath}/user/getAllTask?thisPage="+thisp,
			type: "POST",
			success: function(data) {
			var obj = eval("("+data+")"); 
			$("#tb").empty();
			thisp = obj.thispage;
			page = obj.countrow;
			$.each(obj.list, function(index, value){
				$("#tb").append(
						"<tr align='center' style='cursor: pointer'><td><input class='selete' type='checkbox' value='"+value.userId+"'></td>"+
						"<td name='"+value.userId+"' class='Change'><a>"+value.userId+"</a></td>"+
						"<td name='"+value.userId+"' class='Change'><a>"+value.jobName+"</a></td>"+
						"<td name='"+value.userId+"' class='Change'>"+value.email+"</td>"+
						"<td name='"+value.userId+"' class='Change'>"+value.job+"</td>"+
						"<td name='"+value.userId+"' class='Change'>"+value.isSend+"</td></tr>"
				);
			});
			prepage(thisp,page);
		}
		});
	}
	
	getTask(1);
    
    var  prepage = function prepage(thisp,page){
        $("#pp").pagination({
			total:page,
			pageNumber:thisp,
			pageSize:15,
			layout:['sep','first','prev','links','next','last','sep'],
			loading:false,
			showPageList:true,
			showRefresh:true,
			displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
			buttons: [{
				iconCls:'icon-add',
				handler:function(){
				    $.fancybox({
			            width:390,
						height:190,
			            href: "${pageContext.request.contextPath}/addTask.jsp",
			            onClosed:function(){
				            getTask(thisp);
				        }
			        });
				}
				},{
					iconCls:'icon-reload',
					handler:function(){
					    getTask(thisp);
						$('#pp').pagination('select');
					}
				}],
			onSelectPage:function(pageNumber, pageSize){
        	        getTask(pageNumber);
			},
		});
   }
   
   $(".Change").live("click", function() {
        var id = $(this).attr("name");
        var thisPage = $('#pp').pagination('options').pageNumber;
		$.fancybox({
            width:390,
			height:190,
            href: "${pageContext.request.contextPath}/user/getTaskM?userId="+id,
            onClosed:function(){
	            getTask(thisPage);
	        }
        });
     });
});
</script>
	<body>
		<div title="推送列表" style="padding: 10px;">
			<table class="altrowstable" id="alternatecolor" width="100%">
				<tbody>
					<tr align='center'>
						<td>
							<input id="Songall" type="checkbox" value="0">
						</td>
						<td>
							用户id
						</td>
						<td>
							任务名
						</td>
						<td>
							邮件地址
						</td>
						<td>
							发送时间
						</td>
						<td>
							是否发送
						</td>
					</tr>
				</tbody>
				<tbody id="tb">
				</tbody>
			</table>
			<div id="pp" class="easyui-panel" style="width: 100%"></div>
		</div>
	</body>
</html>
