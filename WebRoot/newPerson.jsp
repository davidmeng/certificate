<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%
	Short myLock = new Short((short)1);
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title><bean:write name="initCompanyName" />资质管理系统--添加人员</title>
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
width:800px;
vertical-align:middle;
}
</style>
<script type="text/javascript">
function goBack(){
	window.location.href = '<c:url value="/certificate.do?method=list" />';
}
$(function() {
    $( "#bookExamDate" ).datepicker({
      changeMonth: true,
      changeYear: true,
      "dateFormat":"yy-mm-dd"
    });
    $( "#bookCreateDate" ).datepicker({
      changeMonth: true,
      changeYear: true,
      "dateFormat":"yy-mm-dd"
    });
    $( "#positionDate" ).datepicker({
        changeMonth: true,
        changeYear: true,
        "dateFormat":"yy-mm-dd"
      });
    $( "#educationDate" ).datepicker({
        changeMonth: true,
        changeYear: true,
        "dateFormat":"yy-mm-dd"
      });
  });

function savePerson(){

	if ($("#name").val()==''){
		
		alert("姓名不能为空");
		return false ;
	}
	if ($("#personInfo").val()==''){
		
		alert("身份证信息不能为空");
		return false ;
	}
	if ($("#companyName").val()==''){
		
		alert("工作单位");
		return false ;
	}
	if ($("#bookNbr").val()!=''){
		
		if ($("#subCategoryId").val()=='0'){
		
			alert("工种不能为空");
			return false ;
		}
		if ($("#bookSequance").val()==''){
		
			alert("证书序号不能为空");
			return false ;
		}
		if (isNaN($("#bookSequance").val())){
		
			alert("证书序号必须是数字");
			return false ;
		}
		if ($("#bookCreateDate").val()==''){
		
			alert("发证时间不能为空");
			return false ;
		}
		if ($("#bookExamDate").val()==''){
		
			alert("检测时间不能为空");
			return false ;
		}
		
	}
	$("#personForm").submit();
}
</script>
</head>
<body>
<br/>
<div id="center">
	<html:form action="/person.do?method=savePerson" styleClass="ch-form ch-box-lite" styleId="personForm"  method="post">
		<legend><bean:write name="initCompanyName" />资质系统--添加新员工</legend>
		<p class="ch-form-row">
			<label for="text">员工姓名：</label>
			<html:text property="name" styleId="name"></html:text>
		</p>
		<p class="ch-form-row">
			<label for="text">身份证号：</label>
			<html:text property="personInfo" styleId="personInfo"></html:text>
		</p>
		<p class="ch-form-row">
			<label for="text">性别：</label>
			<input type="radio" id="option1"  value="1" name="gender">
            <label for="option1">男</label>
            <input type="radio" id="option0"  value="0" name="gender">
            <label for="option0">女</label>
		</p>
		<p class="ch-form-row">
			<label for="text">工作单位：</label>
			<html:text property="companyName" styleId="companyName"></html:text>
		</p>
		<p class="ch-form-row">
			<label for="text">证书编号：</label>
			<html:text property="bookNbr" styleId="bookNbr"></html:text>
		</p>
		<p class="ch-form-row">
			<label for="text">证书序号：</label>
			<html:text property="bookSequance" styleId="bookSequance"></html:text>
		</p>
		<p class="ch-form-row">
			<label for="text">工种：</label>
			<html:select property="subCategoryId" styleId="subCategoryId">
				<html:option value="0">&nbsp;</html:option>
				<html:optionsCollection name="personForm" property="subCategoryList" value="id" label="descr"/>
			</html:select>
		</p>
		<p class="ch-form-row">
			<label for="text">发证时间：</label>
			<html:text property="bookCreateDate" styleId="bookCreateDate" styleClass="demo-datepicker"></html:text>
		</p>
		<p class="ch-form-row">
			<label for="text">检测时间：</label>
			<html:text property="bookExamDate" styleId="bookExamDate" styleClass="demo-datepicker"></html:text>
		</p>
		<p class="ch-form-row">
			<label for="text">职称：</label>
			<html:select property="position">
						<html:option value="0">&nbsp;</html:option>
						<html:option value="1">初级</html:option>
						<html:option value="2">中级</html:option>
						<html:option value="3">高级</html:option>
					</html:select>
		</p>
		<p class="ch-form-row">
			<label for="text">评职称时间：</label>
			<html:text property="positionDate" styleId="positionDate" styleClass="demo-datepicker"></html:text>
		</p>
		<p class="ch-form-row">
			<label for="text">最高学历：</label>
			<html:select property="education">
						<html:option value="0">&nbsp;</html:option>
						<html:option value="1">初中</html:option>
						<html:option value="2">中专</html:option>
						<html:option value="3">高中</html:option>
						<html:option value="4">专科</html:option>
						<html:option value="5">本科</html:option>
						<html:option value="6">研究生</html:option>
					</html:select>
		</p>	
		<p class="ch-form-row">
			<label for="text">毕业时间：</label>
			<html:text property="educationDate" styleId="educationDate" styleClass="demo-datepicker"></html:text>
		</p>
		<p class="ch-form-row">
			<label for="text">身份证是否存在：</label>
			<html:checkbox property="idExist" value="1"></html:checkbox>
		</p>
		<p class="ch-form-row">
			<label for="text">学历证是否存在：</label>
			<html:checkbox property="certificateExist" value="1"></html:checkbox>
		</p>
		
		<p class="ch-form-row">
			<input class="ch-btn-skin ch-btn-small" type="button" value="提交" onclick="savePerson()">&nbsp;&nbsp;&nbsp;&nbsp;
			<input class="ch-btn-skin ch-btn-small" type="button" value="返回" onclick="goBack()">
		</p>
	</html:form>
</div>
</body>
</html>