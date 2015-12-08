<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><spring:message code="nestJsAPI" /> - <spring:message code="dashboardPage" /></title>

    <jsp:include page="include.jsp"/>

    <link href="<spring:url value="/resources/css/dashboard.css"/>" rel="stylesheet" />
    <script src="<spring:url value="/resources/js/dashboard.js"/>"></script>
</head>


<spring:url value="/resources/image/" var="imagesPath"/>

<input type="hidden" id="imagesPath" value="${imagesPath}"/>
<input type="hidden" id="token" value="${authDetails.accessToken}"/>
<input type="hidden" id="resetUrl" value="<spring:url value="/reset"/>"/>
<input type="hidden" id="fireBaseHost" value="${authDetails.fireBaseHost}"/>
<input type="hidden" id="beepUrl" value="<spring:url value="/resources/mp3/beep.mp3"/>"/>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="<spring:url value="/home"/>"><spring:message code="nestJsAPI" /></a>
        </div>
    </div>
</nav>

<div class="jumbotron">
    <div class="container">
        <h3><spring:message code="dashboardPage" /></h3>
    </div>
</div>

<div class="container">
    <div id="devicesRow" class="row text-center">
        <div id="smokeCoAlarm" class="hidden">
            <script>parseSmokeCoAlarmJsonData('${smokeCoAlarmJsonData}');</script>
            <h2><spring:message code="smokeCoAlarmTitle" /></h2>
            <div>
                <img id="smokeCoAlarmImg" src="${imagesPath}smoke_device_ok.png" alt="" class="img-rounded">
                <span id="smokeCoAlarmExclImg" class="alarm unvisible">!</span>
            </div>
        </div>
    </div>
    <hr>
    <footer>
        <p>&copy; <a href="https://www.linkedin.com/in/sergey-kirpichenok-b4723118" target="_blank"><spring:message code="author" /></a></p>
    </footer>
</div>

</body>
</html>