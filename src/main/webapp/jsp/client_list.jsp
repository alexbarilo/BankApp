<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title></title>
        <link href="../../css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <%--List of clients with buttons for relevant operations--%>
    <body>
        <form name="client-list" action="ClientListServlet" method="post" enctype="application/x-www-form-urlencoded">
            <div id="client-buttons-div">
                <input type="submit" class="client-button" name="client-action" value="Add client"/>
                <input type="submit" class="client-button" name="client-action" value="Delete client"/>
                <input type="submit" class="client-button" name="client-action" value="Update client"/><br>
                <input type="submit" class="client-button-show-details" name="client-action" value="Show client details"/>
            </div>
            <div id="client-selection-div">
                <select name="client-selection" id="client-selection" size="25" style="width: 290px">
                    <c:forEach items="${sessionScope.listOfClients}" var="listOfClients">
                        <option value="${listOfClients.clientID}">${listOfClients.firstName} ${listOfClients.lastName}</option>
                    </c:forEach>
                </select>
            </div>
        </form>
    </body>
</html>
