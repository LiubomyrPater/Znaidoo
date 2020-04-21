<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Welcome to ZNAIDOO ${pageContext.request.userPrincipal.name}</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>



<body>

<div>
    <a class="navbar-brand" onclick='window.location.href="home"'>
        <img src="http://www.znaidoo.com/images/main_logo.png">
    </a>
</div>


<div class="text-center" role="toolbar" aria-label="Toolbar with buttons">

    <a class="btn btn-lg" onclick='window.location.href="account"'>Account</a>

    <a class="btn btn-lg" onclick='window.location.href="help"'>Help</a>

    <a class="btn btn-lg" onclick="document.forms['logoutForm'].submit()">Logout</a>

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout"></form>
    </c:if>


</div>

</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</html>




