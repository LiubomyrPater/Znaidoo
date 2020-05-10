<%--
  Created by IntelliJ IDEA.
  User: Elem
  Date: 22.04.2020
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="type" value="${['A', 'B', 'C']}" scope="application" />


<html>
<head>
    <title>Create new devices</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/ajax.js"></script>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <form:form method="POST" modelAttribute="deviceForm" class="form-signin">
        <spring:bind path="serialNumber">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="serialNumber" class="form-control"
                            placeholder="Uniq number" autofocus="true"></form:input>
                <form:errors path="serialNumber"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="type">
            <div class="form-group ${status.error ? 'has-error' : ''}" >
                <form:select type="text" path="type" class="form-control">
                    <form:options items="${type}"/>
                </form:select>
                <form:errors path="type"></form:errors>
            </div>
        </spring:bind>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>
</div>
</body>
</html>




<%--<form:option value="NONE" label="--- Select ---"/>--%>