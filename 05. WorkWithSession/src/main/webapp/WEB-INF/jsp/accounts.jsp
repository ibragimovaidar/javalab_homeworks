<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accounts</title>
</head>
<body>
<main>
    <table>
        <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Email</th>
        </tr>
        <c:forEach var="accountDTO" items="${requestScope.accounts}">
            <tr>
                <th>${accountDTO.firstName}</th>
                <th>${accountDTO.lastName}</th>
                <th>${accountDTO.email}</th>
            </tr>
        </c:forEach>
    </table>
</main>
</body>
</html>
