<%--
 * create.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${requestURI }" modelAttribute="compostela">
	<security:authorize access="hasRole('ADMIN')">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="walk" />
		<form:hidden path="user" />
		<jstl:if test="${test==true }">
			<b> <spring:message code="compostela.true" />
			</b>
			<br />
		</jstl:if>
		<jstl:if test="${test==false }">
			<b><spring:message code="compostela.false" /></b>
			<br />
		</jstl:if>
		<jstl:if test="${days==true }">
			<b> <spring:message code="compostela.days.true" />
			</b>
			<br />
		</jstl:if>
		<jstl:if test="${days==false }">
			<b><spring:message code="compostela.days.false" /></b>
			<br />
		</jstl:if>

		<b><spring:message code="compostela.route" />:&nbsp;<jstl:out
				value="${route.name }"></jstl:out></b>
		<br />

		<jstl:forEach items="${map }" var="h">
			<b><spring:message code="hike.originCity" />:&nbsp;</b>
			<jstl:out value="${h.key.originCity }"></jstl:out>
			<b><spring:message code="hike.destinationCity" />:&nbsp;</b>
			<jstl:out value="${h.key.destinationCity }"></jstl:out>
			<b><spring:message code="registry.inn" />:&nbsp;</b>
			<jstl:out value="${h.value.inn.name }"></jstl:out>
			<b><spring:message code="registry.date" />:&nbsp;</b>
			<jstl:out value="${h.value.date }"></jstl:out>
		</jstl:forEach>



		<acme:checkbox code="compostela.approveDecision" path="decision" />
		<acme:textbox code="compostela.justification" path="justification" />
		<br />
		<acme:checkbox code="compostela.finallyDecision"
			path="finallyDecision" />

		<acme:submit name="save" code="compostela.save" />
		<acme:cancel url="/" code="compostela.cancel" />
	</security:authorize>
</form:form>