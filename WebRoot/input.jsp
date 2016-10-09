<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title><bean:write name="initCompanyName" />考勤统计系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	#data thead tr td
	{mso-style-parent:style0;
	font-size:10.0pt;
	font-weight:100;
	vertical-align:middle;
	background:#CCFFFF;
	mso-pattern:auto none;
	mso-protection:unlocked visible;
	white-space:normal;}
	#data tbody tr td
	{mso-style-parent:style0;
	font-size:10.0pt;
	mso-protection:unlocked visible;
	white-space:normal;}
	</style>
	<script type="text/javascript">
	</script>
  </head>
  
  <body>
   
    <html:form action="/work.do?method=modify" method="post"  enctype="multipart/form-data">
    <table width="800px"  align="center">
    	<thead>
    		<tr>
    			<td width="100%" align="center"><font size="11pt"><bean:write name="initCompanyName" />考勤统计系统</font></td>
    		</tr>
    	</thead>
    	<tbody>
    		<tr>
    			<td>
    				<html:file property="workFile"></html:file>
    				<input type="submit"  value="提交" />
    			</td>
    		</tr>
    	</tbody>
    </table>	
   	</html:form>
  </body>
</html>
