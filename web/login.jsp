<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="beanClient" class="com.mx.nibble.middleware.dao.util.dbCLIENTE" scope="session"/>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page language="java" import="java.sql.*"%>
<%@ page language="java" import="com.mx.nibble.middleware.dao.util.dbCLIENTE"%>
<%@ page language="java" import="com.mx.nibble.middleware.dao.util.dbORG"%>
<%
    
ArrayList al = new ArrayList();
ArrayList al2 = new ArrayList();

try {
    String driver = "org.postgresql.Driver";
    String url = "jdbc:postgresql://olimpo.local:5432/adempiere?autoReconnectForPools=true";
    String username = "adempiere";
    String password = "gusana02";
    String myDataField = null;
    
    String myQuery = "select ad_org_id,ad_client_id,name from adempiere.ad_client";
    Connection myConnection = null;
    PreparedStatement myPreparedStatement = null;
    ResultSet myResultSet = null;
    Class.forName(driver).newInstance();
    myConnection = DriverManager.getConnection(url,username,password);
    myPreparedStatement = myConnection.prepareStatement(myQuery);
    myResultSet = myPreparedStatement.executeQuery();
    //if(myResultSet.next())
    //myDataField = myResultSet.getString("name");
    //out.print(myDataField);
    
    dbCLIENTE client;
    
    int index =0;
    while(myResultSet.next())
    {
        client = new dbCLIENTE();
        client.setAd_org_id(myResultSet.getLong("ad_org_id"));
        client.setAd_client_id(myResultSet.getLong("ad_client_id"));
        client.setName(myResultSet.getString("name"));
        //out.println(myResultSet.getString("name"));
        al.add(index,client);
        index = index+1;
        //out.println(index);
    }
   
   myConnection.close();
   
   
    myQuery = "select ad_org_id,ad_client_id,name from adempiere.ad_org";
    myConnection = null;
    myPreparedStatement = null;
    myResultSet = null;
    Class.forName(driver).newInstance();
    myConnection = DriverManager.getConnection(url,username,password);
    myPreparedStatement = myConnection.prepareStatement(myQuery);
    myResultSet = myPreparedStatement.executeQuery();
        
    dbORG org;
    
    int index2 =0;
    while(myResultSet.next())
    {
        org = new dbORG();
        org.setAd_org_id(myResultSet.getLong("ad_org_id"));
        org.setAd_client_id(myResultSet.getLong("ad_client_id"));
        org.setName(myResultSet.getString("name"));
        //out.println(myResultSet.getString("name"));
        al2.add(index2,org);
        index2 = index2+1;
        //out.println(index);
    }
   
   myConnection.close();
              
   /*for (int i=0; i<al.size();i++){
       //out.println(i);
    dbCLIENTE client2 = new dbCLIENTE();
    client2 = (dbCLIENTE)al.get(i);                         
    //out.println(client2);
   }*/
          
}
catch(ClassNotFoundException e){e.printStackTrace();}
catch (SQLException ex)
{
out.print("SQLException: "+ex.getMessage());
out.print("SQLState: " + ex.getSQLState());
out.print("VendorError: " + ex.getErrorCode());
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CYSSA - Herramienta para Importar Archivos de Opus</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
<div align="center">
<table width="800" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr> 
    <img src="img/cyssa.png" width="443" height="80">
  </tr>
</table>
   <br>
<table width="800" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr> 
        <s:if test="hasActionErrors()">
            <div class="errors"><s:actionerror /></div>
        </s:if>
        <s:if test="hasActionMessages()">
            <div class="welcome"><s:actionmessage /></div>
        </s:if>

        <s:form action="loginAuthenticaion.action">

            <s:hidden name="loginAttempt" value="%{'1'}" />
            <s:textfield key="global.username" name="userName" />
            <s:password key="global.password" name="password"/>  
            <td class="tdLabel"><label for="loginAuthenticaion_idClient" class="label">Cliente: </label></td>
            <td><select name="idClient" >
			 <%for (int i=0; i<al.size();i++){
                         dbCLIENTE client = (dbCLIENTE)al.get(i);                         
                         //out.println(client);
                         %>
                         <option value=<%= client.getAd_org_id()%> ><%= client.getName() %></option>
			 <%}%>
                </select>
            </td>
            <td class="tdLabel"><label for="loginAuthenticaion_idOrg" class="label">Organización: </label></td>
            <td><select name="idOrg" >
			 <%for (int i2=0; i2<al2.size();i2++){
                         dbORG org = (dbORG)al2.get(i2);                         
                         //out.println(org);
                         %>
                         <option value=<%= org.getAd_org_id()%> ><%= org.getName() %></option>
			 <%}%>
                </select>
            </td>
            <s:submit key="global.submit" name="submit" />
        </s:form>
    </tr>
 </table>
 </div>
 </body>
</html>