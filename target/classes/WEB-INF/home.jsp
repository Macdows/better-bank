<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>BetterBank</title>
</head>
    <body>
        <c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
        <fmt:setLocale value="${language}" />
        <fmt:setBundle basename = "com.ynov.bank"/>
        <h1>
            <fmt:message key="home"/>
        </h1>

        <c:forEach items="${request.getParameter('accounts')}" var="account">
            ${account.id}<br>
            ${account.balance}<br>
        </c:forEach>

        <form>
            <select id="language" name="language" onchange="submit()">
                <option value="fr" ${language == 'fr' ? 'selected' : ''}>Français</option>
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
            </select>
        </form>
    </body>
</html>