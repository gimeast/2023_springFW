<%--
  Created by IntelliJ IDEA.
  User: ua
  Date: 23. 6. 28.
  Time: 오전 1:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>list page</title>
</head>
<body>
<a href="/todo/register">REGISTER</a>
<ul>
<c:forEach var="dto" items="${list}">
    <li>
        <span><a href="/todo/read?tno=${dto.tno}">${dto.tno}</a></span>
        <span>${dto.title}</span>
        <span>${dto.dueDate}</span>
        <span>${dto.finished? "DONE" : "NOT YET"}</span>
    </li>
</c:forEach>
</ul>
</body>
</html>
