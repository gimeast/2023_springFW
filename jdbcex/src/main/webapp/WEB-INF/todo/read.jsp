<%--
  Created by IntelliJ IDEA.
  User: ua
  Date: 23. 6. 28.
  Time: 오후 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>read</title>
</head>
<body>
<div>
  <input type="text" name="tno" value="${dto.tno}" disabled>
</div>
<div>
  <input type="text" name="title" value="${dto.title}" disabled>
</div>
<div>
  <input type="date" name="dueDate" value="${dto.dueDate}" disabled>
</div>
<div>
  <input type="checkbox" name="finished" value="${dto.finished}" ${dto.finished ? "checked" : ""} disabled>
</div>

<div>
  <a href="/todo/modify?tno=${dto.tno}">modify</a>
  <a href="/todo/list">list</a>
</div>
</body>
</html>
