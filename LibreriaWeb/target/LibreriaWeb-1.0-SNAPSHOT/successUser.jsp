<%@page import="com.reddragon.libreriaweb.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Libreria Web: Ingreso Correcto de Usuario</title>
</head>
<body bgcolor='white'>
<!-- Page Heading -->
<table border='1' cellpadding='5' cellspacing='0' width='400'>
<tr bgcolor='#CCCCFF' align='center' valign='center' height='20'>
<td><h3>Libreria Web: Ingreso Correcto de Usuario</h3></td>
</tr>
</table>
<%	User user = (User)request.getAttribute("user");
 %>
<p>
EL usuario  <i> <%=user.getUsername() %></i>  se iingreso correctamente.
</p>
<center>Click <a href="index.jsp">aca</a> para salir.</center>
</body>
</html>

