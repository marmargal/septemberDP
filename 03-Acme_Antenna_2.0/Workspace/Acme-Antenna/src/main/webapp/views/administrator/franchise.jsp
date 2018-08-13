<%--
 * franchise.jsp
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="administrator/franchise.do" modelAttribute="franchise">
	
	<security:authorize access="hasRole('ADMIN')">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<acme:textbox code="franchise.businessName" path="businessName" />
	<acme:textbox code="franchise.banner" path="banner" />
	<acme:textbox code="franchise.welcomeEnglishMessage" path="welcomeEnglishMessage" />
	<acme:textbox code="franchise.welcomeSpanishMessage" path="welcomeSpanishMessage" />
	
	<acme:submit name="save" code="franchise.save" />
	<acme:cancel url="/" code="franchise.cancel" />

	</security:authorize>
</form:form>