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

<form:form action="${requestURI}" modelAttribute="banner">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="agent" />

	<security:authorize access="hasRole('AGENT')">
		<acme:textbox code="banner.picture" path="picture" />
		<acme:textbox code="banner.targetPage" path="targetPage" />
		<acme:textbox code="banner.creditCard.holderName" path="creditCard.holderName" />
		<acme:textbox code="banner.creditCard.brandName" path="creditCard.brandName" />
		<acme:textbox code="banner.creditCard.number" path="creditCard.number" />
		<acme:textbox code="banner.creditCard.expirationMonth"
			path="creditCard.expirationMonth" />
		<acme:textbox code="banner.creditCard.expirationYear"
			path="creditCard.expirationYear" />
		<acme:textbox code="banner.creditCard.Cvv" path="creditCard.cvv" />

		<!-- Buttons -->

		<acme:submit name="save" code="banner.submit" />
	</security:authorize>


</form:form>
