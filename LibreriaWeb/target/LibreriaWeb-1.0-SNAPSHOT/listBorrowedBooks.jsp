<%@page import="java.sql.*"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar" %>
<%@page import="java.util.GregorianCalendar" %>
<%@page import="java.text.SimpleDateFormat" %> 
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>   
<%@page import="com.reddragon.libreriaweb.model.Book"%>
<%@page import="com.reddragon.libreriaweb.model.CheckOut"%>
<%-- 
    Document   : listBorrowedBooks
    Created on : Jun 29, 2015, 6:15:18 PM
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library Management System: List borrowed books</title>
</head>
<body bgcolor='white'>
<!-- Page Heading -->
<table border='1' cellpadding='5' cellspacing='0' width='400'>
<tr bgcolor='#CCCCFF' align='center' valign='center' height='20'>
<td><h3>Library Management System: List borrowed books</h3></td>
</tr>
</table>
<p>
This form allows you to list borrowed books
</p>
<%
List<CheckOut> checkout = (ArrayList<CheckOut>)request.getAttribute("checkout");
List<CheckOut> past_returndate = (ArrayList<CheckOut>)request.getAttribute("past_returndate");

%>	
<br/><br/>
List of book issued

<table width='100%' border='1'>
<thead align='center'>
<th>Transaction Id</th>
<th>Book Id</th>
<th>UserName</th>
</thead>
<%
for(CheckOut checkoutInstance:checkout){
%>	
	<tr align='center'>
            <td><%=checkoutInstance.getTransactionId()%></td>	
            <td><%=checkoutInstance.getBookId()%></td>
            <td><%=checkoutInstance.getUserName()%></td>

	</tr>
<%
}
%>
</table>

<br><br>
List of books which are past their return date

<table width='100%' border='1'>
<thead align='center'>
<th>Transaction Id</th>
<th>Book Id</th>
<th>UserName</th>
</thead>
<%
for(CheckOut checkoutInstance:past_returndate){
%>	
	<tr align='center'>
            <td><%=checkoutInstance.getTransactionId()%></td>	
            <td><%=checkoutInstance.getBookId()%></td>
            <td><%=checkoutInstance.getUserName()%></td>

	</tr>
<%
}
%>
</table>

<br/><br/>



</form>

<center>Click <a href="index.jsp">here</a> to log out.</center>
</body>
</html>