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
    <%@include file="/WEB-INF/views/libs.jsp" %>
    <title>Редактор статьи</title>
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
                    div.append("<span color = 'red'>Пустое поле</span>");
                }else{
                    inptitle.siblings('span').remove();
                }
                if (select.val().trim() == "") {
                    select.siblings('span').remove();
                    console.log("selct empty");
                    var div = select.parent("div");
                    div.append("<span color = 'red'>Пустое поле</span>");
                }else{
                    select.siblings('span').remove();
                }

                if (textarea.val().trim() == "") {
                    textarea.siblings('span').remove();
                    console.log("area empty");
                    var div = textarea.parent("div");
                    div.append("<span color = 'red'>Пустое поле</span>");
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
<div class="container">
    <div class="row justify-content-md-center">
        <div class="col-md-7 no-float">
                <h1 class="display-2">Статья</h1>
        </div>
    </div>
    <div class="row justify-content-md-center">
<form:form method="post" action="${isNew ? \"createarticle1\" : \"editarticle\"}"  modelAttribute="article"
           enctype="utf-8">




    <div class="form-group">
        <form:label path="title" title="title"/>
        <form:input class="form-control" path="title" placeholder="Название статьи" id = "inputtitle" type="text" name="titlefield"/>
        <form:errors path="title"/>
    </div>
    <div class="form-group">
        <form:label path="stringCategory" title="category"/>
        <form:select path="stringCategory" id = "inputselect" items="${categories}"/>
    </div>

    <div class="form-group">
        <form:label path="articleBody" title="body"/>
        <form:textarea class = "form-control" placeholder="Форматирование текста происходит при помощи html разметки" path="articleBody" id = "inputtextarea" rows="20" cols="90"/>
        <form:errors path="articleBody"/>
    </div>

    <div class="form-group">
    <button type="submit" id="subbut" class="btn btn-primary">Готово</button>
    </div>

</form:form>
</div>
</div>
</body>
</html>
