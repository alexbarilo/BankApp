<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
    <head>
        <title></title>
    </head>
    <body>
        <form action="VerificationServlet" method="post" enctype="application/x-www-form-urlencoded"/>
            Login: <input type="text" name="login" value="user"/>
            <input type="submit" value="Submit"/>
        </form>
    </body>
</html>
