<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Opsymonroe
  Date: 19.10.2018
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>asd</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <script type="text/javascript">
    </script>
</head>
<body>
    <div>
        <form action="/login" method="post">
            <input id="logininput" type="text" name="username" required>
            <input id="passwordinput" type="password" name="password" required>
            <button type="submit">ВОйти</button>
        </form>
    </div>
    <div>
        <a href="/registration">registrtion</a>
    </div>
</body>
</html>
