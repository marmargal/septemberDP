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

<form:form action="${requestURI}" modelAttribute="innForm">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="inkeeper" />
	<form:hidden path="name" />
	<form:hidden path="badge" />
	<form:hidden path="address" />
	<form:hidden path="phoneNumber" />
	<form:hidden path="email" />
	<form:hidden path="webSite" />
	<form:hidden path="creditCard" />

	<acme:date code="inn.register" path="date" placeholder="dd/MM/yyyy" />


	<!-- Buttons -->


	<acme:submit name="save" code="inn.submit" />
	<acme:cancel url="/" code="inn.cancel" />

</form:form>
