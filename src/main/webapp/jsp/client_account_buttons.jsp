<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="../../css/style.css" rel="stylesheet" type="text/css"/>
<html>
<head>
    <title></title>
</head>
<body>
    <jsp:include page="client_list.jsp"/>
    <%--Account details with relevant buttons--%>
    <div id="client-account-div">
        <div id="client-details-div">
            Client name:  ${sessionScope.currentClient.firstName} ${sessionScope.currentClient.lastName}<br>
            Client address: ${sessionScope.currentClient.address}<br>
            Client city / code: ${sessionScope.currentClient.city} / ${sessionScope.currentClient.postalCode}<br>
        </div>
        <div id="account-buttons-div">
            <form name="account-details" action="AccountTransactionServlet" method="post" enctype="application/x-www-form-urlencoded">
                <select name="account-selection" style="width:200px">
                    <c:forEach items="${sessionScope.currentClient.setOfAccounts}" var="setOfAccounts">
                        <option>${setOfAccounts.accountNum}</option>
                    </c:forEach>
                </select>
                <input type="submit" class="show-account-button" name="account-action" value="Show account details"/>
                <input type="submit" class="account-button" name="account-action" value="Add account"/>
                <input type="submit" class="account-button" name="account-action" value="Delete account"/>
                <input type="submit" class="account-button" name="account-action" value="Update account"/>
                <input type="submit" class="account-button" name="account-action" value="Add transaction"/>
            </form>
        </div>
    </div>
</body>
</html>
