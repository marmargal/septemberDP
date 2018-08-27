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

<b><spring:message code="route.name" />:&nbsp;</b>
<jstl:out value="${route.name}" />
<br />

<b><spring:message code="route.length" />:&nbsp;</b>
<jstl:out value="${route.length}" />
<br />

<b><spring:message code="route.description" />:&nbsp;</b>
<jstl:out value="${route.description}" />
<br />

<b><spring:message code="route.pictures" /></b>
<br />
<jstl:forEach var="p" items="${route.pictures }">
	<h4>
		<img class="imagen" src="${p }" /> <br />
	</h4>
</jstl:forEach>
<br />
<h4>
	<spring:message code="route.hikes" />
	:&nbsp;
</h4>
<jstl:forEach var="h" items="${route.hikes }">
	<jstl:out value="${h.name}" />
	<br />
</jstl:forEach>
<br />