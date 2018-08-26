<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('USER')">
<center>
	<img class="imagen" src="images/logo.jpg" />
	<h1>
		<jstl:out value="${user.name }" />
	</h1>
	<h5>
		<jstl:out value="${compostela.date }" />
	</h5>

	<hr />
	<h2>
		<spring:message code="compostela.user.details" />
	</h2>
	<ul>
		<li><jstl:out value="${user.name }" /></li>
		<li><jstl:out value="${user.surname }" /></li>
		<li><jstl:out value="${user.email }" /></li>
		<li><jstl:out value="${user.phoneNumber }" /></li>
		<li><jstl:out value="${user.address }" /></li>
		<li><jstl:out value="${user.postalAddress }" /></li>
	</ul>
	<jstl:forEach var="p" items="${user.pictures }">
		<img class="imagen" src="${p }" />
	</jstl:forEach>
	<h2>
		<spring:message code="compostela.inn.badges" />
	</h2>
	<img class="imagen" src="${compostela.walk.inn.badge}" />

	<hr />

</center>
</security:authorize>