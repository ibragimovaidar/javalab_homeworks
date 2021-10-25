<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
<div class="signInForm">
    <form action="${pageContext.request.contextPath}/signIn" method="post">
        <label for="email">Введите вашу почту:</label>
        <input id="email" type="email" name="email" placeholder="Почта">
        <br>
        <label for="password">Введите ваш пароль:</label>
        <input id="password" type="password" name="password" placeholder="Пароль">
        <br>
        <br>
        <input type="submit" value="Sign Up">
    </form>
</div>
</body>
</html>
