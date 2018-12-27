<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Opsymonroe
  Date: 15.10.2018
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Статьи</title>
    <%@include file="/WEB-INF/views/libs.jsp" %>
    <script src="//cdnjs.cloudflare.com/ajax/libs/list.js/1.5.0/list.min.js"></script>
    <script>
        $(document).ready(function () {
            var itemContainer = new List('holder', {
                valueNames: ['id', 'title', 'date', 'category'],
                // page: 5,
                // pagination: true

            });

            $(".delbut").click(function () {
                var link = $(this);
                var lielem = $(this).parent().parent().parent();
                if(true){
                $.ajax({
                    url: '/deletearticle',
                    data:{id: $(this).attr("data-id")},
                    type: "GET",

                })
                    .done(function (data) {
                        //console.log();
                       itemContainer.remove('id', link.attr('data-id'));

                    })
                    .fail(function (data) {
                        alert(data);
                    })
            }})
        })
    </script>
    <title>Articles list!</title>
</head>
<body>
<%@include file="/WEB-INF/views/headerexample.jsp" %>

<%--старая версия с таблицей, раскомментить, если паджинация будет плохо работать--%>
<%--в новой версии теперь есть поиск и сортировка--%>
<%--на первый взгляд все работает, дальше не тестил--%>

        <%--<table>--%>
            <%--<c:forEach var="article" items="${articles}">--%>

                    <%--<tr id = "${article.articleId}">--%>
                        <%--<td> <a  href="/article${article.articleId}">${article.title} </a></td>--%>
                        <%--<td>${article.stringCategory}</td>--%>
                        <%--<td>${article.publishdate} </td>--%>
                        <%--<c:if test="${user.isadmin}">--%>
                            <%--<td>--%>
                                <%--<button data-id = "${article.articleId}">delete article</button>--%>
                            <%--</td>--%>
                        <%--</c:if>--%>
                    <%--</tr>--%>


            <%--</c:forEach>--%>
        <%--</table>--%>


<div id="holder" class="container">
        <div class="row justify-content-md-center">
                <div class="col col-3">
                        <h1 class="display-2">Статьи</h1>
                </div>
            </div>
        <div class="row justify-content-md-left">
    <div class="col col-8">
    <input type="text" class="search" placeholder="Secrh" />
    <button class="sort btn btn-primary" data-sort="date">Сортировать по дате</button>
    </div>
    </div>

    <div class="row justify-content-md-left">
        <div class="col col-12">
    <ul class="list" style="list-style-type: none">
        <c:forEach var="article" items="${articles}">
            <li class="list-group-item">
                <div id = "${article.articleId}" style =" color: black; display: inline" >
                    <p class="id" hidden="true">${article.articleId}</p>
                    <p class="title"><a  href="/article${article.articleId}">${article.title} </a></p>
                    <p class="date">${article.publishdate}</p>
                    <p class="category">${article.categories.category}</p>
                    <c:if test="${user.isadmin}">
                       <p>
                            <button id="but" class="btn btn-primary delbut" data-id = "${article.articleId}">Удалить статью</button>
                       </p>
                    </c:if>
                </div>
            </li>

        </c:forEach>
    </ul>
    <!-- <ul class="pagination" style="display: inline-block"></ul> -->
</div>
</div>
</div>


</body>
</html>
