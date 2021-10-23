<%--
  Created by IntelliJ IDEA.
  User: ibrag
  Date: 23.10.2021
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/signIn" method="post">
    <label for="username">Username</label>
    <input id="username" name="username" type="text"/>
    <br>
    <label for="password">Password</label>
    <input id="password" name="password" type="password">
    <br>
    <input type="submit" value="Log in">
</form>
</body>
</html>
