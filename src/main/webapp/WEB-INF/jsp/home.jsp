<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%--<c:set var="device" value="${['??????????', 'English']}" scope="application" />--%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Welcome to ZNAIDOO ${pageContext.request.userPrincipal.name}</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/other_script.js"></script>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>



<body>
<div class="text-center" role="toolbar" aria-label="Toolbar with buttons">
    <a class="btn btn-lg" onclick='window.location.href="account"'>Account</a>
    <a class="btn btn-lg" onclick='window.location.href="help"'>Help</a>

    <a class="btn btn-lg" onclick="document.forms['logoutForm'].submit()">Logout</a>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout"></form>
    </c:if>
</div>

<div>
    <button class="btn btn-default" id="devices-container-button" onclick="show_hide_elem('devices-container')" >Devices</button>

    <div class="panel panel-default">
        <div class="devices-container" id="devices-container">
            <table class="table table-hover table-bordered table-condensed table-striped">

                <tr>
                    <th>Type</th>
                    <th>Name</th>
                    <th>Period link</th>
                    <th>Battery %</th>
                    <th>Visible</th>
                    <th>Owner</th>
                    <th width="70"></th>
                </tr>

                <tbody>
                <c:forEach items="${devices}" var="device">
                    <tr>
                        <td>${device.deviceType}</td>
                        <td>${device.name}</td>
                        <td>${device.periodLink}</td>
                        <td>${device.battery}</td>
                        <td><input type="checkbox"></td>
                        <td>${pageContext.request.userPrincipal.name}</td>
                        <td><a href="<c:url value='/${contextPath}/editDevice'/>" class="btn btn-success custom width">Edit</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <button class="btn btn-default" id="add-new-device-button" onclick='window.location.href = "userAddDevice"'>Add devices</button>
        </div>
    </div>
</div>
</body>
</html>






