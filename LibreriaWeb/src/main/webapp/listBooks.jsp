<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar" %>
<%@page import="java.util.GregorianCalendar" %>
<%@page import="java.text.SimpleDateFormat" %> 
<%@page import="java.util.List"%>
<%@page import="com.reddragon.libreriaweb.model.Book"%>
<%@page import="com.reddragon.libreriaweb.model.User"%>
<%-- 
    Document   : listBooks
    Created on : Jun 29, 2015, 6:10:09 PM
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Libreria Web: Lista de Libros</title>
</head>
<body bgcolor='white'>
<!-- Page Heading -->
<table border='1' cellpadding='5' cellspacing='0' width='400'>
<tr bgcolor='#CCCCFF' align='center' valign='center' height='20'>
<td><h3>Libreria Web: Lista de Libros</h3></td>
</tr>
</table>
<p>
Lista de libros.
</p>
<%

List<Book> books = (ArrayList<Book>)request.getAttribute("books");

%>	

<table width='100%' border='1'>
<thead align='center'>
<th>Id Libro</th>
<th>Nombre Libro</th>
<th>Nombre Autor</th>
<th>ISBN</th>
<th>Publicador</th>
<th>Total de Copias</th>
<th>Copias Disponibles</th>
</thead>
<%
for(Book book:books){
%>	
	<tr align='center'>
		<td><%=book.getBookId()%></td>
		<td><%=book.getBookName()%></td>
		<td><%=book.getAuthorName()%></td>
		<td><%=book.getISBN()%></td>
		<td><%=book.getPublisher()%></td>
		<td><%=book.getTotalCopies()%></td>
		<td><%=book.getAvailCopies() %></td>

	</tr>
<%
}
%>
</table>


<br/><br/>

<center>Click <a href="index.jsp">aca</a> para salir.</center>
</body>
</html>
