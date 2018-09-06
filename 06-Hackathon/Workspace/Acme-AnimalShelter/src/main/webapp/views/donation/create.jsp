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

<form:form action="${requestURI}" modelAttribute="donation">

	<security:authorize access="hasAnyRole('VOLUNTARY','CLIENT')">
		
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="voluntary" />
		<form:hidden path="client" />
		<form:hidden path="event" />

		<acme:textbox code="donation.cuantity" path="cuantity" />
		<acme:textbox code="donation.name" path="name" />
		<acme:textbox code="donation.creditCard.holderName" path="creditCard.holderName" />
		<acme:textbox code="donation.creditCard.brandName" path="creditCard.brandName" />
		<acme:textbox code="donation.creditCard.number" path="creditCard.number" />
		<acme:textbox code="donation.creditCard.expirationMonth" path="creditCard.expirationMonth" />
		<acme:textbox code="donation.creditCard.expirationYear" path="creditCard.expirationYear" />
		<acme:textbox code="donation.creditCard.cvv" path="creditCard.cvv" />
		
		<!-- Buttons -->

		<acme:submit name="save" code="donation.submit" />
		<acme:cancel url="/" code="donation.cancel" />
	</security:authorize>

</form:form>