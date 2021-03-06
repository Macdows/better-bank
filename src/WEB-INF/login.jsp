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
        .error-msg {
            color: red;
        }
    </style>
</head>
<body>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename = "com.ynov.i18n.bank"/>
<h1>
    <fmt:message key="login"/>
</h1>

<form action="/login" method="POST">
    <fmt:message key="username"/>
    <input type="text" name="username" value="macdows">
    <br />
    <fmt:message key="password"/>
    <input type="password" name="password" value="osef">
    <input type="submit" value="Submit" />
</form>

<c:if test = "${requestScope.errorMsg != null}">
    <span class="error-msg">
        <fmt:message key="${requestScope.errorMsg}"/>
    </span>
</c:if>

<form>
    <select id="language" name="language" onchange="submit()">
        <option value="fr" ${language == 'fr' ? 'selected' : ''}>Français</option>
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
    </select>
</form>
</body>
</html>