<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Edit The Device</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/ajax.js"></script>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>
<%@page pageEncoding="UTF-8"%>

<div class="container">
    <form:form method="POST" modelAttribute="editDeviceForm" class="form-signin">

        <spring:bind path="serialNumber">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input readonly="true" type="text" path="serialNumber" class="form-control"></form:input>
                <form:errors path="serialNumber"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="name" class="form-control"></form:input>
                <form:errors path="name"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="periodLink">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="number" min="1" max="1000" path="periodLink" class="form-control"></form:input>
                <form:errors path="periodLink"></form:errors>
            </div>
        </spring:bind>

        <td>Period link (min-5 max-1,000)</td>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>

        <table class="table table-hover table-bordered table-condensed table-striped">
            <tr>
                <th>Another viewers</th>
                <th width="70"></th>
                <tbody>
                <c:forEach items="${viewers.viewers}"  var="viewer">
                    <tr>
                        <td>${viewer.user.username}</td>
                        <td><a href="/editDevice/delete?id=${viewer.id}&deviceSN=${editDeviceForm.serialNumber}"  class="btn btn-danger custom width">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>

            </tr>
        </table>

        <button class="btn btn-lg btn-warning btn-block" type="button"
                onclick='window.location.href="setViewer?deviceSN=${editDeviceForm.serialNumber}"'>Add Viewer</button>

    </form:form>
</div>


</body>
</html>
