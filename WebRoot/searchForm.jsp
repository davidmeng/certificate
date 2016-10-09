<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link rel="stylesheet" type="text/css" href="<c:url value='/wbox/wbox.css' />"/>
<script type="text/javascript" src="<c:url value='/script/wbox-min.js'/>"></script>
<script type="text/javascript">
<!--
function addPerson(){
	
	window.location.href = '<c:url value="/person.do?method=addPerson" />';
}

function assignCertificate(){

	window.open('<c:url value="/assign.do?method=create" />');
}
//alert("该网站将于2015年08月13号到期，为了不影响您的工作，请于到期前续费。谢谢合作。");

//-->
$(function() {
    $( "#educationStartDateStr" ).datepicker({
      changeMonth: true,
      changeYear: true,
      "dateFormat":"yy-mm-dd"
    });
    $( "#educationEndDateStr" ).datepicker({
      changeMonth: true,
      changeYear: true,
      "dateFormat":"yy-mm-dd"
    });
    $( "#positionStartDateStr" ).datepicker({
        changeMonth: true,
        changeYear: true,
        "dateFormat":"yy-mm-dd"
      });
    $( "#positionEndDateStr" ).datepicker({
        changeMonth: true,
        changeYear: true,
        "dateFormat":"yy-mm-dd"
      });
  });
</script>
	<table width="1500px"  align="center">
		<tr>
			
			<td align="center"><img width="100px" height="100px" src="<c:url value="/" /><bean:write name="initCompanyIcon" />" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<font size="25pt"><bean:write name="initCompanyName" />资质管理系统</font></td>
			<td align="right"><a href="#" id="resetPassword">修改密码</a></td>
		</tr>
	</table>
	<html:form action="/certificate.do?method=list" styleId="cerficateForm" method="post" styleClass="ch-form ch-box-lite">	
	<table width="1500px"  align="center" id="searchTable">
	<tr><td width="10%">名字</td><td width="30%"><p class="ch-form-row"><html:text property="name" style="width:200px"/></p></td>
		<td width="10%">身份证号</td><td width="30%"><p class="ch-form-row"><html:text property="id" style="width:200px"/></p></td>
		<td width="20%"></td>
	</tr>
	<tr>
		<td>所属单位</td><td><p class="ch-form-row"><html:text property="companyName" style="width:200px"/></p></td>
		<td>证件编号</td><td><p class="ch-form-row"><html:text property="certificationNum" style="width:200px"/></p></td>
		<td></td>
	</tr>
	<tr><td>工种</td>
		<td>
		<html:select property="subCategoryId" styleId="subCategoryId" style="width:200px">
			<html:option value="0">&nbsp;</html:option>
			<html:optionsCollection name="cerficateForm" property="subCategoryList" value="id" label="descr"/>
		</html:select>
		</td>
		<td>证件类型</td>
		<td>
			<html:radio property="certificateTypeId" value="2" styleId="check1"></html:radio><label for="check1">项目证</label>
			<html:radio property="certificateTypeId" value="1" styleId="check2"></html:radio><label for="check2">非项目证</label>
		</td>
		<td>
		</td>
	</tr>
	<tr>
		<td>身份证</td>
		<td>
			<html:radio property="idExist" value="1" styleId="idExist1"></html:radio><label for="idExist1">存在</label>
			<html:radio property="idExist" value="-1" styleId="idExist2"></html:radio><label for="idExist2">不存在</label>
			<html:radio property="idExist" value="0" styleId="idExist3"></html:radio><label for="idExist3">忽略</label>
		</td>
		<td>学历证</td>
		<td>
			<html:radio property="certificateExist" value="1" styleId="certificateExist1"></html:radio><label for="certificateExist1">存在</label>
			<html:radio property="certificateExist" value="-1" styleId="certificateExist2"></html:radio><label for="certificateExist2">不存在</label>
			<html:radio property="certificateExist" value="0" styleId="certificateExist3"></html:radio><label for="certificateExist3">忽略</label>
		</td>
		<td></td>
	</tr>
	<tr>
		<td>职称</td>
		<td>
					<html:select property="position" styleId="position" style="width:200px" >
					<html:option value="0">&nbsp;</html:option>
					<html:option value="1">初级</html:option>
					<html:option value="2">中级</html:option>
					<html:option value="3">高级</html:option>
				</html:select>
		</td>
		<td>发证年份</td>
		<td><html:text property="issueYear" style="width:200px"/></td>
	</tr>
	<tr>
		<td>借出情况</td>
		<td><html:text property="lendInfo" style="width:200px"/></td>
		<td>使用情况</td>
		<td><html:text property="usedInfo" style="width:200px"/></td>
		<td>
		<input type="submit" value="查找" style="ch-btn ch-btn-small">&nbsp;&nbsp;
		<logic:equal value="true" name="MODIFY">
			<input type="button" value="添加用户" style="ch-btn ch-btn-small" onclick="addPerson()">&nbsp;&nbsp;
			<input type="button" value="配证" style="ch-btn ch-btn-small" onclick="assignCertificate()">
		</logic:equal>
		</td>
	</tr>
	</table>
	</html:form>