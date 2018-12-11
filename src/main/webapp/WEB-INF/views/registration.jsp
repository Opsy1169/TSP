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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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

<form:form action="/registration" method="post" enctype="utf8" modelAttribute="userdto">
    <div>
        <form:label path="username">Login</form:label>
        <form:input path="username" id = "un" type="text" name="username"/>
        <form:errors path="username" />
    </div>
    <div>
        <form:label path="password">Password</form:label>
        <form:input  path="password" type="password" id = "p" name="password"/>
        <form:errors path="password"/>
    </div>
    <div>
        <form:label path="confirmPass">confirm password</form:label>
        <form:input path="confirmPass" type="password" id  = "cp" name="confirmPass"/>
        <form:errors path="confirmPass"/>
    </div>
    <input type="submit" id="subbut" value="submit">
</form:form>

</body>
</html>
