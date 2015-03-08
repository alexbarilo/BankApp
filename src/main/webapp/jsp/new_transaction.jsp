<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <form method="post" action="NewFormServlet" enctype="application/x-www-form-urlencoded">
        <h3>Enter new transaction details</h3>
        Beneficiary's bank name<input name="ben-bank-name" type="text"/><br>
        Beneficiary first name<input name="ben-first-name" type="text"/><br>
        Beneficiary last name<input name="ben-last-name" type="text"/><br>
        Beneficiary's account number<input name="ben-account-num" type="text"/><br>
        Amount to remit<input name="remit-amount" type="text"><br>
        Transaction date<input name="transaction-date" type="date"><br>
        <input type="hidden" name="jsp-identifier" value="transaction-form">
        <input type="submit" value="Submit"/>
    </form>
</body>
</html>
