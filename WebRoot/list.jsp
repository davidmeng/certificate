<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
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
<title><bean:write name="initCompanyName" />资质管理系统</title>
<script type="text/javascript" src="<c:url value='/script/jquery-1.4.2.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/script/jquery-ui-1.8.2.custom.min.js'/>"></script>
<script type='text/javascript' src='<c:url value="/dwr/interface/certificateDwr.js"/>'></script>
<script type='text/javascript' src='<c:url value="/dwr/engine.js"/>'></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/style/chico-min-0.13.3.css' />"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/style/jquery.ui.all.css' />"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/style/my.css' />"/>
<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/" /><bean:write name="initCompanyIcon" />" media="screen" />
<style type="text/css">

</style>
<script type="text/javascript">

window.onload = function (){

	var url = $("#cerficateForm").attr("action") ; 
	if (url.indexOf("jsessionid")>-1){
		$("#cerficateForm").attr("action","<%=request.getContextPath() %>/certificate.do?method=list");
		$("#cerficateForm").submit();
	}
	window.onscroll=scall; 
	window.onresize=scall; 
	window.onload=scall; 
	var wBox = $("#resetPassword").wBox({title:"重置密码",requestType:"iframe",target:'<c:url value="/user/resetPassword.jsp" />'});
}

var person_id  ;
function enableUseage( personId){
	initPersonId(personId);
	var cid = getCertificateId(personId);
	
	if(cid==-1){
		alert("请先选择证件！");
		return ;
	}
	$("#used_span_"+personId).html("");
	$("#used_"+personId).css("display","");
	$("#used_"+personId).attr("disabled",false);
	$("#used_"+personId).select();
}
function enableLend( personId){

	initPersonId(personId);
	var cid = getCertificateId(personId);
	if(cid==-1){
		alert("请先选择证件！");
		return ;
	}
	$("#lend_span_"+personId).html("");
	$("#lend_"+personId).css("display","");
	$("#lend_"+personId).attr("disabled",false);
	$("#lend_"+personId).select();
}
function updateUsage( personId){

	var cid = getCertificateId(personId);
	certificateDwr.updateUsedInfo(cid,$("#used_"+personId).val(),callBackUpdateUsage);
}

function callBackUpdateUsage(){

	$("#used_"+person_id).css("display","none");
	$("#used_"+person_id).attr("disabled",true);
	$("#used_span_"+person_id).html($("#used_"+person_id).val());
}
function updateLendInfo(personId){

	var cid = getCertificateId(personId);
	certificateDwr.updateLendInfo(cid,$("#lend_"+personId).val(),callBackUpdateLend);
}
function callBackUpdateLend(){

	$("#lend_"+person_id).css("display","none");
	$("#lend_"+person_id).attr("disabled",true);
	$("#lend_span_"+person_id).html($("#lend_"+person_id).val());
}
function lockInfo(personId){

	var cid = getCertificateId(personId);
	if(cid==-1){
		alert("请先选择证件！");
		return ;
	}
	certificateDwr.lockCertificate(cid,callBackLockInfo);
}
function callBackLockInfo(){
	
	$("#cerficateForm").submit();
}
function unLockInfo(personId){

	var cid = getCertificateId(personId);
	if(cid==-1){
		alert("请先选择证件！");
		return ;
	}
	certificateDwr.unlockCertificate(cid,callBackLockInfo);
}
function getCertificateId( personId){
	
	var id = $("#certificate_"+personId).find("option:selected").attr("value");
	return id ;
}
function initPersonId(personId){
	person_id = personId;
}
function initInfo(personId){
	initPersonId(personId);
	var cid = getCertificateId(personId);
	if(cid==-1){
		var c = new Object();
		c.lendInfo='';
		c.usedInfo='';
		callBackInitInfo(c) ;
	}
	certificateDwr.getCertificateById(cid,callBackInitInfo);
}
function callBackInitInfo(c){

	//lendInfo,usedInfo
	if(c==null){
		return ;
	}
	if(c.lendInfo!=''){
		$("#lend_"+person_id).val(c.lendInfo);
		$("#lend_span_"+person_id).html(c.lendInfo);
	}else {
		$("#lend_"+person_id).val("");
		$("#lend_span_"+person_id).html("");
	}
	if(c.usedInfo!=''){
		$("#used_"+person_id).val(c.usedInfo);
		$("#used_span_"+person_id).html(c.usedInfo);
	}else {
		$("#used_"+person_id).val("");
		$("#used_span_"+person_id).html("");
	}
}

