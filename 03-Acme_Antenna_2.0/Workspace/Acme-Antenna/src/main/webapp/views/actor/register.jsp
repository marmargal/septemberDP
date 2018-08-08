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

<form:form action="${requestURI }" modelAttribute="userForm">
	
	<form:hidden path="user.id"/>
	<form:hidden path="user.version"/>
	
	<acme:textbox code="actor.name" path="user.name"/>
	<br/>
	<acme:textbox code="actor.surname" path="user.surname"/>
	<br/>
	<acme:textbox code="actor.email" path="user.email"/>
	<br/>
	<acme:textbox code="actor.phoneNumber" path="user.phoneNumber"/>
	<br/>
	<acme:textbox code="actor.address" path="user.postalAddress"/>
	<br/>
	<acme:textbox code="actor.pictures" path="user.pictures"/>
	<br/>
	<acme:textbox code="actor.userName" path="user.userAccount.username"/>
	<br/>
	<acme:password code="actor.password" path="user.userAccount.password"/>
	<br/>
	<acme:password code="actor.repeatPassword" path="confirmPassword"/>
	<br/>
	
	<jstl:if test="${userForm.user.id == 0}">
   		<form:label path="terms">
		<spring:message code="actor.acceptTerms"/><a href="terms/list.do"><spring:message code="actor.acceptTerms"/></a>
		</form:label>
		<input type="checkbox" id="terms" name="terms" required /> <spring:message code="actor.acceptTermsLink" /><br>
		<form:errors cssClass="error" path="terms"/>
   </jstl:if>
	
	<acme:submit name="save" code="actor.save"/>
	<acme:submit name="cancel" code="actor.cancel"/>
	
</form:form>
	




