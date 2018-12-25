<%--
  Created by IntelliJ IDEA.
  User: Opsymonroe
  Date: 21.10.2018
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin home</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<script>
    $(document).ready(function(){
        $('#catselect').on('change', function () {
            if($(this).val().trim() == "")
                return;
            window.location = $(this).val();
        }
    )
    })
</script>
<style type="text/css">
    #right_col{
        margin-left: 21%;
    }
</style>
<body>

<%@include file="/WEB-INF/views/sidebartest.jsp" %>
<%@include file="/WEB-INF/views/headerexample.jsp" %>
<div id="right_col">
    <h1>Congrats you are admin best hu,an in the world</h1>
    <div>
        <div>
            <a href="/yourprofile">to your profile</a>
        </div>
        <hr>
        <div>
            <a href="/articlelist">to the list of articles</a>
        </div>
        <div>
            <a href="/comments">to the comments</a>
        </div>
        <div>
            <a href="/adminuserslist">to the list of users</a>
        </div>
        <div>
            <a href="/createarticle">create a brand new article</a>
        </div>
        <div>
            <select id = "catselect">
                <option value = "">select</option>
                <option value = "/selectcat?cat=IT">IT</option>
                <option value = "/selectcat?cat=TV">tv</option>
                <option value = "/selectcat?cat=Offtop">offtop</option>
                <option value = "/selectcat?cat=Books">books</option>
                <option value = "/selectcat?cat=Movies">movies</option>
            </select>
        </div>
    </div>
</div>
</body>
</html>
