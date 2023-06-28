<%--
  Created by IntelliJ IDEA.
  User: ua
  Date: 23. 6. 28.
  Time: 오후 8:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<c:if test="${param.result eq 'error'}">
    <h1>로그인 에러!!</h1>
</c:if>
<form action="/login" method="post">
    <div>
        ID: <input type="text" name="mid">
    </div>
    <div>
        PW:<input type="password" name="mpw">
    </div>
    <div>
        <input type="checkbox" name="auto">
    </div>
    <button type="submit">login</button>
</form>
</body>
</html>
