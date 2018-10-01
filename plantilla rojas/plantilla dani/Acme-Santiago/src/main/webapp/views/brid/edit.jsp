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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${requestURI}" modelAttribute="brid">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="identifier" />
	<form:hidden path="moment" />
	<form:hidden path="user" />
	<form:hidden path="administrator" />
	
	<security:authorize access="hasRole('USER')">

		<acme:textbox code="brid.title" path="title" />
		<acme:textbox code="brid.description" path="description" />
		<acme:textbox code="brid.gauge" path="gauge" type="number" />
		<acme:select items="${routes}" itemLabel="name" code="brid.route" path="route"/>
		
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
	
		<form:hidden path="title" />
		<form:hidden path="description" />
		<form:hidden path="gauge" />
	
		<acme:checkbox code="brid.approved" path="approved"/>
		<acme:textbox code="brid.justification" path="justification" />
	
	</security:authorize>

	<!-- Buttons -->

	<acme:submit name="save" code="brid.submit" />
	
		<security:authorize access="hasRole('USER')">
		<jstl:if test="${brid.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="brid.delete" />"
				onclick="return confirm('<spring:message code="brid.confirm.delete" />')" />&nbsp;
		</jstl:if>
</security:authorize>
	
	<acme:cancel url="/" code="brid.cancel" />

</form:form>
