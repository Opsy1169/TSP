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
</head>
<body>
<%@include file="/WEB-INF/views/headerexample.jsp" %>
<h1>${user.login}`s profile!</h1>

<table>
    <td>
        <tr>Login:</tr>
        <tr>${user.login}</tr>
    </td>
    <td>
        <tr>Admin:</tr>
        <tr>${user.isadmin}</tr>
    </td>
</table>
<table>
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
</body>
</html>
