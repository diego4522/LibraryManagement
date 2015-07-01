<%-- 
    Document   : successCheckout
    Created on : Jun 30, 2015, 9:55:22 PM
    Author     : Diego
--%>

<%@page import="com.reddragon.libreriaweb.model.CheckOut"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Libreria Web: Prestamo Correcto</title>
</head>
<body bgcolor='white'>
<!-- Page Heading -->
<table border='1' cellpadding='5' cellspacing='0' width='400'>
<tr bgcolor='#CCCCFF' align='center' valign='center' height='20'>
<td><h3>Libreria Web: Prestamo Correcto</h3></td>
</tr>
</table>
<%	CheckOut checkout = (CheckOut)request.getAttribute("checkout");
 %>
<p>
Has solicitado el libro de ID <i> <%=checkout.getBookId() %></i> por  <%=checkout.getUserName() %> Disfruta tu libro.
Click <a href='adminsection.jsp'>aca</a> para regresar seccion de administrador.<br/>
</p>
<center>Click <a href="index.jsp">aca</a> para salir.</center>
</body>
</html>
