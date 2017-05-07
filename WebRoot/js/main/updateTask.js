$(document).ready(function() {
	for(var i=0;i<24;i++){
		if(i<10){
			$("#hour").append("<option value='"+'0'+i+"'>"+'0'+i+"</option>");
		}else{
			$("#hour").append("<option value='"+i+"'>"+i+"</option>");
		}
	}
	
	for(var i=0;i<60;i++){
		if(i<10){
			$("#min").append("<option value='"+'0'+i+"'>"+'0'+i+"</option>");
		}else{
			$("#min").append("<option value='"+i+"'>"+i+"</option>");
		}
	}
});