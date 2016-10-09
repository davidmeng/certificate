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
<title><bean:write name="initCompanyName" />资质管理系统--人员信息</title>
<script type="text/javascript" src="<c:url value='/script/jquery-1.4.2.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/script/jquery-ui-1.8.2.custom.min.js'/>"></script>
<script type='text/javascript' src='<c:url value="/dwr/interface/certificateDwr.js"/>'></script>
<script type='text/javascript' src='<c:url value="/dwr/engine.js"/>'></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/style/chico-min-0.13.3.css' />"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/style/jquery.ui.all.css' />"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/style/my.css' />"/>

<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/" /><bean:write name="initCompanyIcon" />" media="screen" />
<script type="text/javascript">

function deleteBook(id){

	if(confirm("您确定要删除该证件吗？")){
	
		var url = "<c:url value='/person.do?method=deleteBook&bookId=' />"+id;
		window.location.href = url;
	}
}

function expiredBook(id){

	if(confirm("您确定要使该证件过期吗？")){
	
		var url = "<c:url value='/person.do?method=expiredBook&bookId=' />"+id;
		window.location.href = url;
	}
}

function validBook(id){

	if(confirm("您确定要激活该证件吗？")){
	
		var url = "<c:url value='/person.do?method=validBook&bookId=' />"+id;
		window.location.href = url;
	}
}

function deleteBook(id){

	if(confirm("您确定要删除该证件吗？")){
	
		var url = "<c:url value='/person.do?method=deleteBook&bookId=' />"+id;
		window.location.href = url;
	}
}

function goBack(){
	window.location.href = '<c:url value="/certificate.do?method=list" />';
}

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
  
</script>
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

