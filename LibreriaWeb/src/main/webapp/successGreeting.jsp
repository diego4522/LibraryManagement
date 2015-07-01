<%-- 
    Document   : successGreeting
    Created on : Jun 23, 2015, 11:56:34 PM
    Author     : Diego
--%>

<%@page import="java.util.List"%>
<%@page import="com.reddragon.libreriaweb.model.CheckOut"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Libreria Web: Bienvenido Usuario</title>
    </head>
   
 <body bgcolor='white'>
<!-- Page Heading -->
<table border='1' cellpadding='5' cellspacing='0' width='400'>
<tr bgcolor='#CCCCFF' align='center' valign='center' height='20'>
<td><h3>Libreria Web: Bienvenido Usuario</h3></td>
</tr>
</table>
<%	List<CheckOut> checkout = (ArrayList<CheckOut>)request.getAttribute("checkedOutItems");
 %>

<p> Bienvenido a la Libreria Web</p>

<u>Libros Actualmente Prestados:</u><br/><br/>	
<table width='100%' border='1'>
<thead align='center'>
<th>Id Transaccion</th>
<th>Nombre Usuario</th>
<th>Id Libro</th>
<th>Fecha de Regreso</th>
</thead>
<%
for(CheckOut checkedOutItem: checkout) {
%>	
	<tr align='center'>
		<td><%=checkedOutItem.getTransactionId()%></td>
		<td><%=checkedOutItem.getUserName()%></td>
		<td><%=checkedOutItem.getBookId()%></td>
                <td><%=checkedOutItem.getReturnDate()%></td>
	</tr>
<%
}
%>
</table>
<br/><br/><br/><br/>
<center>Click <a href="index.jsp">aca</a> para salir.</center>

</body>
</html>
