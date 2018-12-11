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
    <script src="//cdnjs.cloudflare.com/ajax/libs/list.js/1.5.0/list.min.js"></script>
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
    // function doAjax(id) {
    //     alert(id);
    //     divid = 'div' + id;
    //     $('#').parent().hide();
    //
    // };
        var itemContainer = new List('holder', {
            valueNames: ['id', 'login', 'admin'],
            page: 5,
            pagination: true

        });

    $("button").click(function () {
        if(confirm("Вы действительно хотите удалить пользователя?")) {
            button = $(this);
            var lielem = $(this).parent().parent().parent();
            //div = $(this).parent("div");
            // console.log(div.attr("id"));
            $.ajax({
                url: '/deleteuser',
                data: ({id: $(this).attr("data-id")}),
                type: "GET",
                // accepts: "text/html; charset=UTF-8",
            })
                .done(function (data) {
                    // div.remove();
                    itemContainer.remove('id', button.attr('data-id'))
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
<%@include file="/WEB-INF/views/headerexample.jsp" %>
<h1>List of users</h1>

    <%--<c:forEach var="user" items="${users}">--%>
        <%--<div id="user${user.userId}">--%>
            <%--&lt;%&ndash;<p><a href="/userprofile${user.userId}">Login ${user.login}</a> </p>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<p>Admin ${user.isadmin}</p>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<c:if test="${!user.isadmin}">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<button id="${user.userId}" >delete user</button>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<hr>&ndash;%&gt;--%>
            <%--<tr>--%>
                <%--<td><a href="/userprofile${user.userId}">Login ${user.login}</a> </td>--%>
                <%--<td>Admin ${user.isadmin}</td>--%>
                <%--<c:if test="${!user.isadmin}">--%>
                <%--<td>--%>
                    <%--<button id="${user.userId}" >delete user</button>--%>
                <%--</td>--%>
                <%--</c:if>--%>
            <%--</tr>--%>
        <%--</div>--%>

    <%--</c:forEach>--%>
<div id="holder">
    <input type="text" class="search"/>
    <ul class="list" style="list-style-type: none">
        <c:forEach var="user" items="${users}">
            <li style="background-color: #40a070; border-radius: 25px">
                <div id = "${user.userId}" style =" color: black; display: inline" >
                    <p class="id" hidden="true">${user.userId}</p>
                    <p class="login"><a href="/userprofile${user.userId}">Login ${user.login}</a></p>
                    <p class="admin">${user.isadmin}</p>
                    <c:if test="${!user.isadmin}">
                        <p>
                            <button id="but" data-id = "${user.userId}">delete user</button>
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
