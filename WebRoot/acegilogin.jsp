<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value='/script/jquery-1.4.2.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/script/jquery.jqtransform.without.textare.js'/>"></script>

<link rel="stylesheet" type="text/css" href="<c:url value='/style/base.css' />"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/style/jqtransform.css' />"/>
<title>登陆</title>
  	<script type="text/javascript">
  	var hasChecked = false ;
  	function checkInfo(sessionId)
  	{
  		var id = document.getElementById("chartId").value;
  		
  		loginDwr.chechImage(checkedInfo,id,sessionId);
  		
  	}
  	function checkedInfo(bool)
  	{
  		hasChecked = bool ;
  		if (bool)
  		{
  			document.getElementById("info").innerHTML="<font color='999999'>测试成功</font>";
  		}
  		else
  		{
  			document.getElementById("info").innerHTML="<font color='red'>图片测试失败</font>";
  			document.getElementById("jcaptcha").src="jcaptcha";
  			document.getElementById("chartId").select();
  		}
  	}
  	
  	function checkSubmit ()
  	{
  		/*
  		if(!hasChecked)
  		{
  			alert("认证失败");
  			return false ;
  		}
  		
  		*/
  		return true;
  	}
  	$(document).ready(function(){
            
    	$('body').jqTransform({imgPath:'<c:url value="/img/" />'});
    });
  	</script>
</head>
<body>
<div id="loginDiv">
<form action="j_security_check" method="post"  onsubmit="return checkSubmit()">
	<table align="center" width="100%">
	<!--
	<tr style="display:none">
		<td>
			<img src="jcaptcha" id="jcaptcha" /> 
		</td>
		<td>
			<input type="text" id="chartId" name="chartId" onblur="checkInfo('<%=request.getSession().getId() %>')" /><span id="info"></span>
		</td>
	</tr>  -->
	<tr>
		<td><br/><br/><br/><br/></td>
		<td></td>
	</tr>
	<tr>
		<td width="120px" align="right">用户名：</td>
		<td><input type="text" name="j_username" id="j_username" /></td>
	</tr>
	
	<tr>
		<td align="right">密码：</td>
		<td><input type="password" name="j_password" id="j_password" />
		
		<%
			String error = request.getParameter("login_error");
			if (error!=null&&error.equals("1"))
			{
				out.println("<font color='red'>用户名 密码错误</font>");
			}
			else if(error!=null&&error.equals("2")){
			
				out.println("<font color='red'>您没有权限，请更换用户后访问。</font>");
			}
		 %>
	
		</td>
	</tr>

	<tr>
		<td colspan="2" align="center"><input name="submit" type="submit" value="提交"	/>
		<input name="reset" type="reset" value="重置"	/></td>
		
	</tr>
	</table>
</form>
</div>
</body>
</html>