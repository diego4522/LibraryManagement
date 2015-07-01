<%-- 
    Document   : listusers
    Created on : Jun 30, 2015, 9:30:34 PM
    Author     : Diego
--%>

<%@page import="com.reddragon.libreriaweb.model.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>Libreria Web: Lista de Usuarios</title>
</head>
<body>
<center><h2><u>Libreria Web: Lista de Usuarios</u></h2></center>
<%

List<User> users = (ArrayList<User>)request.getAttribute("users");
%>	
<table width='100%' border='1'>
<thead align='center'>
<th>Nombre</th>
<th>Apellido</th>
<th>Edad</th>
<th>Genero</th>
<th>Usuario</th>
</thead>
<%
for(User user:users){
%>	
	<tr align='center'>
		<td><%=user.getFirstName()%></td>
		<td><%=user.getSurname()%></td>
		<td><%=user.getAge()%></td>
		<td><%=user.getGender()%></td>
		<td><%=user.getUsername()%></td>
	</tr>
<%
}
%>
</table>

<br/><br/><br/><br/>

<%
%>
<center>Click <a href="adminsection.jsp">aca</a> para regresar admin page.</center>
<center>Click <a href="index.jsp">aca</a> para salir.</center>
</body>
</html>