</head>
<body>
<br/>
<div id="center">
	<html:form action="/person.do?method=savePerson" styleId="personForm"  method="post">
		
		<table width="1000px" border="1px"  align="center" class="ch-form ch-box-lite">
		<tr>
			<td colspan="5"><legend><bean:write name="initCompanyName" />资质系统--人员信息</legend></td>
		</tr>
			<tr>
				<td width="18%"><label for="text">员工姓名：</label></td>
				<td width="32%"><html:text property="name" styleId="name" style="width:200px"></html:text></td>
				<td width="18%"><label for="text">身份证号：</label></td>
				<td width="32%"><html:text property="personInfo" styleId="personInfo" style="width:200px"></html:text></td>
				<td width="10%"></td>
				
			</tr>
			<tr>
				<td><label for="text">工作单位：</label></td>
				<td><html:text property="companyName" styleId="companyName" style="width:200px"></html:text></td>
				<td><label for="text">工种：</label></td>
				<td>
					<html:select property="subCategoryId" styleId="subCategoryId" style="width:200px">
						<html:option value="0">&nbsp;</html:option>
						<html:optionsCollection name="personForm" property="subCategoryList" value="id" label="descr"/>
					</html:select>
				</td>
				<td>
					
				</td>
			</tr>
			<tr>
				<td><label for="text">证书编号：</label></td>
				<td><html:text property="bookNbr" styleId="bookNbr" style="width:200px"></html:text></td>
				<td><label for="text">证书序号：</label></td>
				<td><html:text property="bookSequance" styleId="bookSequance" style="width:200px"></html:text></td>
				<td></td>
			</tr>
			<tr>
				<td><label for="text">发证时间：</label></td>
				<td><html:text property="bookCreateDate" styleId="bookCreateDate" style="width:200px" styleClass="demo-datepicker"></html:text></td>
				<td><label for="text">检测时间：</label></td>
				<td><html:text property="bookExamDate" styleId="bookExamDate" style="width:200px" styleClass="demo-datepicker"></html:text></td>
				<td></td>
			</tr>
			<tr>
				<td><label for="text">职称：</label></td>
				<td><html:select property="position" styleId="position" style="width:200px" >
					<html:option value="0">&nbsp;</html:option>
					<html:option value="1">初级</html:option>
					<html:option value="2">中级</html:option>
					<html:option value="3">高级</html:option>
				</html:select></td>
				<td><label for="text">职称时间：</label></td>
				<td><html:text property="positionDate" styleId="positionDate" style="width:200px" styleClass="demo-datepicker"></html:text></td>
				<td></td>
			</tr>
			<tr>
				<td><label for="text">最高学历：</label></td>
				<td>
					<html:select property="education" styleId="education" style="width:200px">
						<html:option value="0">&nbsp;</html:option>
						<html:option value="1">初中</html:option>
						<html:option value="2">中专</html:option>
						<html:option value="3">高中</html:option>
						<html:option value="4">专科</html:option>
						<html:option value="5">本科</html:option>
						<html:option value="6">研究生</html:option>
					</html:select>
				</td>
				<td><label for="text">毕业时间：</label></td>
				<td><html:text property="educationDate" styleId="educationDate" style="width:200px" styleClass="demo-datepicker"></html:text></td>
				<td></td>
			</tr>
			<tr>
				<td>身份证</td>
				<td>
					<html:checkbox property="idExist" value="1"></html:checkbox>
				</td>
				<td>学历证</td>
				<td>
					<html:checkbox property="certificateExist" value="1"></html:checkbox>
				</td>
			</tr>
			<tr>

				<td>
					<input class="ch-btn-skin ch-btn-small" type="button" onclick="savePerson()" value="保存人员信息">
					<input class="ch-btn-skin ch-btn-small" type="button" value="返回" onclick="goBack()">
				</td>
			</tr>
		</table>
	</html:form>
	
	<table width="1000px" border="1px"  align="center" class="ch-form ch-box-lite">
	<tr>
		<td colspan="5"><legend><bean:write name="initCompanyName" />资质系统--证件信息</legend></td>
	</tr>
	<tr  height="50px" style="background-color:#dddddd">
		<td width="5%"></td>
		<td width="13%"><label for="text">证书编号</label></td>
		<td width="13%"><label for="text">序号</label></td>
		<td width="13%"><label for="text">工种</label></td>
		<td width="13%"><label for="text">借出情况</label></td>
		<td width="13%"><label for="text">使用情况</label></td>
		<td width="13%"><label for="text">状态</label></td>
		<td width="13%"><label for="text">操作</label></td>
	</tr>
	
	<logic:notEmpty name="personForm" property="person.books">
	<logic:iterate id="book" name="personForm" property="person.books" indexId="indexId">
	<tr  height="50px"
		<logic:equal value="true" name="book" property="lock">
			<%
				if(indexId%2==1){
					out.print("class='lockedOddRow'  onmouseover='this.className=\"lockedHoverRow\"' onmouseout='this.className=\"lockedOddRow\"' ");
				}else{
					out.print("class='lockedEvenRow' onmouseover='this.className=\"lockedHoverRow\"' onmouseout='this.className=\"lockedEvenRow\"' ");
				}
			%>
			
		</logic:equal>
		<logic:equal value="false" name="book" property="lock">
			<%
				if(indexId%2==1){
					out.print("class='oddRow'  onmouseover='this.className=\"hoverRow\"' onmouseout='this.className=\"oddRow\"' ");
				}else{
					out.print("class='evenRow' onmouseover='this.className=\"hoverRow\"' onmouseout='this.className=\"evenRow\"' ");
				}
			 %>
		</logic:equal>
	>
		<td><%=indexId+1 %></td>
		<td><bean:write name="book" property="number" /></td>
		<td><bean:write name="book" property="sequance" format="#" /></td>
		<td><bean:write name="book" property="subCategory.descr" /></td>
		<td><pre><bean:write name="book" property="usedInfo" /></pre></td>
		<td></td>
		<td><bean:write name="book" property="bookStatus.descr" /></td>
		<td>
			<logic:equal value="true" name="book" property="lock">
				<a href="javascript:alert('当前证件已经被锁定，请解锁后删除。')">删除<a/>
			</logic:equal>
			<logic:equal value="false" name="book" property="lock">
				<a href="javascript:deleteBook('<bean:write name="book" property="id" format="#" />')">删除<a/>
				<logic:equal value="1" name="book" property="bookStatus.id">
					<a href="javascript:expiredBook('<bean:write name="book" property="id" format="#" />')">过期<a/>
				</logic:equal>
				<logic:equal value="2" name="book" property="bookStatus.id">
					<a href="javascript:validBook('<bean:write name="book" property="id" format="#" />')">生效<a/>
				</logic:equal>
				
			</logic:equal>
		</td>
		
	</tr>
	</logic:iterate>
	</logic:notEmpty>
	</table>
</div>
</body>
</html>