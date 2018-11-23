<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Opsymonroe
  Date: 30.10.2018
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <title>List of users</title>
</head>
<%--<script>--%>
    <%--$(document).ready(function () {--%>
        <%--$("p").click(function () {--%>
            <%--$(this).text("not asd")--%>
        <%--});--%>
    <%--});--%>
<%--</script>--%>
<script type="text/javascript">
    $(document).ready(function () {
    function doAjax(id) {
        alert(id);
        divid = 'div' + id;
        $('#').parent().hide();

    };

    $("button").click(function () {
        if(confirm("Вы действительно хотите удалить пользователя?")) {
            div = $(this).parent("div");
            console.log(div.attr("id"));
            $.ajax({
                url: '/deleteuser',
                data: ({id: $(this).attr("id")}),
                type: "GET",
                // accepts: "text/html; charset=UTF-8",
            })
                .done(function (data) {
                    div.remove();
                })
                .fail(function (jqXHR, exception) {
                    alert("something went wrong");
                });
        }

        // $.ajax({
        //     url: '/ajaxTest',
        //     data: ({body: $("textarea").val()}),
        //     success: function (data) {
        //         Callback(data)
        //     }})

    });

    function Callback(data){
        $("ul").prepend('<li>' + data + "</li>");
    }
    });
</script>
<body>

<h1>List of users</h1>

    <c:forEach var="user" items="${users}">
        <div id="user${user.userId}">
            <%--<p><a href="/userprofile${user.userId}">Login ${user.login}</a> </p>--%>
            <%--<p>Admin ${user.isadmin}</p>--%>
            <%--<c:if test="${!user.isadmin}">--%>
                <%--<button id="${user.userId}" >delete user</button>--%>
            <%--</c:if>--%>
            <%--<hr>--%>
            <tr>
                <td><a href="/userprofile${user.userId}">Login ${user.login}</a> </td>
                <td>Admin ${user.isadmin}</td>
                <c:if test="${!user.isadmin}">
                <td>
                    <button id="${user.userId}" >delete user</button>
                </td>
                </c:if>
            </tr>
        </div>

    </c:forEach>




</body>
</html>
