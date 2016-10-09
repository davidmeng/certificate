<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ page import="mfw.acegi.form.AssignForm"%>
<%@ page import="java.util.Map"%>
<%@ page import="mfw.acegi.pojo.Book"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title><bean:write name="initCompanyName" />资质管理系统--配证</title>
<script type="text/javascript" src="<c:url value='/script/jquery-1.4.2.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/script/jquery-ui-1.8.2.custom.min.js'/>"></script>
<script type='text/javascript' src='<c:url value="/dwr/interface/certificateDwr.js"/>'></script>
<script type='text/javascript' src='<c:url value="/dwr/engine.js"/>'></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/style/chico-min-0.13.3.css' />"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/style/jquery.ui.all.css' />"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/style/my.css' />"/>
<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/" /><bean:write name="initCompanyIcon" />" media="screen" />
<style type="text/css">
body{
	TEXT-ALIGN: center;
}
#center{ 
MARGIN-RIGHT: auto;
MARGIN-LEFT: auto;
height:200px;
width:1200px;
vertical-align:middle;
line-height:200px;
}
.assign{

	width:80px;
}
</style>
<script type="text/javascript">


function checkMax(o, id){

	if (o.value==''){
		$("#errorText_"+id).html("");
		return ; 
	}
	if(isNaN(o.value)){
		$("#errorText_"+id).html("<font color='red'>必须是数字</font>");
		o.select();
	}
	
	var maxValue = $("#max_"+id).val();

	if(maxValue < 1*o.value){
	
		$("#errorText_"+id).html("<font color='red'>超出范围</font>");
		o.select();
	}else{
		$("#errorText_"+id).html("");
	}
}


function assignTask(){

	$("#assignForm").submit();	
}
function submitAssign(){

	if($("#usedInfo").val()==''){
		if(!confirm('使用信息没有填写，确定提交吗？')){
			return ;
		}
	}
	$("#batchAssignForm").submit();	
}
</script>
</head>
<body>

<br/>
<div id="center">
	
	<table width="1100px">
	<tr>
		<td>
			<html:form action="/assign.do?method=save" method="post" styleId="assignForm">
			<table width="500px"  align="center">
				<tr>
					<td colspan="2">
						<table  width="500px">
							<tr>
								<td>公司名称：</td>
								<td><html:text property="companyName" styleId="companyName" style="width:200px" /></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr><td>
					
					<table width="500px">
						<tr class='tableHeader'>
							<td width="150px">工种</td>
							<td width="100px">剩余数量</td>
							<td width="250px">需要数量</td>
						</tr>
						<logic:notEmpty name="assignForm" property="subCategoryList">
						<logic:iterate id="subCategory" name="assignForm" property="subCategoryList" indexId="indexId">
						<tr
							<%
								if(indexId%2==1){
									out.print("class='oddRow'  onmouseover='this.className=\"hoverRow\"' onmouseout='this.className=\"oddRow\"' ");
								}else{
									out.print("class='evenRow' onmouseover='this.className=\"hoverRow\"' onmouseout='this.className=\"evenRow\"' ");
								}
							 %>
						>
							<td><bean:write name="subCategory" property="descr" /></td>
							<td><bean:write name="subCategory" property="count" format="#" /></td>
							<td><input name="quantitys" type="text" name="bookNumber" class="assign" id="input_<bean:write name="subCategory" property="subCategoryId" format="#" />" onblur="checkMax(this,'<bean:write name="subCategory" property="subCategoryId" format="#" />')">
								<input type="hidden" id="max_<bean:write name="subCategory" property="subCategoryId" format="#" />" value='<bean:write name="subCategory" property="count" format="#" />'>
								<input type="hidden" name=subCategoryIds value='<bean:write name="subCategory" property="subCategoryId" format="#" />'>
								<span id="errorText_<bean:write name="subCategory" property="subCategoryId" format="#" />"></span>
							</td>
						</tr>
						</logic:iterate>
						</logic:notEmpty>
					</table>
					
				</td></tr>
						
				<tr>
					<td colspan="2">
						<input type="button" value="提交" onclick="assignTask()" style="ch-btn ch-btn-small" />
					</td>
				</tr>
			</table>
			</html:form>
		</td>
		<td>
			<logic:notEmpty name="assignForm" property="assignList">
			<html:form action="/assign.do?method=assign" method="post" styleId="batchAssignForm">
			<table width="500px">
				<tr  class='tableHeader'>
					<td width="100px">工种名称</td>
					<td width="100px">姓名</td>
					<td width="250px">工种  (剩余数量)</td>
				</tr>
				<logic:iterate id="assign" name="assignForm" property="assignList" indexId="indexId">
					<tr
						<%
							if(indexId%2==1){
								out.print("class='oddRow'  onmouseover='this.className=\"hoverRow\"' onmouseout='this.className=\"oddRow\"' ");
							}else{
								out.print("class='evenRow' onmouseover='this.className=\"hoverRow\"' onmouseout='this.className=\"evenRow\"' ");
							}
						 %>
					>
						<td><bean:write name="assign" property="subCategoryDescr" /></td>
						<bean:define id="subCategoryId" name="assign" property="subCategoryId"></bean:define>
						<td><bean:write name="assign" property="person.name" /></td>
						<td>
							<logic:notEmpty name="assign" property="bookList">
							<logic:iterate id="bookVo" name="assign" property="bookList">
							<logic:equal value="true" name="bookVo" property="select">
								<b><bean:write name="bookVo" property="book.subCategory.descr"/></b>
								<input type="hidden" name="bookIds" value='<bean:write name="bookVo" property="book.id" format="#"/>'>
							</logic:equal>
							<logic:equal value="false" name="bookVo" property="select">
								<bean:write name="bookVo" property="book.subCategory.descr"/> 
							</logic:equal>
							(<bean:write name="bookVo" property="count" format="#"/>)
							<br>
							</logic:iterate>
							</logic:notEmpty>
						</td>
					</tr>
				</logic:iterate>
				<tr>
					<td>批量使用信息</td>
					<td>
						<input type="text" name="usedInfo" id="usedInfo">
					</td>
					<td>
						<input type="button" value="使用并锁定" style="ch-btn ch-btn-small" onclick="submitAssign()"/>
					</td>
				</tr>
				
			</table>
			</html:form>
			</logic:notEmpty>
			<logic:notEmpty name="assignForm" property="errorMessage">
					<font color="red"><bean:write name="assignForm" property="errorMessage" /></font>
			</logic:notEmpty>
		</td>
	</tr>
	</table>
	
	
</div>
</body>
</html>