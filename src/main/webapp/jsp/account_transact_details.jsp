<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="../../css/style.css" rel="stylesheet" type="text/css"/>
<html>
<head>
    <title></title>
</head>
<body>
    <jsp:include page="client_account_buttons.jsp"/>
    <div id="account-transaction-details-div">
        <div id="account-details-div">
            <p>
            Account number: ${sessionScope.currentAccount.accountNum}<br>
            Account amount: ${sessionScope.currentAccount.amount}<br>
            Account currency: ${sessionScope.currentAccount.currency}<br>
            Date of issue: ${sessionScope.currentAccount.date}<br>
            </p>
        </div>
        <div id="transaction-div">
            <table id="transaction-table" name="transactions-table">
                <table-head>
                    <tr>
                        <th>Beneficiary's Account No</th>
                        <th>Beneficiary's bank</th>
                        <th>Beneficiary Fist name</th>
                        <th>Beneficiary Last name</th>
                        <th>Remitted amount</th>
                        <th>Transaction date</th>
                    </tr>
                </table-head>
                <table-body>
                    <c:forEach items="${sessionScope.currentAccount.setOfTransactions}" var="setOfTransactions">
                        <tr>
                            <td>${setOfTransactions.benAccountNum}</td>
                            <td>${setOfTransactions.bankName}</td>
                            <td>${setOfTransactions.benFirstName}</td>
                            <td>${setOfTransactions.benLastName}</td>
                            <td>${setOfTransactions.benAmount}</td>
                            <td>${setOfTransactions.date}</td>
                        </tr>
                    </c:forEach>
                </table-body>
            </table>
        </div>
    </div>
</body>
</html>
