<%--
 * edit.jsp
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
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="decision/offices/edit.do" modelAttribute="decisionForm">
	<security:authorize access="hasRole('OFFICER')">
	
		<form:hidden path="id" />
		<form:hidden path="version" />

		<form:hidden path="moment" />
		
		<acme:select items="${applications }" itemLabel="applications" code="decision.application" path="application"/>
		<br/>
		<acme:checkbox code="decision.isAccepted" path="isAccepted"/>
		<br/>
		<acme:textbox path="comment" code="decision.comment" />
		<br/>
		
		
		<acme:submit name="save" code="decision.save" />
		<acme:submit name="cancel" code="decision.cancel" />
	</security:authorize>
</form:form>