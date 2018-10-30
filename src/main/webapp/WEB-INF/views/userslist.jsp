<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Opsymonroe
  Date: 30.10.2018
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of users</title>
</head>
<body>

<h1>List of users</h1>

    <c:forEach var="user" items="${users}">
       <p><a href="/userprofile${user.userId}">Login ${user.login}</a> </p>
        <p>Admin ${user.isadmin}</p>
        <hr>
    </c:forEach>


</body>
</html>
