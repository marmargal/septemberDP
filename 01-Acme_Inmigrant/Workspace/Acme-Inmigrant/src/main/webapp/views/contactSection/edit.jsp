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

<form:form action="contactSection/immigrant/edit.do" modelAttribute="contactSection">
	<security:authorize access="hasRole('IMMIGRANT')">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<jstl:if test="${contactSection.id != 0}">
			<form:hidden path="application" />
		</jstl:if>

		<acme:textbox path="email" code="contactSection.email" />
		<acme:textbox path="phoneNumber" code="contactSection.phoneNumber" />
		<acme:textbox path="pageNumber" code="contactSection.pageNumber" />
		
		<jstl:if test="${contactSection.id == 0}">
			<acme:select items="${application}" itemLabel="ticker" code="contactSection.application" path="application" />
		</jstl:if>

		<acme:submit name="save" code="contactSection.submit" />
		<jstl:if test="${contactSection.id != 0}">
			<acme:delete confirmationCode="contactSection.confirm.delete" buttonCode="contactSection.delete" id="${contactSection.id}" />
		</jstl:if>
		<acme:cancel url="application/immigrant/display.do" code="contactSection.cancel" />
	</security:authorize>
</form:form>