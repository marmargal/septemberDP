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

<form:form action="socialSection/immigrant/edit.do" modelAttribute="socialSection">
	<security:authorize access="hasRole('IMMIGRANT')">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<jstl:if test="${socialSection.id != 0}">
			<form:hidden path="application" />
		</jstl:if>

		<acme:textbox path="nickName" code="socialSection.nickName" />
		<acme:textbox path="socialNetwork" code="socialSection.socialNetwork" />
		<acme:textbox path="profileLink" code="socialSection.profileLink" />
		
		<jstl:if test="${socialSection.id == 0}">
			<acme:select items="${application}" itemLabel="ticker" code="socialSection.application" path="application" />
		</jstl:if>
		
		<acme:submit name="save" code="socialSection.submit" />
		<jstl:if test="${socialSection.id != 0 && socialSection.application.socialSection.size() > 1}">
			<acme:delete confirmationCode="socialSection.confirm.delete" buttonCode="socialSection.delete" id="${socialSection.id}" />
		</jstl:if>
		<acme:cancel url="application/immigrant/display.do" code="socialSection.cancel" />
	</security:authorize>
</form:form>