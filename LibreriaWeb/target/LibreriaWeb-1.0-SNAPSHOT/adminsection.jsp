<%-- 
    Document   : adminsection
    Created on : Jun 29, 2015, 5:47:03 PM
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body bgcolor='white'>
<form action="AdminSectionServletPath" method="post">
<table border='1' cellpadding='5' cellspacing='0' width='400'>
<tr bgcolor='#CCCCFF' align='center' valign='center' height='20'>
<td><h3>Bienvenido Administrador</h3>
</td>
</table>

<p>
Opciones de Administrador.

</p> 

<input type="radio" name="menuselection" value="listbooks">Lista de Libros <br>
<input type="radio" name="menuselection" value="listborrowedbooks">Lista de Libros Prestados<br>
<input type="radio" name="menuselection" value="listusers">Lista de Usuarios<br>
<input type="radio" name="menuselection" value="checkoutbook">Pedir/Regresar un Libro<br>
<input type="submit" value="Submit">
<br><br>
<center>Click <a href="index.jsp">aca</a> para salir.</center>
</form> 
</body>
</html>