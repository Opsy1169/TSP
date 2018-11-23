<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Opsymonroe
  Date: 02.11.2018
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Article</title>
</head>
<body>
<h1>Pishi svoyu ebuchuyu stat`u</h1>
<form:form action="${isNew ? \"createarticle\" : \"editarticle\"}" method="post" modelAttribute="article" enctype="utf-8">
    <table>
        <tr>
            <td><form:label path="title" title="title"/></td>
            <td><form:input path="title" type="text" name="titlefield" /></td>
        </tr>
        <tr>
            <form:label path="articleBody" title="body"/>
            <form:textarea  path="articleBody" rows="20" cols="90" />
        </tr>
        <tr>
            <input type="submit"/>
        </tr>
    </table>
</form:form>
</body>
</html>
