<%--
  Created by IntelliJ IDEA.
  User: Opsymonroe
  Date: 21.10.2018
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users home</title>
    <%@include file="/WEB-INF/views/libs.jsp" %>
</head>
<body>
    <%@include file="/WEB-INF/views/headerexample.jsp" %>
    <div class="container-fluid">
            <div class="row">
                <div class="col-md-3 no-float">
                    <h1 class="display-4">Welcome back, user</h1>
                    
                </div>
                <div class="col-md-9 no-float">
                    <h1 class="display-4">Controls:</h1>
                    <div class="btn-group-vertical w-100" role="group" aria-label="Basic example">
                            <a href="/userslist" class="btn btn-primary">to the list of users</button>
                            <a href="/createarticle" class="btn btn-secondary">Create a brand new article</a>
                        </div>
                </div>
            </div>
        </div>
</body>
</html>
