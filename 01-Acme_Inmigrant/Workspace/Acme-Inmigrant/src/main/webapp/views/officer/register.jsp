<%--
 * register.jsp
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

<form:form action="officer/register.do" modelAttribute="officerForm">
	
	<acme:textbox code="officer.name" path="name"/>
	<br/>
	<acme:textbox code="officer.surname" path="surname"/>
	<br/>
	<acme:textbox code="officer.email" path="email"/>
	<br/>
	<acme:textbox code="officer.phone" path="phoneNumber"/>
	<br/>
	<acme:textbox code="officer.address" path="address"/>
	<br/>
	<acme:textbox code="officer.officerName" path="username"/>
	<br/>
	<acme:password code="officer.password" path="password"/>
	<br/>
	<acme:password code="officer.repeatPassword" path="repeatPassword"/>
	<br/>
	
	<acme:checkbox code="officer.acceptTerms" path="termsAndConditions"/>
	
	<a href="terms/list.do"><spring:message code="officer.acceptTermsLink"/></a>
	<br />
	
	<acme:submit name="save" code="officer.save"/>
	<acme:submit name="cancel" code="officer.cancel"/>
	
</form:form>
	




