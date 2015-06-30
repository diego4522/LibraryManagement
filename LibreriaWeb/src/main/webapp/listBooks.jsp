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
<title>Library Management System: List Books</title>
</head>
<body bgcolor='white'>
<!-- Page Heading -->
<table border='1' cellpadding='5' cellspacing='0' width='400'>
<tr bgcolor='#CCCCFF' align='center' valign='center' height='20'>
<td><h3>Library Management System: List Books</h3></td>
</tr>
</table>
<p>
This form allows you to view the list of books.
</p>
<%

List<Book> books = (ArrayList<Book>)request.getAttribute("books");

%>	

<table width='100%' border='1'>
<thead align='center'>
<th>Book Id</th>
<th>Book Name</th>
<th>Author Name</th>
<th>ISBN</th>
<th>Publisher</th>
<th>Total Copies</th>
<th>Available Copies</th>
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

<center>Click <a href="index.html">here</a> to log out.</center>
</body>
</html>
