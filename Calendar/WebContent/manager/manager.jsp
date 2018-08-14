<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>后台首页</title>

<script src='/Calendar/js/jquery.min.js'></script>
<script>
function getCookie(name){
	var strcookie = document.cookie;//获取cookie字符串
	var arrcookie = strcookie.split("; ");//分割
	//遍历匹配
	for ( var i = 0; i < arrcookie.length; i++) {
	var arr = arrcookie[i].split("=");
	if (arr[0] == name){
	return arr[1];
	}
	}
	return "";
}
var cookie=getCookie("login");
//过滤请求
$.ajax({ 
	url : "http://132.230.104.158:8090/manager",
	dataType : "jsonp",
	cache:false,
	async:false,
	data:{"cookie":cookie},
    complete:function(response){
    	console.info(response.responseJSON);
    	response=response.responseJSON;
    	if(response!=null){
    		var result=response.result;
        	console.info(result);
        	if(result=="failed"){
        		window.location.href="http://132.230.104.158:8080/Calendar/login.html";
        	}
    	}
        
    }
});</script>

</head>
<frameset cols="10%,*" frameborder="no">
	<frame src="../manager/left.jsp"
		name="left">
	<frame src="../Calendar.jsp" name="right">
</frameset>


</html>