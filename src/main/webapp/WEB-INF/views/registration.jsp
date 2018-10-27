<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Opsymonroe
  Date: 21.10.2018
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>registration</title>
</head>
<body>

<form:form action="/user/registration" method="post" enctype="utf8" modelAttribute="user">
    <div>
        <form:label path="username">Login</form:label>
        <form:input path="" type="text" name="username"/>
    </div>
    <div>
        <form:label path="password">Password</form:label>
        <form:input  path="password" type="password" name="password"/>
    </div>
    <div>
        <form:label path="confirmPass">confirm password</form:label>
        <form:input path="confirmPass" type="password" name="confirmPass"/>
    </div>
    <input type="submit" value="submit">
</form:form>

</body>
</html>
