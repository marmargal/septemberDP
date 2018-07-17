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
	
	<acme:textbox code="user.name" path="name"/>
	<br/>
	<acme:textbox code="user.surname" path="surname"/>
	<br/>
	<acme:textbox code="user.email" path="email"/>
	<br/>
	<acme:textbox code="user.phone" path="phoneNumber"/>
	<br/>
	<acme:textbox code="user.address" path="address"/>
	<br/>
	<acme:textbox code="user.userName" path="username"/>
	<br/>
	<acme:password code="user.password" path="password"/>
	<br/>
	<acme:password code="user.repeatPassword" path="repeatPassword"/>
	<br/>
	
	<acme:checkbox code="user.acceptTerms" path="termsAndConditions"/>
	
	<a href="terms/list.do"><spring:message code="user.acceptTermsLink"/></a>
	<br />
	
	<acme:submit name="save" code="user.save"/>
	<acme:submit name="cancel" code="user.cancel"/>
	
</form:form>
	




