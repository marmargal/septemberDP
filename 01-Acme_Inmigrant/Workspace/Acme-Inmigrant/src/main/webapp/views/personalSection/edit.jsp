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

<form:form action="personalSection/immigrant/edit.do" modelAttribute="personalSection">
	<security:authorize access="hasRole('IMMIGRANT')">
		<form:hidden path="id" />
		<form:hidden path="version" />

		<acme:textbox path="names" code="personalSection.name" />
		<acme:textbox path="birthPlace" code="personalSection.birthPlace" />
		<acme:date code="personalSection.birthDate" path="birthDate" placeholder="dd/MM/yyyy"/>
		<acme:textbox path="picture" code="personalSection.picture" />
		
		<acme:submit name="save" code="personalSection.submit" />
		<acme:cancel url="application/display.do" code="personalSection.cancel" />
	</security:authorize>
</form:form>