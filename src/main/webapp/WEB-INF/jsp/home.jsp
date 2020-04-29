<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Welcome to ZNAIDOO ${pageContext.request.userPrincipal.name}</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/other_script.js"></script>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <style>
        #map {
            width: 100%;
            height: 500px;
        }
    </style>
    <style>
        .layer1{
            position: relative;
        }
        .layer2{
            position: absolute;
        }
        .layer3{
            position: absolute;
            opacity: 0.6;
        }
    </style>
</head>
<body>
<div class="text-center" role="toolbar" aria-label="Toolbar with buttons">

    <a class="btn btn-lg" href="<c:url value='/account?username=${username}'/>"><spring:message code="home.account"/></a>
    <a class="btn btn-lg" onclick='window.location.href="help"'><spring:message code="home.help"/></a>
    <script async defer src="https://maps.googleapis.com/maps/api/js?callback=initMap"></script>
    <a class="btn btn-lg" onclick="document.forms['logoutForm'].submit()"><spring:message code="home.logout"/></a>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout"></form>
    </c:if>

</div>


<div class="layer1">
    <button class="btn btn-default" id="devices-container-button" onclick="show_hide_elem('devices-container')" >
        <spring:message code="home.devices"/></button>

    <div class="layer2" id="map">
        <script type="text/javascript">
            var map;
            function initMap() {
                map = new google.maps.Map(document.getElementById('map'), {
                    center: {lat:49.850, lng:24.000},
                    zoom: 12
                });
            }
        </script>
    </div>

    <div class="layer3 panel panel-default">
        <div class="devices-container" id="devices-container">
            <table class="table table-hover table-bordered table-condensed table-striped">

                <tr>
                    <th><spring:message code="home.typeDevice"/></th>
                    <th><spring:message code="home.nameDevice"/></th>
                    <th><spring:message code="home.timeLinkDevice"/></th>
                    <th><spring:message code="home.batteryDevice"/></th>
                    <th><spring:message code="home.visibleDevice"/></th>
                    <th><spring:message code="home.ownerDevice"/></th>
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
                        <td>${device.user.username}</td>
                        <c:if test="${device.user.username.equals(pageContext.findAttribute('username'))}">
                        <td><a href="<c:url value='editDevice?deviceSN=${device.serialNumber}'/>" class="btn btn-success custom width">
                            <spring:message code="home.editDevice"/></a></td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <button class="btn btn-default" id="add-new-device-button" onclick='window.location.href = "userAddDevice"'>
                <spring:message code="home.addDevice"/></button>
        </div>
    </div>
</div>


</body>
</html>






