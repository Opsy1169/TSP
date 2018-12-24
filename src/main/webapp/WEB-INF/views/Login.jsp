<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
    <link rel="stylesheet" href="/resources/bootstrap-grid.min.css">
    <link rel="stylesheet" href="/resources/bootstrap.min.css">
    <script src="/resources/js/jquery.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    </script>
</head>
<body>
       
    <div class="container">
            <div class="row justify-content-md-center">
                <div class="col col-3">
                        <h1 class="display-2">Log in</h1>
                </div>
            </div>
            <div class="row justify-content-md-center">
                <div class="col col-4">
                <form action="/login" method="post">
                        <div class="form-group" >
                            <label for="exampleInputEmail1">Username</label>
                            <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter username" name="username" required>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Password</label>
                            <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="password" required>
                        </div>
                        <div class="btn-group w-100" role="group" aria-label="Basic example">
                            <button type="submit" class="btn btn-primary">Login</button>
                            <a href="/registration" class="btn btn-secondary">Registration</a>
                        </div>
                        </form>
                    </div>
            </div>
            <c:choose> 
                <c:when test="${error != null}">
                    <div class="alert alert-danger" role="alert">
                        ${error}
                    </div>
                </c:when>
            </c:choose>
    </div> 
   
</body>
</html>
