<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<jsp:useBean id="color" class="java.lang.String" scope="session"/>
<jsp:useBean id="menu" class="java.lang.String" scope="session"/>
<%@page import="java.util.Date"%><html>
    
<head>
<title>CYSSA - Herramienta para Importar Archivos de Opus</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="shortcut icon" href="favicon.ico" />
</head>
<body bgcolor="#FFFFFF" text="#000000">
<table width="800" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr> 
    <td colspan="2"><img src="img/cyssa.png" width="443" height="80"></td>
  </tr>
  <tr> 
    <td align ="right" height="4">
    <link rel="stylesheet" type="text/css" href="lib/ddlevelsmenu-base.css" />
    <link rel="stylesheet" type="text/css" href="lib/ddlevelsmenu-topbar.css" />
    <link rel="stylesheet" type="text/css" href="lib/ddlevelsmenu-sidebar.css" />
    <script type="text/javascript" src="lib/ddlevelsmenu.js"></script>     
      <script type="text/javascript">
        ddlevelsmenu.setup("ddtopmenubar", "topbar")
        </script>
        <%= menu %>
    </td>
  </tr>
</table>
    <s:if test="hasActionErrors()">
            <div class="errors"><s:actionerror /></div>
        </s:if>
        <s:if test="hasActionMessages()">
            <div class="welcome"><s:actionmessage /></div>
        </s:if>


<h4>Usuario : <%=session.getAttribute("loggedInUser").toString()%> 

Hora de Acceso : <%=new Date()%></h4>
</body>
</html>
