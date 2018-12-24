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
    <link rel="stylesheet" href="/resources/bootstrap-grid.min.css">
    <link rel="stylesheet" href="/resources/bootstrap.min.css">
    <script src="/resources/js/jquery.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <title>registration</title>
</head>
<script>
    $(document).ready(function () {
        $("#subbut").click(function (event) {
            event.preventDefault();
            var username = $("#un");
            var pass = $("#p");
            var confpass = $("#cp");

            if (username.val().trim() == "") {
                username.siblings('span').remove();
                console.log("title empty");
                var div = username.parent("div");
                div.append("<span>empty</span>");
            }else{
                username.siblings('span').remove();
            }
            if (pass.val().trim() == "") {
                pass.siblings('span').remove();
                console.log("selct empty");
                var div = pass.parent("div");
                div.append("<span>empty</span>");
            }else{
                pass.siblings('span').remove();
            }

            if (confpass.val().trim() == "") {
                confpass.siblings('span').remove();
                console.log("area empty");
                var div = confpass.parent("div");
                div.append("<span>empty</span>");
            }else{
                confpass.siblings('span').remove();
            }
            if (username.val().trim() == "" || pass.val().trim() == "" || confpass.val().trim() == "") {
                return;
            }
            if(pass.val() !== confpass.val()){
                pass.siblings('span').remove();
                console.log("selct empty");
                var div = pass.parent("div");
                div.append("<span>Your passwords are nor equal</span>");
                return;
            }
            $.ajax({
                url: "/checkexistance",
                data: {login: username.val()},
                method: "POST",
            })
                .done(function (data) {
                    console.log("asd");
                    console.log(data);
                    if(data == true){
                        username.siblings('span').remove();
                        console.log("title empty");
                        var div = username.parent("div");
                        div.append("<span>User with same username already exists</span>");
                        return;
                    }
                    console.log("after")
                    $("#subbut").unbind('click').click();
                })
                .fail(function () {
                    alert("shit");
                })



        });
    })
</script>
<body>

        <div class="container">
                <div class="row justify-content-md-center">
                        <div class="col col-4">
                                <h1 class="display-3">Registration</h1>
                        </div>
                    </div>
            <div class="row justify-content-md-center">
                <div class="col col-4">
                        <form:form action="/registration" method="post" enctype="utf8" modelAttribute="userdto">
                        <div class="form-group" >
                          <label for="exampleInputEmail1">Username</label>
                          <form:input type="text" path="username" class="form-control" id="un" aria-describedby="emailHelp" placeholder="Enter username" name="username" required=""/>
                            <form:errors path="username">
                                <div class="alert alert-danger" role="alert">
                                        ${error}
                                </div>
                            </form:errors>
                        </div>
                        <div class="form-group">
                          <label for="exampleInputPassword1">Password</label>
                          <form:input type="password" path="password" class="form-control" id="p" placeholder="Password" name="password" required=""/>
                          <form:errors path="password"/>
                        </div>
                        <div class="form-group">
                                <label for="exampleInputPassword1">Confirm password</label>
                                <form:input type="password" path="confirmPass" class="form-control" id="cp" placeholder="Confirm password" name="confirmPass" required=""/>
                                <form:errors path="confirmPass"/>
                              </div>
                        <div class="btn-group w-100" role="group" aria-label="Basic example">
                            <button type="submit" id="subbut" class="btn btn-primary">Registration</button>
                            <a href="/" class="btn btn-secondary">Back</a>
                        </div>
                      </form:form>
                    </div>
                </div>
                
        </div>

</body>
</html>
