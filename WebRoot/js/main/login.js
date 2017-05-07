$(document).ready(function() {
	function flushValidateCode(obj){
		obj.src = "${pageContext.request.contextPath }/admin/rand?time=" + new Date();
	}
});