<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<body>
    <form method="post" action="NewFormServlet" enctype="application/x-www-form-urlencoded">
        <h3>Please enter client information</h3><br>
        <input type="hidden" name="clientID" value="${requestScope.currentClient.clientID}"/>
        Client first name<input type="text" name="first-name" value="${requestScope.currentClient.firstName}"/><br>
        Client last name<input type="text" name="last-name" value="${requestScope.currentClient.lastName}"/><br>
        Client address<input type="text" name="address" size="100" value="${requestScope.currentClient.address}"/><br>
        Client city<input type="text" name="city" value="${requestScope.currentClient.city}"/><br>
        Client postal code<input type="text" name="postal-code" value="${requestScope.currentClient.postalCode}"/><br>
        <input type="hidden" name="jsp-identifier" value="client-form">
        <input type="submit" value="Submit">
    </form>
</body>
</html>
