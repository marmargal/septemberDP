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

<form:form action="${requestUri}" modelAttribute="report">

	<security:authorize access="hasRole('EMPLOYEE')">
		
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="application" />
		<form:hidden path="makeMoment" />


		<acme:textbox code="report.description" path="description" />
		<acme:selectBoolean code="report.suitable" path="suitable" items="${suitables }"/>
		
		
		<!-- Buttons -->

		<acme:submit name="save" code="report.submit" />
		<acme:cancel url="../../" code="report.cancel" />
	</security:authorize>

</form:form>