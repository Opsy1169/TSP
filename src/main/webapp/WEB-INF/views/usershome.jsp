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
    <title>Главная </title>
    <%@include file="/WEB-INF/views/libs.jsp" %>
</head>
<style>
    html,body,.container {
        height:100%;
    }
    .container-fluid {
        display:table;
        width: 100%;
        margin-top: -50px;
        padding: 50px 0 0 0; /*set left/right padding according to needs*/
        box-sizing: border-box;
    }

    .row {
        height: 100%;
        display: table-row;
    }

    .row .no-float {
    display: table-cell;
    float: none;
    }
</style>
<body>
    <%@include file="/WEB-INF/views/headerexample.jsp" %>
    <div class="container-fluid">
            <div class="row">
                <div class="col-md-3 no-float">
                    <h1 class="display-4">Здравствуйте, юзер.</h1>
                    
                </div>
                    <div class="col-md-9 no-float">
                    <h1 class="display-4">Действия:</h1>
                    <div class="btn-group-vertical w-100" role="group" aria-label="Basic example">
                            <a href="/userslist" class="btn btn-primary">Cписок авторов</a>
                            <a href="/createarticle" class="btn btn-secondary">Создать статью</a>
                        </div>
                        <div class="form-group">
                            <select id = "catselect">
                                <option value = "">Категории</option>
                                <option value = "/selectcat?cat=IT">IT</option>
                                <option value = "/selectcat?cat=TV">TV</option>
                                <option value = "/selectcat?cat=Movies">Movies</option>
                                <option value = "/selectcat?cat=Music">Music</option>
                                <option value = "/selectcat?cat=Science">Science</option>
                                <option value = "/selectcat?cat=Books">Books</option>
                                <option value = "/selectcat?cat=Media">Media</option>
                                <option value = "/selectcat?cat=Games">Games</option>
                                <option value = "/selectcat?cat=Travelling">Travelling</option>
                            </select>
                </div>
                </div>
            </div>
        </div>
</body>
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
</html>
