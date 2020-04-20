<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>

<body>

<div class="container">


    <form method="POST" action="${contextPath}/login" class="form-signin">

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>

            <input name="username" type="text" class="form-control" placeholder="Nickname" autofocus="true"/>

            <input name="password" type="password" class="form-control" placeholder="Password"/>

            <label><input type="checkbox" checked="checked" >Remember me</label>

            <span>${error}</span>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        </div>


        <div class="text-center" role="toolbar" aria-label="Toolbar with buttons">

            <button class="btn btn-lg btn-primary btn-success" type="submit">Log In</button>
            <button class="btn btn-lg btn-primary btn-warning" type="button"
                    onclick='window.location.href = "${contextPath}/registration"'>Sign In</button>
            <button class="btn btn-lg btn-primary btn-danger" type="button"
                    onclick='window.location.href = "${contextPath}/forgottenPassword"'>Forgotten</button>

            <button class="btn btn-lg btn-primary btn-block btn-default" type="button"
                    onclick='window.location.href = "demoPage"'>DEMO</button>

            <%--<script>function log_in() {
                window.location.href = "${contextPath}/registration"
            }</script>--%>

        </div>


    </form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
