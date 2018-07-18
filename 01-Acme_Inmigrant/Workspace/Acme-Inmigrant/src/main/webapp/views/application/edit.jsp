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

<form:form action="application/immigrant/edit.do" modelAttribute="application">
	<security:authorize access="hasRole('IMMIGRANT')">
		<form:hidden path="id" />
		<form:hidden path="version" />

		<form:hidden path="ticker" />
		<form:hidden path="openedMoment" />
		<form:hidden path="closedMoment" />
		<form:hidden path="personalSection" />
		<form:hidden path="contactSection" />
		<form:hidden path="workSection" />
		<form:hidden path="socialSection" />
		<form:hidden path="educationSection" />
		
		<acme:textbox path="creditcard.holderName" code="application.creditCard.holderName" />
		<acme:textbox path="creditcard.brandName" code="application.creditCard.brandName" />
		<acme:textbox path="creditcard.number" code="application.creditCard.number" />
		<acme:textbox path="creditcard.expirationMonth" code="application.creditCard.expirationMonth" />
		<acme:textbox path="creditcard.expirationYear" code="application.creditCard.expirationYear" />
		<acme:textbox path="creditcard.CVV" code="application.creditCard.Cvv" />

		<acme:textbox path="personalSection.name" code="application.personalSection.name" />
		<acme:textbox path="personalSection.birthPlace" code="application.personalSection.birthPlace" />
		<acme:date code="application.personalSection.birthDate" path="personalSection.birthDate" placeholder="dd/MM/yyyy"/>
		<acme:textbox path="personalSection.picture" code="application.personalSection.picture" />
		
		<acme:submit name="save" code="application.submit" />
		<acme:cancel url="application/display.do" code="application.cancel" />
	</security:authorize>
</form:form>