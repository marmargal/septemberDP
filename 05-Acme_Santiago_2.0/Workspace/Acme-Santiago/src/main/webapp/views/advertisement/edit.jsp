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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${requestURI }" modelAttribute="advertisement">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="hike"/>
	<form:hidden path="taboo" />
	
	<security:authorize access="hasRole('AGENT')">
		<acme:textbox code="advertisement.title" path="title"/>
		<acme:textbox code="advertisement.banner" path="banner"/>
		<acme:textbox code="advertisement.targetPage" path="targetPage"/>
		<acme:textbox code="advertisement.creditCard.holderName" path="creditCard.holderName" />
		<acme:textbox code="advertisement.creditCard.brandName" path="creditCard.brandName" />
		<acme:textbox code="advertisement.creditCard.number" path="creditCard.number" />
		<acme:textbox code="advertisement.creditCard.expirationMonth" path="creditCard.expirationMonth" />
		<acme:textbox code="advertisement.creditCard.expirationYear" path="creditCard.expirationYear" />
		<acme:textbox code="advertisement.creditCard.Cvv" path="creditCard.cvv" />
		<acme:textbox type= "number" code="advertisement.activeDays" path="activeDays"/>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
	<jstl:if test="${route.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="advertisement.delete" />"
				onclick="return confirm('<spring:message code="advertisement.confirm.delete" />')" />&nbsp;
	</jstl:if>
	</security:authorize>
	
	<acme:submit name="save" code="advertisement.save"/>
	<acme:cancel url="advertisement/list.do" code="advertisement.cancel"/>
	
</form:form>