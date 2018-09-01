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

<form:form action="${requestUri}" modelAttribute="configuration">

	<acme:textbox code="configuration.banner" path="banner" />
	<acme:textbox code="configuration.englishWelcome" path="englishWelcome" />
	<acme:textbox code="configuration.spanishWelcome" path="spanishWelcome" />
	<acme:textbox code="configuration.countryCode" path="countryCode" />
	
	<acme:submit name="save" code="configuration.submit" />
	<acme:cancel url="/" code="configuration.cancel" />

</form:form>
