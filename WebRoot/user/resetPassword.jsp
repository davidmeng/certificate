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
<title>百宝箱——重置密码</title>
  	<script type="text/javascript">
  	$(document).ready(function(){
            
    	$('body').jqTransform({imgPath:'<c:url value="/img/" />'});
    });
    
    function checkSubmit(){
    	
    	if($("#oldPassword").val()==""){
    		
    		alert("原密码不能为空");
    		return false;
    	}
    	
    	if($("#newPassword").val()==""){
    		
    		alert("新密码不能为空");
    		return false;
    	}
    	
    	if($("#passowrd1").val()==""){
    		
    		alert("确认密码不能为空");
    		return false;
    	}
    	
    	
    	if($("#passowrd1").val()!=$("#newPassword").val()){
    		
    		alert("两次密码不一致。请重新输入。");
    		return false;
    	}
    }
  	</script>
</head>
<body>
<div id="loginDiv">
<html:form action="/user.do?method=resetPassword" method="post"  onsubmit="return checkSubmit()">
	<table align="center" width="100%">
	<tr>
		<td><br/><br/><br/><br/></td>
		<td></td>
	</tr>
	<tr>
		<td width="120px" align="right">原密码：</td>
		<td><input type="password" name="oldPassword" id="oldPassword" /></td>
	</tr>
	
	<tr>
		<td align="right">新密码：</td>
		<td><input type="password" name="newPassword" id="newPassword" />
		<logic:notEmpty name="notEqual">
		<logic:equal value="true" name="notEqual">
			<font color="red">密码错误</font>
		</logic:equal>
		</logic:notEmpty>
		</td>
	</tr>
	<tr>
		<td align="right">确认密码：</td>
		<td><input type="password" name="passowrd1" id="passowrd1" />
		</td>
	</tr>

	<tr>
		<td colspan="2" align="center"><input name="submit" type="submit" value="提交"	/>
		<input name="reset" type="reset" value="重置"	/></td>
	</tr>
	</table>
</html:form>
</div>
</body>
</html>