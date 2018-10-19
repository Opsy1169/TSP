<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Opsymonroe
  Date: 15.10.2018
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>an article</h1>
<h2>${article.title}</h2>
<h2>${article.author}</h2>
${article.articleBody}

    <ol>
        <c:forEach var="comment" items="${comments}">
            <li>
                <p>${comment.authorId}</p>
                <p> ${comment.body}</p>
            </li>
        </c:forEach>
    </ol>
</body>
</html>
