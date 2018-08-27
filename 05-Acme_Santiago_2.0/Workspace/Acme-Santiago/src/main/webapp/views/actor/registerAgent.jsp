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

<form:form action="${requestURI }" modelAttribute="agentForm">
	
	<form:hidden path="agent.id"/>
	<form:hidden path="agent.version"/>
	
	<acme:textbox code="actor.name" path="agent.name"/>
	<br/>
	<acme:textbox code="actor.surname" path="agent.surname"/>
	<br/>
	<acme:textbox code="actor.email" path="agent.email"/>
	<br/>
	<acme:textbox code="actor.phoneNumber" path="agent.phoneNumber"/>
	<br/>
	<acme:textbox code="actor.address" path="agent.postalAddress"/>
	<br/>
	<acme:textbox code="actor.pictures" path="agent.pictures"/>
	<br/>
	<acme:textbox code="actor.userName" path="agent.userAccount.username"/>
	<br/>
	<acme:password code="actor.password" path="agent.userAccount.password"/>
	<br/>
	<acme:password code="actor.repeatPassword" path="confirmPassword"/>
	<br/>
	
	<jstl:if test="${agentForm.agent.id == 0}">
   		<form:label path="terms">
		<spring:message code="actor.acceptTerms"/><a href="terms/list.do"><spring:message code="actor.acceptTerms"/></a>
		</form:label>
		<input type="checkbox" id="terms" name="terms" required /> <spring:message code="actor.acceptTermsLink" /><br>
		<form:errors cssClass="error" path="terms"/>
   </jstl:if>
	
	<acme:submit name="save" code="actor.save"/>
	<acme:submit name="cancel" code="actor.cancel"/>
	
</form:form>