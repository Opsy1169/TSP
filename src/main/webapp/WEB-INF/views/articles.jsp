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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/list.js/1.5.0/list.min.js"></script>
    <script>
        $(document).ready(function () {
            var itemContainer = new List('holder', {
                valueNames: ['id', 'title', 'date', 'category'],
                page: 5,
                pagination: true

            });

            $("button").click(function () {
                var link = $(this);
                var lielem = $(this).parent().parent().parent();
                if(confirm("Вы действительно хотите удалить статью?")){
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


<div id="holder">
    <input type="text" class="search"/>
    <button class="sort" data-sort="author">Sort by author</button>
    <button class="sort" data-sort="date">Sort by date</button>
    <ul class="list" style="list-style-type: none">
        <c:forEach var="article" items="${articles}">
            <li style="background-color: #40a070; border-radius: 25px">
                <div id = "${article.articleId}" style =" color: black; display: inline" >
                    <p class="id" hidden="true">${article.articleId}</p>
                    <p class="title"><a  href="/article${article.articleId}">${article.title} </a></p>
                    <p class="date">${article.publishdate}</p>
                    <p class="category">${article.stringCategory}</p>
                    <c:if test="${user.isadmin}">
                       <p>
                            <button id="but" data-id = "${article.articleId}">delete article</button>
                       </p>
                    </c:if>
                </div>
            </li>

        </c:forEach>
    </ul>
    <ul class="pagination" style="display: inline-block"></ul>
</div>


</body>
</html>
