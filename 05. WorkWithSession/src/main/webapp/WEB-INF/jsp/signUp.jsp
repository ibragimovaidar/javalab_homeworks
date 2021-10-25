<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
<div class="signUpForm">
    <form action="${pageContext.request.contextPath}/signUp" method="post">
        <label for="firstName">Введите ваше имя:</label>
        <input id="firstName" name="firstName" placeholder="Имя">
        <br>
        <label for="lastName">Введите вашу фамилию:</label>
        <input id="lastName" name="lastName" placeholder="Фамилия">
        <br>
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
