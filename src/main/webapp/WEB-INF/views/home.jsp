<%--
  Created by IntelliJ IDEA.
  User: Opsymonroe
  Date: 28.09.2018
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  </head>
  <script>
    $(document).ready(function () {
        $("p").click(function () {
            $(this).text("not asd")
        });
    });
  </script>
  <script type="text/javascript">
    function doAjax() {

            $.ajax({
                url: '/ajaxTest',
                data: ({body: $("textarea").val()}),
                success: function (data) {
                    Callback(data)
                }})

            };

    function Callback(data){
        $("ul").prepend('<li>' + data + "</li>");
    }
  </script>
  <body>
  <p>asdasdasd</p>

    <button  value="send" id="sub" onclick="doAjax()">send </button>

  <textarea rows="5" cols="30"></textarea>

  <ul>
  <c:forEach var = "comment" items = "${comments}" >
    <li>${comment.body}</li>
    <li>${comment.authorId}</li>
  </c:forEach>
    <li>${string}</li>
  </ul>
    $END$
  </body>
</html>
