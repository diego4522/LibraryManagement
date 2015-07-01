<%-- 
    Document   : successReturn
    Created on : Jun 30, 2015, 10:00:55 PM
    Author     : Diego
--%>

<%@page import="com.reddragon.libreriaweb.model.CheckOut"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Libreria Web: Regreso de Libro</title>
</head>
<body bgcolor='white'>
<form action="AdminSection" method="post">
<!-- Page Heading -->
<table border='1' cellpadding='5' cellspacing='0' width='400'>
<tr bgcolor='#CCCCFF' align='center' valign='center' height='20'>
<td><h3>Libreria Web: Regreso de Libro</h3></td>
</tr>
</table>
<%	CheckOut return_book  = (CheckOut)request.getAttribute("returnBook");
 %>
<p>
El Id de su libro prestado es <i> <%=return_book.getBookId() %></i> por el usuario con ID <%=return_book.getUserName() %> que halla disfrutado su libro.
<!-- Click <a href='adminsection.jsp'>here</a> to try another checkout/return.<br/> -->
</p>
Click <a href='adminsection.jsp'>aca</a> para volver a la pagina de administrador

<center>Click <a href="index.jsp">aca</a> para salir.</center>
</form>
</body>
</html>
