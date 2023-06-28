<%--
  Created by IntelliJ IDEA.
  User: ua
  Date: 23. 6. 28.
  Time: 오전 1:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/todo/register" method="post">

    <input type="text" name="title">
    <input type="date" name="dueDate">

    <button type="reset">RESET</button>
    <button type="submit">REGISTER</button>
</form>
</body>
</html>
