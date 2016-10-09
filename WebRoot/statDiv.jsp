<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="mfw.acegi.vo.SubCategoryStatisticsVo" %>

	<% int count = 0 , total = 0; %>
	<div style="position:absolute;left:10px;top:300px;cursor:hand;display:none;" id="statDiv"  onclick="closeDiv()">
		<table style="width:220px" class="ch-box-lite" >
			<thead>
				<tr style="background-color:#dddddd">
					<td>&nbsp;&nbsp;&nbsp;工种类型</td><td colspan="2">个数<i style="float:right" class="ch-icon-caret-left"></i></td>
					
				</tr>
			</thead>
			<tbody>
				<logic:notEmpty name="cerficateForm" property="statList">
				<logic:iterate id="stat" name="cerficateForm" property="statList" indexId="indexId">
					<tr
						<%
							if(indexId%2==1){
								out.print("class='oddRow'  onmouseover='this.className=\"hoverRow\"' onmouseout='this.className=\"oddRow\"' ");
							}else{
								out.print("class='evenRow' onmouseover='this.className=\"hoverRow\"' onmouseout='this.className=\"evenRow\"' ");
							}
							count+=((SubCategoryStatisticsVo)stat).getCount();
							total+=((SubCategoryStatisticsVo)stat).getTotalCount();
						 %>
					>
						<td>&nbsp;&nbsp;&nbsp;<a href="javascript:setSubCategory('<bean:write name="stat" property="subCategoryId" format="#" />') "><bean:write name="stat" property="descr" /></a></td>
						<td><a href="javascript:setSubCategory('<bean:write name="stat" property="subCategoryId" format="#" />') "><bean:write name="stat" property="count" format="#"/></a></td>
						<td><a href="javascript:setSubCategory('<bean:write name="stat" property="subCategoryId" format="#" />') ">(<bean:write name="stat" property="totalCount" format="#"/>)</a></td>
					</tr>
					
				</logic:iterate>
				</logic:notEmpty>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;合计</td>
					<td><%=count %></td>
					<td>(<%=total %>)</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="oppStatDiv" onmouseover="openDiv()" style="cursor:hand;position:absolute;left:10px;top:300px;width:10px;">
		<i class="ch-icon-caret-right"></i>
	</div>