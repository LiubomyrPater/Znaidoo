<%--
  Created by IntelliJ IDEA.
  User: Elem
  Date: 26.04.2020
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Add Viewer</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/ajax.js"></script>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>
<%@page pageEncoding="UTF-8"%>

<div class="container">




    <form:form method="POST" modelAttribute="addViewerForm" class="form-signin">


        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="username" class="form-control"
                            placeholder="Nickname" autofocus="true"></form:input>
                <form:errors path="username"></form:errors>
            </div>
        </spring:bind>


        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>
</div>
</body>
</html>




