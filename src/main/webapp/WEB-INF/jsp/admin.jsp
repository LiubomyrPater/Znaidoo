<%--
  Created by IntelliJ IDEA.
  User: Elem
  Date: 16.04.2020
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Admin</title>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/other_script.js"></script>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

</head>
<body>
<div>
    <button type="button" onclick='window.location.href = "admin/createNewDevice"'>Create new device</button>
</div>

<div>
    <a class="btn btn-lg" onclick="document.forms['logoutForm'].submit()"><spring:message code="home.logout"/></a>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout"></form>
    </c:if>
</div>

</body>
</html>

