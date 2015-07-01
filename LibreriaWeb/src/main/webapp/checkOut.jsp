<%@page import="com.reddragon.libreriaweb.model.CheckOut"%>
<%@page import="com.reddragon.libreriaweb.model.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar" %>
<%@page import="java.util.GregorianCalendar" %>
<%@page import="java.text.SimpleDateFormat" %> 
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Libreria Web: Prestar/Devolver</title>
</head>
<body bgcolor='white'>
<!-- Page Heading -->
<table border='1' cellpadding='5' cellspacing='0' width='400'>
<tr bgcolor='#CCCCFF' align='center' valign='center' height='20'>
<td><h3>Libreria Web: Prestar/Devolver</h3></td>
</tr>
</table>
<p>
Presta o Devuelve Libros.
</p>
<%

List<Book> books = (ArrayList<Book>)request.getAttribute("books");
%>	
<form action='CheckOutServletPath' method='POST'>
<table width='100%' border='1'>
<thead align='center'>
<th>Id Libro</th>
<th>Nombre Libro</th>
<th>Nombre Autor</th>
<th>ISBN</th>
<th>Publicado</th>
<th>Total de Copias</th>
<th>Copias Disponible</th>
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
		<td><input type='radio' name='bkgroup1' value=<%=book.getBookId()%> /></td>

	</tr>
<%
}
%>
</table>

<br/><br/><br/><br/>


<%
	CheckOut checkout = new CheckOut();
	if(request.getAttribute("errorMsgs") != null){
		%>
		<div>
		<%="Please correct the following errors!!!!" %>
		</div>
		
<% 
		java.util.List<String> errorMsgs = (java.util.List<String>)request.getAttribute("errorMsgs");
		for(String errorMsg: errorMsgs){	
%>
		<li> <%= errorMsgs%></li>		
<%		}
		
		if((CheckOut)request.getAttribute("checkout") != null){
			checkout = (CheckOut)request.getAttribute("checkout");	
		}
		
	}
%>


<% 
   SimpleDateFormat dateFormatter = new SimpleDateFormat();
   
	Date today = new Date();
   String todayString = dateFormatter.format(today);
   System.out.println(todayString);
   Calendar	calendar = new GregorianCalendar();
   calendar.add(Calendar.DAY_OF_YEAR, 7);
   Date sevenDaysAfterNow = calendar.getTime();
   String sevenDaysAfterNowString = dateFormatter.format(sevenDaysAfterNow);%>

Nombre de Usuario:<input type='text' name='username' value="<%=checkout.getUserName()%>"><br/><br/>

Fecha de Regreso:<input type="text" name="dateofreturn" value=<%= sevenDaysAfterNowString %> />
<input type='submit' name='checkout' value='Checkout book'/>
<input type='submit' name='return' value='Return book'/>



</form>

<center>Click <a href="index.jsp">aca</a> para salir.</center>
</body>
</html>
