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
<title>密码重置成功</title>
  	<script type="text/javascript">
  	$(document).ready(function(){
            
    	$('body').jqTransform({imgPath:'<c:url value="/img/" />'});
    	//wBox_close
    	$("#closeWbox").addClass("wBox_close");
    });
    
    function closeWbox(){
    
    	alert(window.parent);
    	alert(window.parent.wBox1);
    	window.parent.wBox1.close();
    }
  	</script>
</head>
<body>
<div id="loginDiv">
<form >
	<table align="center" width="100%">
	<tr>
		<td><br/><br/><br/><br/></td>
	</tr>
	<tr>
		<td align="center">密码重置成功</td>
	</tr>

	<tr>
		<td align="center">
			<input type="button" value="关闭" id="closeWbox" onclick="closeWbox()" />
		</td>
	</tr>
	</table>
</form>
</div>
</body>
</html>