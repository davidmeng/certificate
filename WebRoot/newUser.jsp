<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<script type="text/javascript" src="<c:url value='/script/jquery-1.4.2.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/script/calendar.js'/>"></script>
<script type='text/javascript' src='<c:url value="/dwr/interface/certificateDwr.js"/>'></script>
<script type='text/javascript' src='<c:url value="/dwr/engine.js" />'></script>
<script type="text/javascript" src="<c:url value='/script/jquery.pagination.js'/>"></script>
<script type="text/javascript" src="<c:url value='/script/jquery.jqtransform1.js'/>"></script>

<link rel="stylesheet" type="text/css" href="<c:url value='/style/pagination.css' />"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/style/jqtransform.css' />"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/style/base.css' />"/>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<link rel="icon" href="favicon.ico" type="image/x-icon" /> 
<link rel="Bookmark" href="favicon.ico" type="image/x-icon" /> 
<script type="text/javascript">

function checkPassword(){


	if ($("#password").val()==""){
		
		return ;
	}
	if ($("#password").val()!=$("#password1").val()){
	
		$("#passwordCheck").html("<font color='red'>两次密码不相同。</fond>");
	}else {
	
		$("#passwordCheck").html("<font color='#999999'></fond>");
	}
}
function checkUserName(){
	
	var userName = $("#userName").val();
	certificateDwr.isUserExist(userName,callBackCheckUserName);
}

function callBackCheckUserName(exist){

	if (exist){
		$("#checkUserName").html("<font color='red'>用户名已存在。</fond>");
		$("#userName").select();
	}
	else {
		$("#checkUserName").html("<font color='#999999'>用户名可以使用。</fond>");
	}
}

function checkSubmit(){

	if ($("#userName").val()=="")
	{
		alert("用户名不能为空。请填写后保存。");
		return false ;
	}
	if ($("#password").val()==""){
		alert("密码不能为空。请填写后保存。");
		return false ;
	}
	if ($("#password1").val()==""){
		alert("确认密码不能为空。请填写后保存。");
		return false ;
	}
	if ($("#password1").val()!=$("#password").val()){
		alert("两次密码不相同，请修改后保存。");
		return false ;
	}
}
</script>
</head>
<body>
<div id="bodyDiv">
<form action="<%=request.getContextPath() %>/user.do?method=saveUser" method="post" onsubmit="return checkSubmit()">
<table width="950px" align="center">
<tr><td width="100%" align="center">
<table  width="100%" align="center">
	<tr>
		<td width="30%">*登录名</td>
		<td width="70%"><input type="text" id="userName" name="username" onblur="checkUserName()" /><span id="checkUserName"></span></td>
	</tr>
	<tr>
		<td>*密码</td>
		<td><input type="password" name="password" id="password" onblur="checkPassword()" /></td>
	</tr>
	<tr>
		<td>*密码确认</td>
		<td><input type="password" name="password1"  id="password1" onblur="checkPassword()" />
		<span id="passwordCheck"></span>
		</td>
	</tr>
	<tr id="submitTR">
		<td colspan="2">
			<input type="submit" id="submit" value="保存" />
		</td>
	</tr>
</table>
</td></tr></table>
</form>
</div>
</body>
</html>