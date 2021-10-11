<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<main>
    <div class = "statistics">
        <c:forEach items="${requestScope.wordsStatisticsList}" var="wordsStatistics">
            <div class="statistics-elem">
                <p>id: ${wordsStatistics.id}</p>
                <p>folder: ${wordsStatistics.folder}</p>
                <p>filename: ${wordsStatistics.filename}</p>
                <table>
                    <tr>
                        <th>Word</th>
                        <th>Value</th>
                    </tr>
                    <c:forEach items="${wordsStatistics.words}" var="word">
                        <tr>
                            <td>${word.word}</td>
                            <td>${word.value}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:forEach>
    </div>
</main>
</body>
</html>
