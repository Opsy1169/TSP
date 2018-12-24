<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Opsymonroe
  Date: 30.10.2018
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Profile</title>
    <%@include file="/WEB-INF/views/libs.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/views/headerexample.jsp" %>
<h1>${user.login}`s profile!</h1>

<c:choose>
    <c:when test="${articles != null}">
        <table class="table">
                <thead class="thead-dark">
                        <tr>
                        <th scope="col">Artice name</th>
                        <th scope="col">Date</th>
                        <th scope="col">Edit:</th>
                        </tr>
                    </thead>
            <c:forEach var="article" items="${articles}">
                <tr>
                <td><a href="/article${article.articleId}">${article.title}</a> </td>
                <td>${article.publishdate}</td>
                <c:set var = "login" value="${auth.username}"/>
                <c:if test="${user.login eq login}">
                    <td><a href="/editarticle${article.articleId}">Edit article</a> </td>
                </c:if>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <div class="container">
            <div class="col col-md-2 col-md-offset-5">
                <h1 class="display-2">Nothing to show.</h1>
            </div>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>
