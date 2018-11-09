<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>BetterBank</title>
    <style>
        form {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename = "com.ynov.i18n.bank"/>

<a href="<c:url value="/accounts"/>"><fmt:message key="accounts"/></a>

<h1>
    <fmt:message key="profile"/>
</h1>

<form action="/profile" method="POST">
    <fmt:message key="password"/>
    <input type="password" name="password">

    <input type="submit" value="Submit" />
</form>

<c:if test = "${requestScope.message != null}">
    <span class="error-msg">${requestScope.message}</span>
</c:if>


<form>
    <select id="language" name="language" onchange="submit()">
        <option value="fr" ${language == 'fr' ? 'selected' : ''}>Français</option>
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
    </select>
</form>
</body>
</html>