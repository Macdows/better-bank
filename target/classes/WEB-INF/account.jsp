<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>BetterBank</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 5px 7px;
        }
        form {
            margin-top: 20px;
            display: flex;
            flex-direction: column;
            width: 200px;
        }

        form > * {
            margin-bottom: 10px;
        }
    </style>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('form input[type=button]').click(function(){
                $.ajax({
                    type: 'POST',
                    url: '/api/transaction',
                    data: {
                        name: $('#label')[0].value,
                        amount: $('#amount')[0].value,
                        src_account: ${account.id},
                        dest_account: $('#destaccount')[0].value,
                    }
                });
            });

            $('#delete-account').click(function(){
                $.ajax({
                    type: 'DELETE',
                    url: '/api/account?id=${account.id}',
                });
            });

            $.ajax({
                type: 'GET',
                url: '/api/account',
                success : function(accountList){
                    accountList.splice(accountList.findIndex(x => x.id === ${account.id}), 1);
                    accountList.forEach(function(account) {
                        $('#destaccount').append('<option value="' + account.id + '">' + account.label + '</option>')
                    })
                }
            });
        });
    </script>
</head>
<body>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename = "com.ynov.i18n.bank"/>

<a href="<c:url value="/profile"/>"><fmt:message key="profile"/></a>
<a href="<c:url value="/accounts"/>"><fmt:message key="accounts"/></a>

<h1>
    <fmt:message key="account"/>${account.id}
</h1>

<table>
    <tr>
        <th><fmt:message key="id"/></th>
        <th><fmt:message key="label"/></th>
        <th><fmt:message key="balance"/></th>
        <th><fmt:message key="interest_rate"/></th>
        <th><fmt:message key="actions"/></th>
    </tr>
    <tr>
        <td>${account.id}</td>
        <td>${account.label}</td>
        <td>${account.balance}</td>
        <td>${account.interest_rate}</td>
        <td>
            <button id="delete-account"><fmt:message key="delete"/></button>
        </td>
    </tr>
</table>

<h3>
    <fmt:message key="transactions"/>
</h3>

<table>
    <tr>
        <th><fmt:message key="id"/></th>
        <th><fmt:message key="label"/></th>
        <th><fmt:message key="amount"/></th>
        <th><fmt:message key="src_account"/></th>
        <th><fmt:message key="dest_account"/></th>
        <th><fmt:message key="date"/></th>
    </tr>
    <c:forEach items="${requestScope.transactions}" var="t">
        <tr>
            <td>${t.id}</td>
            <td>${t.name}</td>
            <td>${t.amount}</td>
            <td>${t.src_account.getLabel()}</td>
            <td>${t.dest_account.getLabel()}</td>
            <td>${t.date}</td>
        </tr>
    </c:forEach>
</table>

<h3>
    <fmt:message key="create_transaction"/>
</h3>

<form>
    <fmt:message key="label"/>
    <input id="label" type="text" name="label">

    <fmt:message key="amount"/>
    <input id="amount" type="number" name="amount">

    <fmt:message key="dest_account"/>
    <select id="destaccount" name="dest_account"></select>

    <input type="button" value="Submit" />
</form>

<form>
    <select id="language" name="language" onchange="submit()">
        <option value="fr" ${language == 'fr' ? 'selected' : ''}>Français</option>
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
    </select>
</form>
</body>
</html>