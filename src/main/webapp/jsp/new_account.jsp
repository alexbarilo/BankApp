<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <form method="post" action="NewFormServlet" enctype="application/x-www-form-urlencoded">
        <h3>Please enter account information</h3>
        <input type="hidden" name="client-id" value=""/>
        Account number<input name="account-number" type="text"/><br>
        Account amount<input name="account-amount" type="text"/><br>
        Currency<select name="account-currency">
                    <option>USD</option>
                    <option>EUR</option>
                    <option>UAH</option>
                </select><br>
        Issue date<input name="account-date" type="date" /><br>
        <input type="hidden" name="jsp-identifier" value="account-form"/>
        <input type="submit" value="Submit"/>
    </form>
</body>
</html>
