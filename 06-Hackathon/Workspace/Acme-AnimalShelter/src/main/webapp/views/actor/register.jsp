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

<form:form action="${requestURI }" modelAttribute="actorForm">
	
	<form:hidden path="id"/>
	
	<security:authorize access="hasRole('BOSS')">
		<jstl:if test="${requestURI == 'employee/boss/register.do' }">
			<acme:select items="${centers }" itemLabel="name" code="actor.center" path="center"/>
		</jstl:if>
	</security:authorize>
	
	<acme:textbox code="actor.name" path="name"/>
	<br/>
	<acme:textbox code="actor.surname" path="surname"/>
	<br/>
	<acme:textbox code="actor.email" path="email"/>
	<br/>
	<acme:textbox code="actor.phoneNumber" path="phoneNumber"/>
	<br/>
	
	<jstl:if test="${actorForm.aceptPhoneNumberConditions == false }">
		<acme:checkbox code="actor.acceptPhone" path="aceptPhoneNumberConditions"/>
		<br />
	</jstl:if>
	
	<acme:textbox code="actor.address" path="address"/>
	<br/>
	<acme:textbox code="actor.userName" path="username"/>
	<br/>
	<acme:password code="actor.password" path="password"/>
	<br/>
	<acme:password code="actor.repeatPassword" path="repeatPassword"/>
	<br/>
	
	<jstl:if test="${requestURI == 'client/register.do' or requestURI == 'voluntary/register.do' or requestURI == 'veterinary/boss/register.do' or requestURI == 'boss/boss/register.do' or requestURI == 'employee/boss/register.do'}">
		<acme:checkbox code="actor.acceptTerms" path="termsAndConditions"/>
		<a href="terms/list.do"><spring:message code="actor.acceptTermsLink"/></a>
		<br />
	</jstl:if>
	
	<acme:submit name="save" code="actor.save"/>
	<acme:cancel url="./" code="actor.cancel"/>
	
</form:form>
	




