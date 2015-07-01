<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <title>Pagina Principal</title>
</head>
<body bgcolor='white'>
<table border='1' cellpadding='5' cellspacing='0' width='400'>
<tr bgcolor='#CCCCFF' align='center' valign='center' height='20'>
<td><h3>Bienvenido a la Libreria Web</h3>
</td>
</table>

<p>
Ingrese para poder ver los libros prestados.

</p> 


<a >Ingrese sus Datos</a><br/><br/>

<%
	if( request.getAttribute("errorMsgs") != null){
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
		
	}
%>

<form action="GreetingServletPath" method="POST">
  Usuario: <input type="text" name="userName" size="20"><br>
  Contraseña : <input type="password" name="password" size="20">
  <br/><br/>
  <input type="submit" value="Ingresar">
</form>
<p> ¿Aun no esta resgistrado? Click <a href="addUser.jsp" >aca</a> para registrarse.
    
</body>
</html>