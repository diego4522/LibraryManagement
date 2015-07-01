<%@page import="com.reddragon.libreriaweb.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Libreria Web: Agregar nuevo usuario </title>
</head>
<body bgcolor='white'>
<!-- Page Heading -->
<table border='1' cellpadding='5' cellspacing='0' width='400'>
<tr bgcolor='#CCFFFF' align='center' valign='center' height='20'>
<td><h3>Libreria Web: Agregar nuevo usuario </h3></td>
</tr>
</table>
<p>
Escriba la informacion del nuevo usuario
</p>

<%
	User user = new User();
	if(request.getAttribute("errorMsgs") != null){
		%>
		<div>
		<%="Please correct the following errors!!!!" %>
		</div>
		
<% 
		java.util.List<String> errorMsgs = (java.util.List<String>)request.getAttribute("errorMsgs");
		for(String errorMsg: errorMsgs){	
%>
		<li> <%= errorMsg%></li>		
<%		}
		
		user = (User)request.getAttribute("user");
		
	}
%>

<form action='AddUsersServletPath' method='POST'>

Nombre:<input type='text' name='firstname' value="<%=user.getFirstName()%>"> <br/><br/>
Apellido:<input type='text'name='surname' value="<%=user.getSurname()%>"><br/><br/>
Edad:<input type='text' name='age' value="<%=user.getAge()%>"><br/><br/>
Genero:<input type='text' name='gender' value="<%=user.getGender() %>"><br/><br/>
Usuario:<input type='text' name='username' value="<%=user.getUsername() %>"><br/><br/>
Contraseña:<input type='password' name='password' value="<%=user.getPassword() %>"><br/><br/>

<input type='submit' value='Ingresar Usuario'/>
</form>
<center>Click <a href="index.jsp"> aca </a>para salir.</center>
</body>
</html>
