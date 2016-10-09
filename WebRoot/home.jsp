<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title><bean:write name="initCompanyName" />资质管理系统</title>
<script type="text/javascript">

function submitForm(){

	window.document.forms[0].submit();
}

function goLink(){

	window.location.href = "certificate.do?method=list" ;
}
</script>
</head>
<body>
<html:form action="/certificate.do?method=home" method="post" enctype="multipart/form-data">
	<html:select property="subCategoryId">
		<html:option value=""></html:option>
		<html:optionsCollection name="cerficateForm" property="subCategoryList" value="id" label="descr" />
	</html:select>
	<html:file property="workFile"></html:file>
	<input type="button" onclick="submitForm();"  value="提交" />
	<a href="certificate.do?method=list"><input type="button" value="列表" /></a>
</html:form>
</body>
</html>