<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Opsymonroe
  Date: 15.10.2018
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Articles list!</title>
</head>
<body>
    <ul>
        <c:forEach var="article" items="${articles}">
            <li> <a href="/article${article.articleId}">  ${article.title}       .${article.publishdate}</a> </li>
        </c:forEach>
    </ul>
</body>
</html>
