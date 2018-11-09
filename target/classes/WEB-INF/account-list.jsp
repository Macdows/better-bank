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
        }form {
             margin-top: 20px;
             display: flex;
             flex-direction: column;
             width: 200px;
         }
    </style>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('form input[type=button]').click(function(){
                $.ajax({
                    type: 'POST',
                    url: '/api/account',
                    data: {
                        label: $('#label')[0].value,
                        balance: $('#balance')[0].value,
                        interest_rate: $('#interest_rate')[0].value,
                        userId: ${userId},
                    }
                });
            });

            /*var accounts = []

            $.ajax({
                type: 'GET',
                url: '/api/account',
                success : function(accountList){
                    console.log(accountList)
                    accounts = accountList
                }
            });*/
        });
    </script>
</head>
    <body>
        <c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
        <fmt:setLocale value="${language}" />
        <fmt:setBundle basename = "com.ynov.i18n.bank"/>

        <a href="<c:url value="/profile"/>"><fmt:message key="profile"/></a>

        <h1>
            <fmt:message key="home"/>
        </h1>

        <table>
            <tr>
                <th><fmt:message key="id"/></th>
                <th><fmt:message key="label"/></th>
                <th><fmt:message key="balance"/></th>
                <th><fmt:message key="interest_rate"/></th>
                <th><fmt:message key="actions"/></th>
            </tr>
            <c:forEach items="${requestScope.accounts}" var="account">
                <tr>
                    <td>${account.id}</td>
                    <td>${account.label}</td>
                    <td>${account.balance}</td>
                    <td>${account.interest_rate}</td>
                    <td>
                        <a href="<c:url value="/account?id=${account.id}"/>">
                            <button><fmt:message key="show"/></button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <h3>
            <fmt:message key="create_account"/>
        </h3>

        <form>
            <fmt:message key="label"/>
            <input id="label" type="text" name="label">

            <fmt:message key="balance"/>
            <input id="balance" type="number" name="balance">

            <fmt:message key="interest_rate"/>
            <input id="interest_rate" type="number" name="interest_rate">

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