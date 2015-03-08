<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WMQ Reports Page</title>
<s:head />
<style type="text/css">
@import url(style.css);
</style>
</head>
<%@ include file="../main.jsp" %>
<body>


<s:if test="productList.size() > 0">
    
    <display:table name="productList" requestURI="WMQReports" defaultsort="1" defaultorder="descending" pagesize="5" export="true" id="currentRowObject">
    <display:setProperty name="export.rtf.filename" value="Report.rtf"/>
    <display:column property="name" title="Name" sortable="true" headerClass="sortable" />
    <display:column property="version" title="Version" />        
    <display:column media="csv excel rtf" property="version" title="Version" />
    <display:setProperty name="export.pdf" value="true" />
    </display:table>

</s:if>
</body>
</html>