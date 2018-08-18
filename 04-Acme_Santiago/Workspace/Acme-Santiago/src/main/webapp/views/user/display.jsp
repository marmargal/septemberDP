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

<b><spring:message code="user.name" />:&nbsp;</b>
<jstl:out value="${user.name}" />
<br />

<b><spring:message code="user.surname" />:&nbsp;</b>
<jstl:out value="${user.surname}" />
<br />

<b><spring:message code="user.email" />:&nbsp;</b>
<jstl:out value="${user.email}" />
<br />

<b><spring:message code="user.phoneNumber" />:&nbsp;</b>
<jstl:out value="${user.phoneNumber}" />
<br />

<b><spring:message code="user.address" />:&nbsp;</b>
<jstl:out value="${user.address}" />
<br />

<b><spring:message code="user.postalAddress" />:&nbsp;</b>
<jstl:out value="${user.postalAddress}" />
<br />

<b><spring:message code="user.pictures" /></b>
<br />
<jstl:forEach var="p" items="${user.pictures }">
	<h4>
		<img class="imagen" src="${p }" /> <br />
	</h4>
</jstl:forEach>
<br />

<h4>
	<spring:message code="user.routes" />
	:&nbsp;
</h4>
<jstl:forEach var="h" items="${user.routes }">
	<jstl:out value="${h.name}" />
	<a href="route/display.do?routeId=${h.id}"><spring:message
			code="route.display" /></a>
	<br />
</jstl:forEach>
<br />