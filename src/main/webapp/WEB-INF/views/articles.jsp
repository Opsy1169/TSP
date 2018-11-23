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
    <script>
        $(document).ready(function () {
            $("button").click(function () {
                var link = $(this);
                var divholder = $(this).parent().parent();
                if(confirm("Вы действительно хотите удалить статью?")){
                $.ajax({
                    url: '/deletearticle',
                    data:{id: $(this).attr("data-id")},
                    type: "GET",

                })
                    .done(function (data) {

                        divholder.remove();
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

        <%--<c:forEach var="article" items="${articles}">--%>
            <%--<div>--%>
            <%--<tr>--%>
                <%--<td><a  href="/article${article.articleId}">${article.title} </a> </td>--%>
                <%--<td>${article.publishdate} </td>--%>
                <%--<c:if test="${user.isadmin}">--%>
                    <%--<td>--%>
                        <%--<a href="/deletearticle${article.articleId}">delete article</a>--%>
                    <%--</td>--%>
                <%--</c:if>--%>
            <%--</tr>--%>
            <%--</div>--%>
        <table>
            <c:forEach var="article" items="${articles}">

                    <tr id = "${article.articleId}">
                        <td><a  href="/article${article.articleId}">${article.title} </a> </td>
                        <td>${article.publishdate} </td>
                        <c:if test="${user.isadmin}">
                            <td>
                                <button data-id = "${article.articleId}">delete article</button>
                            </td>
                        </c:if>
                    </tr>


            </c:forEach>
        </table>
            <%--<li id="id${article.articleId}"> <a href="/article${article.articleId}">  ${article.title}       .${article.publishdate}</a> </li>--%>

</body>
</html>
