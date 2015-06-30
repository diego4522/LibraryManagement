<%-- 
    Document   : addperson
    Created on : Jun 29, 2015, 4:08:18 PM
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <title>Library Management System: Add a new user</title>
    </head>
    <body>
        <form action="GreetingServletPath" method="POST">
        User Name: <input type="text" name="userName" size="20"><br>
        Password : <input type="password" name="password" size="20">
        <br/><br/>
        <input type="submit" value="Sign in">
        </form>
    </body>
</html>
