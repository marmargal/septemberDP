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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${requestURI}" modelAttribute="notice">

	<security:authorize access="hasRole('VOLUNTARY')">
		
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="voluntary" />
		<form:hidden path="date" />
		<form:hidden path="discarded" />


		<acme:textbox code="notice.description" path="description" />
		<form:label path="type">
		<spring:message code="notice.type" />:
		</form:label>
		<form:select path="type">
		<form:option value="DOG" />
		<form:option value="CAT" />
		<form:option value="BIRD" />
		</form:select>
		
		<br/>
		<form:label path="level">
		<spring:message code="notice.level" />:
		</form:label>
		<form:select path="level">
		<form:option value="1" />
		<form:option value="2" />
		<form:option value="3" />
		</form:select>
		<acme:textbox code="notice.gpsCoordinates.latitude" path="gpsCoordinates.latitude" />
		<acme:textbox code="notice.gpsCoordinates.longitude" path="gpsCoordinates.longitude" />
		
		<!-- Buttons -->

		<acme:submit name="save" code="notice.submit" />
		<acme:cancel url="/" code="notice.cancel" />
	</security:authorize>

</form:form>