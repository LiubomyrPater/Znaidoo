<%--
  Created by IntelliJ IDEA.
  User: Elem
  Date: 14.04.2020
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<c:set var="countryList" value="${['Ukraine', 'Poland']}" scope="application" />
<c:set var="language" value="${['Українська', 'English']}" scope="application" />



<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create an account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>

<body>
<%@page pageEncoding="utf-8"%>

<div class="container">

    <form:form method="POST" modelAttribute="changeForm" class="form-signin">
        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input readonly="true" type="text" path="username" class="form-control"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="email" class="form-control" autofocus="true" placeholder="Email"></form:input>
                <form:errors path="email"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="phoneNumber">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="tel" path="phoneNumber" class="form-control" placeholder="Phone"></form:input>
                <form:errors path="phoneNumber"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="country">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:select type="text" path="country" class="form-control">
                    <form:option value="--- Select ---" label="--- Select ---"/>
                    <form:options items="${countryList}"/>
                </form:select>
                <form:errors path="country"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="language">
            <div class="form-group ${status.error ? 'has-error' : ''}" >
                <form:select type="text" path="language" class="form-control">
                    <form:option value="--- Select ---" label="--- Select ---"/>
                    <form:options items="${language}"/>
                </form:select>
                <form:errors path="language"></form:errors>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>

