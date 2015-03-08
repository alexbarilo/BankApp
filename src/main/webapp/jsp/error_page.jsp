<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 05.10.14
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

    ${requestScope.message}<br>
    <form action="${requestScope.redirectionPage}">
        <input type="submit" value="Redirect to start page">
    </form>
</body>
</html>
