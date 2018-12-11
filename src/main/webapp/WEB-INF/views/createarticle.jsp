<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Opsymonroe
  Date: 02.11.2018
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <title>Create Article</title>
</head>
<script>
    $(document).ready(function () {
        var done = false;
        $("#subbut").click(function (event) {
                event.preventDefault();
                var inptitle = $("#inputtitle");
                var select = $("#inputselect");
                var textarea = $("#inputtextarea");
                console.log(select.val())
                if (inptitle.val().trim() == "") {
                    inptitle.siblings('span').remove();
                    console.log("title empty");
                    var div = inptitle.parent("div");
                    div.append("<span>empty</span>");
                }else{
                    inptitle.siblings('span').remove();
                }
                if (select.val().trim() == "") {
                    select.siblings('span').remove();
                    console.log("selct empty");
                    var div = select.parent("div");
                    div.append("<span>empty</span>");
                }else{
                    select.siblings('span').remove();
                }

                if (textarea.val().trim() == "") {
                    textarea.siblings('span').remove();
                    console.log("area empty");
                    var div = textarea.parent("div");
                    div.append("<span>empty</span>");
                }else{
                    textarea.siblings('span').remove();
                }
                if (inptitle.val().trim() == "" || select.val().trim() == "" || textarea.val().trim() == "") {
                    return;
                }

                $(this).unbind('click').click();
        })
    })
</script>
<body>
<%@include file="/WEB-INF/views/headerexample.jsp" %>
<h1>Pishi svoyu ebuchuyu stat`u</h1>
<form:form method="post" action="${isNew ? \"createarticle1\" : \"editarticle\"}"  modelAttribute="article"
           enctype="utf-8">




    <div>
        <form:label path="title" title="title"/>
        <form:input path="title" id = "inputtitle" type="text" name="titlefield"/>
        <form:errors path="title"/>
    </div>
    <div>
        <form:label path="stringCategory" title="category"/>
        <form:select path="stringCategory" id = "inputselect" items="${categories}"/>
    </div>

    <div>
        <form:label path="articleBody" title="body"/>
        <form:textarea path="articleBody" id = "inputtextarea" rows="20" cols="90"/>
        <form:errors path="articleBody"/>
    </div>

    <input id="subbut" type="submit"/>

</form:form>
</body>
</html>
