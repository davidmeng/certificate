<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车库管理-用户管理</title>
<script type="text/javascript" src="<c:url value='/script/jquery-1.4.2.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/script/flexigrid.js'/>"></script>
<script type="text/javascript" src="<c:url value='/script/jQselect.js'/>"></script>
<script type="text/javascript" src="<c:url value='/script/jquery.jqtransform1.js'/>"></script>
<script type="text/javascript">

	$(document).ready(function(){
	
		$('form').jqTransform({imgPath:'<c:url value="/img/" />'});
		});
		
function mycheck(){

	var userName = $("#userName").val();
	if (userName==''){
	
		alert('用户姓名不能为空，请填写后提交。');
		return false ;
	}
}
function goBack(){

	window.location.href = '<c:url value="/user.do?method=list" />';
}
</script>
<style>

	body
		{
		font-family: Arial, Helvetica, sans-serif;
		font-size: 12px;
		}
		
	.flexigrid div.fbutton .add
		{
			background: url(<c:url value='/flexigrid/css/images/add.png' />) no-repeat center left;
		}	

	.flexigrid div.fbutton .delete
		{
			background: url(<c:url value='/flexigrid/css/images/close.png' />) no-repeat center left;
		}	
</style>
<link rel="stylesheet" type="text/css" href="<c:url value='/style/jqtransform.css' />"/>

</head>
<body>
<table width="900px" align="center">
<tr>
	<td width="100%">
		<table width="100%">
			<tr>
				<td  width="100%" align="center"><font size="5">车库管理系统——人员管理</font>
				<a href="<c:url value='/card.do?method=list' />">卡管理</a>
				</td>
			</tr>
		</table>
	</td>
</tr>
<tr>
	<td width="100%">
		<html:form action="/user.do?method=save" method="post" onsubmit="return mycheck()">
		<table>
			<tr>
				<td width="15%"></td>
				<td width="35%"></td>
				<td width="15%"></td>
				<td width="35%"></td>
			</tr>
			<tr>
				<td>用户姓名</td>
				<td><html:text property="userName" styleId="userName" /></td><td></td><td></td>
			</tr>
			<tr>
				<td>证件号码</td>
				<td><html:text property="userNum"  styleId="userNum"/></td><td></td><td></td>
			</tr>
			<tr>
				<td>联系方式</td>
				<td><html:text property="cellphone"  styleId="cellphone"/></td><td></td><td></td>
			</tr>
			<tr>
				<td colspan="4">
					<html:hidden property="userId"/>
					<input type="submit" value="保存" >
					<input type="button" value="返回" onclick="goBack()" >
				</td>
			</tr>
		</table>
		</html:form>
	</td>
</tr>
</table>
</body>
</html>