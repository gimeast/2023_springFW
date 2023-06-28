<%--
  Created by IntelliJ IDEA.
  User: ua
  Date: 23. 6. 28.
  Time: 오후 1:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>modify</title>
</head>
<body>
<form id="form1" action="/todo/modify" method="post">
    <div>
        <input type="text" name="tno" value="${dto.tno}" readonly>
    </div>
    <div>
        <input type="text" name="title" value="${dto.title}" >
    </div>
    <div>
        <input type="date" name="dueDate" value="${dto.dueDate}">
    </div>
    <div>
        <input type="checkbox" name="finished" value="${dto.finished}" ${dto.finished ? "checked" : ""} >
    </div>
    <button type="reset">RESET</button>
    <button type="button" id="removeBtn">REMOVE</button>
    <button type="submit">MODIFY</button>
</form>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script>
    $(function () {
        $("#removeBtn").on("click", function (e) {
            let form = $("#form1");
            form.attr("action", "/todo/remove");
            form.submit();
        });
    });
</script>
</body>
</html>