function setSubCategory(subCategoryId){

	$("#subCategoryId").val(subCategoryId);
	$("#cerficateForm").submit();
}

function scall(){ 
	$("#statDiv").css("top",document.body.scrollTop +300);
	$("#oppStatDiv").css("top",document.body.scrollTop +300);
} 

var close = true ;

function closeDiv(){

	if(!close){
		$("#statDiv").hide( "fold",{}, 500 );
		$("#oppStatDiv").show( "fold",{}, 500 );
		close = true ;
	}
}

function openDiv(){
	if(close){
		$("#oppStatDiv").hide( "fold",{}, 500 );
		$("#statDiv").show( "fold",{}, 500 );
		close = false ;
	}
}
</script>
</head>
<body>

<br/>
<div id="center" style="position:absolute;left:100">
	<jsp:include page="searchForm.jsp"></jsp:include>
	<table width="1500px"  align="center">
	<logic:notEmpty name="cerficateForm"  property="personList">
	<table width="1500px" border="1px"  align="center" class="ch-box-lite">
		<thead>
			<tr height="50px" style="background-color:#dddddd">
				<th align="left" width="1%"></th>
				<th align="left" width="6%">名字</th>
				<th align="left" width="9%">身份证号</th>
				<th align="left" width="8%">所属单位</th>
				<th align="left" width="9%">证件类型</th>
				<th align="left" width="9%">职称</th>
				<th align="left" width="9%">身份证存在</th>
				<th align="left" width="9%">学历证存在</th>
				<th align="left" width="9%">借出情况</th>
				<th align="left" width="9%">使用情况</th>
				<logic:equal value="true" name="MODIFY">
					<th width="11%">操作</th>
				</logic:equal>
			</tr>
		</thead>
		<tbody>
		<bean:define id="locked" value="0"></bean:define>
		<bean:define id="odd" value="false"></bean:define>
		
		<logic:iterate id="person" name="cerficateForm"  property="personList" indexId="indexId">
		<tr valign="middle"
			<logic:equal value="true" name="person" property="lockInfo">
				
				<%
				if(indexId%2==1){
					out.print("class='lockedOddRow'  onmouseover='this.className=\"lockedHoverRow\"' onmouseout='this.className=\"lockedOddRow\"' ");
				}else{
					out.print("class='lockedEvenRow' onmouseover='this.className=\"lockedHoverRow\"' onmouseout='this.className=\"lockedEvenRow\"' ");
				}
			 %>
			</logic:equal> 
			<%
				if(indexId%2==1){
					out.print("class='oddRow'  onmouseover='this.className=\"hoverRow\"' onmouseout='this.className=\"oddRow\"' ");
				}else{
					out.print("class='evenRow' onmouseover='this.className=\"hoverRow\"' onmouseout='this.className=\"evenRow\"' ");
				}
			 %>
			
			height="50px"			
		>
			<td><%=indexId+1 %></td>
			<td><bean:write name="person" property="name" /></td>
			<td><bean:write name="person" property="personInfo" /></td>
			<td><bean:write name="person" property="company.name" /></td>
			<td>
				
				<select
					
					<logic:equal value="true" name="person" property="lockInfo">
					 	style="width:200px; display:none"
					</logic:equal>
				  		style="width:200px"
				  		id="certificate_<bean:write name="person" property="id" format='#'/>"  onchange="initInfo('<bean:write name="person" property="id" format='#'/>')">
					<logic:equal value="false" name="person" property="lockInfo">
						<option value="-1">&nbsp;</option>
					</logic:equal>
					
					<logic:notEmpty name="person" property="books">
					<logic:iterate id="certificate" name="person" property="books">
					<logic:equal value="true" name="person" property="lockInfo">
					</logic:equal>
					<option value="<bean:write format='#' name='certificate' property='id' />" ><bean:write name='certificate' property='subCategory.descr' />	<bean:write name='certificate' property='number' /> [<bean:write name='certificate' property='sequance' format="#" />]
					</option>
					</logic:iterate>
					</logic:notEmpty>
				</select>
				
				<logic:equal value="true" name="person" property="lockInfo">
					<logic:notEmpty name="person" property="books">
					<logic:iterate id="certificate" name="person" property="books">
						<bean:write name='certificate' property='subCategory.descr' /> <bean:write name='certificate' property='number' /> [<bean:write name='certificate' property='sequance' format="#" />]
					</logic:iterate>
					</logic:notEmpty>
				</logic:equal>
			</td>
			<td><bean:write name="person" property="positionStr" /></td>
			<td><input type="checkbox" 
				<logic:equal value="1" name="person" property="idExist">
					checked="true"
				</logic:equal>
			 /></td>
			<td><input type="checkbox" 
				<logic:equal value="1" name="person" property="certificateExist">
					checked="true"
				</logic:equal>
			 /></td>
			<td>
			<logic:equal value="true" name="MODIFY">
				<a href="javascript:enableLend('<bean:write name="person" property="id" format='#'/>')"><pre><span id="lend_span_<bean:write name="person" property="id" format='#'/>"><bean:write name="person" property="lendInfo" filter="true" /></span></pre></a>
				<textarea style="display:none" rows="5" cols="20" id="lend_<bean:write name="person" property="id" format='#'/>" disabled onblur="updateLendInfo('<bean:write name="person" property="id" format='#'/>')"><bean:write name="person" property="lendInfo" /></textarea>
			</logic:equal>
			<logic:equal value="false" name="MODIFY">
				<bean:write name="person" property="lendInfo" filter="true" />
			</logic:equal>
			</td>
			<td>
			<logic:equal value="true" name="MODIFY">
				<a href="javascript:enableUseage('<bean:write name="person" property="id" format='#'/>')"><pre><span id="used_span_<bean:write name="person" property="id" format='#'/>"><bean:write name="person" property="usedInfo" filter="true" /></span></pre></a>
				<textarea style="display:none" rows="5" cols="20" id="used_<bean:write name="person" property="id" format='#'/>" disabled onblur="updateUsage('<bean:write name="person" property="id" format='#'/>')"><bean:write name="person" property="usedInfo" /></textarea>
			</logic:equal>
			<logic:equal value="false" name="MODIFY">
				<bean:write name="person" property="usedInfo" filter="true" />
			</logic:equal>
			</td>
			<logic:equal value="true" name="MODIFY">
				<td>
					<a href="javascript:enableLend('<bean:write name="person" property="id" format='#'/>')">借出</a>
					<a href="javascript:enableUseage('<bean:write name="person" property="id" format='#'/>')">使用</a>
					<logic:equal value="false" name="person" property="lockInfo">
					<a href="javascript:lockInfo('<bean:write name="person" property="id" format='#'/>')">锁定</a>
					</logic:equal>
					<logic:equal value="true" name="person" property="lockInfo">
					<a href="javascript:unLockInfo('<bean:write name="person" property="id" format='#'/>')">解锁</a>
					</logic:equal>
					<a href="<c:url value='/person.do?method=personDetail&personId='  /><bean:write name="person" property="id" format='#'/>">编辑</a>
				</td>
			</logic:equal>
		</tr>
		</logic:iterate>
		</tbody>
	</table>
	</logic:notEmpty>
	</table>
</div>
<jsp:include page="statDiv.jsp"></jsp:include>
</body>
</html>