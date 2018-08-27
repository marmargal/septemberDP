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
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="walk"/>
	<form:hidden path="user"/>
	

	<acme:checkbox code="compostela.approveDecision" path="decision"/>
	<acme:textbox code="compostela.justification" path="justification"/>
	<br/>
	<acme:checkbox code="compostela.finallyDecision" path="finallyDecision"/>
	
	<acme:submit name="save" code="compostela.save"/>
	<acme:cancel url="/" code="compostela.cancel"/>
	</security:authorize>
</form:form>