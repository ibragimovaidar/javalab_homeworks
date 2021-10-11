<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Words Statistics service</title>
</head>
<body>
<main>
    <form action="${pageContext.request.contextPath}/words" method="post">
        <label for="folder">Folder absolute path</label>
        <input id="folder" name="folder" type="text"/>
        <br>
        <label for="limit">Limit</label>
        <input id="limit" name="limit" type="password">
        <br>
        <input type="submit" value="submit">
    </form>
</main>
</body>
</html>
