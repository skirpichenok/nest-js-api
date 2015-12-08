<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title><spring:message code="nestJsAPI" /> - <spring:message code="homePage" /></title>

	<jsp:include page="include.jsp"/>

	<link href="<spring:url value="/resources/css/home.css"/>" rel="stylesheet" />
	<script src="<spring:url value="/resources/js/home.js"/>"></script>
</head>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="<spring:url value="/home"/>"><spring:message code="nestJsAPI" /></a>
		</div>
	</div>
</nav>

<div class="jumbotron">
	<div class="container">
		<h3><spring:message code="homePage" /></h3>
		<div>
			<form:form action="authorizationCode">
				<button id="authorize-button" class="btn btn-primary btn-lg"><spring:message code="authorize" /></button>
			</form:form>
		</div>
	</div>
</div>

<div class="container">
	<hr>
	<footer>
		<p>&copy; <a href="https://www.linkedin.com/in/sergey-kirpichenok-b4723118" target="_blank"><spring:message code="author" /></a></p>
	</footer>
</div>

</body>
</html>