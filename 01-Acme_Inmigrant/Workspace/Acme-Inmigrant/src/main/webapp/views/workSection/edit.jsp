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

<form:form action="workSection/immigrant/edit.do" modelAttribute="workSection">
	<security:authorize access="hasRole('IMMIGRANT')">
		<form:hidden path="id" />
		<form:hidden path="version" />

		<acme:textbox path="nameCompany" code="workSection.nameCompany" />
		<acme:textbox path="position" code="workSection.position" />
		<acme:date code="workSection.startDate" path="startDate" placeholder="dd/MM/yyyy"/>
		<acme:date code="workSection.endDate" path="endDate" placeholder="dd/MM/yyyy"/>
		
		<acme:submit name="save" code="workSection.submit" />
		<acme:cancel url="application/display.do" code="workSection.cancel" />
	</security:authorize>
</form:form>