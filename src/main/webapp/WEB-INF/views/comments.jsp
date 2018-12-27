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
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Comments</title>
    <%@include file="/WEB-INF/views/libs.jsp" %>
</head>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/list.js/1.5.0/list.min.js"></script>

<%--<script type="text/javascript" charset="utf8"--%>
            <%--src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>--%>
    <%--<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">--%>

    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>--%>
    <%--<script type="text/javascript" src=${pageContext.request.contextPath}/anotherres/js/jPages-master/js/jPages.js"></script>--%>
        <%--<link rel="stylesheet" type="text/css" href="<c:url value="/anotherres/js/jPages-master/css/jPages.css"/>" >--%>
    <%--<spring:url value="/resources/js/jPajes-master/js/jPages.js" var="pagesjs"/>--%>
    <spring:url value="/resources/js/jPages-master/js/jPages.js" var="jpages"/>
        <spring:url value="/resources/check.css" var="pagescss"/>

    <script src="${jpages}"></script>
    <link href="${pagescss}" rel="stylesheet"/>
</head>

<body>
    <%@include file="/WEB-INF/views/headerexample.jsp" %>

<%--<div class="holder"></div>--%>
    <%--<ul id="itemContainer">--%>
        <%--<c:forEach var="comment" items="${comments}">--%>
            <%--<li>--%>
                <%--<span>${comment.body}</span>--%>
                <%--<span>${comment.authorId.login}</span>--%>
                <%--<span>${comment.formatTime}</span>--%>

            <%--</li>--%>
        <%--</c:forEach>--%>
    <%--</ul>--%>
<!-- Future navigation panel -->
<script type="text/javascript">

    //$(document).ready(function () {


        // $('#itemContainer').easyPaginate({
        // paginateElement: 'p',
        //     elementsPerPage: 3,
        //     effect: 'climb'
        // })
        // let jPages = $('div.holder').jPages({
        //     containerID: 'itemContainer'
        // });
    //})


</script>

<!-- Item container (doesn't need to be an UL) -->
    <!-- Items -->
<div id="holder" class="container">
        <div class="row justify-content-md-center">
                <div class="col col-3">
                        <h1 class="display-2">Comments</h1>
                </div>
            </div>
            <div class="row justify-content-md-left">
                    <div class="col col-8">
    <input type="text" class="search" plac placeholder="Serch" />
    <button class="sort btn btn-primary" data-sort="author">Sort by author</button>
    <button class="sort btn btn-secondary" data-sort="date">Sort by date</button>
                    </div>
            </div>
            <div class="row justify-content-md-left">
                    <div class="col col-12">
<ul class="list" style="list-style-type: none">
 <c:forEach var="comment" items="${comments}">
     <li class="list-group-item">
         <div style =" color: black">
             <p class="new" hidden="true">0</p>
         <p class="author">${comment.authorId.login}</p>
         <p class="date font-italic">${comment.date}</p>
         <p class="body font-weight-bolder">${comment.body}</p>
         </div>
     </li>

 </c:forEach>
</ul>
    <ul class="pagination" style="display: inline-block"></ul>
                    </div>
            </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        var itemContainer = new List('holder', {
            valueNames: ['author', 'date', 'body'],
            page: 5,
            pagination: true

        });

        $("#asd").click(function () {
            // $(".list").prepend("<li style=\"background-color: #40a070; border-radius: 25px\"> "+
            //     "<div style =\" color: red\">" +
            //     "<p class=\"new\">1</p>" +
            //     "<p class=\"author\">1169</p>" +
            //     "<p class=\"date\">2018-1-1</p>" +
            //     "<p class=\"body\">add comment</p>"+
            //     "</div>" +
            //     "</li>");
            itemContainer.add({"new": 1,"author": '1169', "date": '2018-12-05', "body": 'add comment' });
            itemContainer.sort("date", {alphabet: "-0123456789", order: "desc"});

        })
    })

</script>


    <%--<div style="background-color: #40a070">asdasdasdasd</div>--%>
    <%--<div style="background-color: #40a070">asdasdasdasd</div>--%>
    <%--<div style="background-color: #40a070">asdasdasdasd</div>--%>
    <%--<div style="background-color: #40a070">asdasdasdasd</div>--%>
    <%--<div style="background-color: #40a070">asdasdasdasd</div>--%>
    <%--<div style="background-color: #40a070">asdasdasdasd</div>--%>
    <%--<div style="background-color: #40a070">asdasdasdasd</div>--%>
    <%--<div style="background-color: #40a070">asdasdasdasd</div>--%>
    <%--<div style="background-color: #40a070">asdasdasdasd</div>--%>
    <%--<div style="background-color: #40a070">asdasdasdasd</div>--%>
    <%--<div style="background-color: #40a070">asdasdasdasd</div>--%>
    <%--<div style="background-color: #40a070">asdasdasdasd</div>--%>
    <%--<div style="background-color: #40a070">asdasdasdasd</div>--%>
    <%--<div style="background-color: #40a070">asdasdasdasd</div>--%>
    <%--<div style="background-color: #40a070">asdasdasdasd</div>--%>
    <%--<div style="background-color: #40a070">asdasdasdasd</div>--%>
    <%--<div style="background-color: #40a070">asdasdasdasd</div>--%>
    <%--<div style="background-color: #40a070">asdasdasdasd</div>--%>





</body>
</html>
