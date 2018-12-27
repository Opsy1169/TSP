<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Opsymonroe
  Date: 15.10.2018
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/WEB-INF/views/libs.jsp" %>
    <script src="//cdnjs.cloudflare.com/ajax/libs/list.js/1.5.0/list.min.js"></script>
    <title>${article.title}</title>
</head>
<script>
    $(document).ready(function () {
        var itemContainer = new List('holder', {
            valueNames: ['author', 'time', 'body'],
            page: 5,
            pagination: true

        });

        $("#sub").click(function (e) {
            var body = $("#text-area").val();
            if(body.trim() == "")
                return
            var username = $(this).attr("data-user");
            console.log(username);
            console.log(body);
            var article = $(this).attr("data-article");
            $.ajax({
                url: "/addcomment",
                data: {
                    body: body,
                    user: username,
                    article: article
                },

                method: "POST",
            })
                .done(function (data) {
                    console.log("in done");
                    console.log(data)
                    console.log(data.time);
                    console.log(data.body);
                    console.log(username);
                    a = {author: username, time: data.time, body: data.body };
                    console.log(a);
                    itemContainer.add(a);
                    itemContainer.sort("time", {alphabet: ":-0123456789", order: "desc"});
                    $("#stub").css("display", "none");
                    //старый скрипт, раскомментить, если новая версия будет работать неправильно
                    // var commentssection = $("#comment-section");
                    // var comment =  "<div style=\"background-color: darkslategray\"> "
                    //     +" <p>"+username+"</p>"
                    //     +" <p>"+data.time+"</p>"
                    //     + "</div>"
                    //     + "<div>"
                    //     + " <p>" +  data.body +"</p>"
                    //     + "<hr></div> ";
                    // console.log(comment);
                    // commentssection.prepend(comment);

                    $("#text-area").val("");
                })
                .fail(function () {
                    alert("error");
                })
        });
    })
</script>
<body>
<%@include file="/WEB-INF/views/headerexample.jsp" %>
<div class="container">
    <div class="row justify-content-md-center">
        <div class="col-md-7 no-float">
        <h1 class="display-2">${article.title}</h1>
            </div>
            </div>
            <div class="row justify-content-md-center">
                <div class="col-md-7 no-float">
                <h2 class="display-2">Категория: ${article.categories.category}</h2>
                    </div>
                    </div>
            <div class="row justify-content-md-center">
                    <div class="col-md-7 no-float">
                        <h2 class="display-5" >Автор: ${articles.categories.category}</h2>
                        </div>
            </div>
            <div class="row justify-content-md-center">
                <div class="col-md-7 no-float">
        <h6 class="display-4">${article.author.login}</h2>
        </div>
        </div>
        <div style="background-color: antiquewhite; width: 1000px">
            <p class="lead">${article.articleBody}</p>
        </div>
        
<c:if test="${ not empty user.login}">
    <div>
        <textarea class = "form-control" id="text-area" rows="5" cols="30"></textarea>
    </div>
    <div>
        <button class="btn btn-primary" data-user = "${user.login}" data-article = "${article.articleId}" value="send" id="sub">Отправить </button>
    </div>
</c:if>
<div >


</div>
<div id="holder">
    <ul class="list" style="list-style-type: none">
        <c:if test="${empty(comments)}">
            <li class="list-group-item" id = "stub">
                <div style =" color: black">
                <p class="new" hidden="true">0</p>
                <p  class="author"> </p>
                <p  class="time"> </p>
                <p  class="body"> </p>
                </div>
            </li>
</c:if>
        <c:forEach var="comment" items="${comments}">
            <li class="list-group-item">
                <div style =" color: black">
                    <p class="new" hidden="true">0</p>
                    <p class="author">${comment.authorId.login}</p>
                    <p class="time">${comment.formatTime}</p>
                    <p class="body">${comment.body}</p>
                </div>
            </li>

        </c:forEach>
    </ul>
    <ul class="pagination" style="display: inline-block"></ul>
</div>

<%--если что-то не будет работать, то можно поробовать какую-нибудь паджинацию попроще, потому что конкретно тут--%>
<%--используется очень мало возмжностей библиотеки--%>
<%--старая версия, раскомментить, если паджинация будет работать неправильно--%>
<%--<div id="comment-section" style="width: 700px; background-color: darkgray">--%>
    <%--<ul>--%>
        <%--<c:forEach var="comment" items="${comments}">--%>
            <%--<div  style="width: 700px; background-color: darkslategray">--%>
            <%--<div >--%>
                <%--<p>${comment.authorId.login }</p>--%>
                <%--<p>${comment.formatTime}</p>--%>
            <%--</div>--%>
                <%--<div >--%>
                <%--<p> ${comment.body}</p>--%>
                <%--</div>--%>
                <%--<hr>--%>
            <%--</div>--%>
        <%--</c:forEach>--%>
    <%--</ul>--%>
<%--</div>--%>
</div>
</body>
</html>

