<%--
  Created by IntelliJ IDEA.
  User: ibrag
  Date: 23.10.2021
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<html>
<head>
    <title>Users</title>
</head>
<body>
<ul>
    <c:forEach var="user" items="${requestScope.users}">
        <li>
            ${user}
        </li>
    </c:forEach>
</ul>
</body>
</html>
