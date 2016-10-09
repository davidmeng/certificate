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
<script type="text/javascript" src="<c:url value='/script/jquery.jqtransform.js'/>"></script>
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
<link rel="stylesheet" type="text/css" href="<c:url value='/flexigrid/css/flexigrid/flexigrid.css' />"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/style/jqtransform.css' />"/>
<script type="text/javascript">
	$(document).ready(function(){
	
		$('form').jqTransform({imgPath:'<c:url value="/img/" />'});
		
		
		$("#userInfo").flexigrid
			(
			{
			url:encodeURI('<c:url value="/user.do?method=jsonList&userNum=" />'+encodeURIComponent($("#userNum").val())
			+"&userName="+encodeURIComponent($("#userName").val())
				
				),
			//url: 'http://www.flexigrid.info/post2.php',
			dataType: 'json',
			method : 'POST',
			colModel : [
				{display: '用户姓名', name : 'userName', width : 100, sortable : true, align: 'center'},
				{display: '证件号码', name : 'userNum', width : 100, sortable : true, align: 'left'},
				{display: '联系方式', name : 'cellphone', width : 100, sortable : true, align: 'left'},
				{display: '操作', name : 'option', width : 500, sortable : false, align: 'center'}
				],
			sortname: "iso",
			sortorder: "asc",
			usepager: true,
			title: '用户信息',
			useRp: true,
			rp: 10,
			showTableToggleBtn: true,
			width: 910,
			height: 280
			}
			);
	});
	
	
function addUser(){

	window.location.href = '<c:url value="/user.do?method=create" />' ;
}
</script>
</head>
<body>
<table width="900px" align="center">
<tr>
	<td width="100%">
		<table width="100%">
			<tr>
				<td  width="100%" align="center"><font size="5">车库管理系统——人员管理</font>
				<a href="<c:url value='/card.do?method=list' />">卡管理</a></td>
			</tr>
		</table>
	</td>
</tr>
<tr>
	<td width="100%">
		<html:form action="/user.do?method=list" method="post" >
		<table>
			<tr>
				<td width="15%"></td>
				<td width="35%"></td>
				<td width="15%"></td>
				<td width="35%"></td>
				<td width="10%"></td>
			</tr>
			<tr>
				<td>用户姓名</td>
				<td><html:text property="userName" styleId="userName"></html:text></td>
				<td>证件号码</td>
				<td><html:text property="userNum" styleId="userNum"></html:text></td>
				<td><input type="submit" value="查询" /></td>
			</tr>
		</table>
		<table id="userInfo" style="display:none">
		</table>
		<table width="100%">
			<tr>
				<td width="100%">
					<input type="button" value="添加用户" onclick="addUser()"/>
				</td>
			</tr>
		</table>
		</html:form>
	</td>
</tr>
</table>
</body>
</html>