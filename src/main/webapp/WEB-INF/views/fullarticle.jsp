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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <title>Title</title>
</head>
<script>
    $(document).ready(function () {
        $("#sub").click(function (e) {
            var body = $("#text-area").val();
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
                    console.log(data);
                    console.log(username);
                    var commentssection = $("#comment-section");
                    var comment =  "<div style=\"background-color: darkslategray\"> "
                        +" <p>"+username+"</p>"
                        +" <p>"+data+"</p>"
                        + "</div>"
                        + "<div>"
                        + " <p>" +  body +"</p>"
                        + "<hr></div> ";
                    console.log(comment)
                    commentssection.prepend(comment)
                    $("#text-area").text("");
                })
                .fail(function () {
                    alert("error");
                })
        });
    })
</script>
<body>
        <h1>an article</h1>

        <h2>${article.title}</h2>

        <h2>${article.author.login}</h2>

        <div style="background-color: antiquewhite; width: 1000px">
            ${article.articleBody}
        </div>

<c:if test="${ not empty user.login}">
    <div>
        <textarea id="text-area" rows="5" cols="30"></textarea>
    </div>
    <div>
        <button  data-user = "${user.login}" data-article = "${article.articleId}" value="send" id="sub">send </button>
    </div>
</c:if>
<div >


</div>


<div style="width: 700px; background-color: darkgray">
        <c:forEach var="comment" items="${comments}">
            <div id="comment-section" style="width: 700px; background-color: darkslategray">
            <div >
                <p>${comment.authorId.login }</p>
                <p>${comment.date}</p>
            </div>
                <div >
                <p> ${comment.body}</p>
                </div>
                <hr>
            </div>

        </c:forEach>
</div>

</body>
</html>
