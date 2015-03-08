<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <form action="NewFormServlet" method="post" enctype="application/x-www-form-urlencoded">
        Account number: ${requestScope.currentAccount.accountNum}<br>
        <input type="hidden" name="client-id" value="${requestScope.currentAccount.clientID.clientID}"/>
        <input type="hidden" name="account-number" value="${requestScope.currentAccount.accountNum}"/>
        Account amount<input type="text" name="account-amount" value="${requestScope.currentAccount.amount}"/><br>
        Account currency<select name="account-currency"/><br>
                            <option>USD</option>
                            <option>EUR</option>
                            <option>UAH</option>
                        </select><br>
        Issue date<input type="date" name="account-date" value="${requestScope.currentAccount.date}"/><br>
        <input type="hidden" name="jsp-identifier" value="update-account-form"/>
        <input type="submit" value="Submit"/>
    </form>
</body>
</html>
