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

<form:form action="${requestURI }" modelAttribute="route">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="comments"/>
	<form:hidden path="user"/>
	
	<acme:textbox code="route.name" path="name"/>
	<acme:textbox type="number" code="route.length" path="length"/>
	<acme:textbox code="route.description" path="description"/>
	<acme:textbox type ="url" code="route.pictures" path="pictures"/>
	<acme:select items="${hikes }" itemLabel="name" code="route.hikes" path="hikes"/>
	
	<acme:submit name="save" code="route.save"/>
	<security:authorize access="hasAnyRole('ADMIN', 'USER')">
	<jstl:if test="${route.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="route.delete" />"
				onclick="return confirm('<spring:message code="route.confirm.delete" />')" />&nbsp;
	</jstl:if>
	</security:authorize>
	<security:authorize access="hasRole('USER')">
	<jstl:if test="${route.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="route.delete" />"
				onclick="return confirm('<spring:message code="route.confirm.delete" />')" />&nbsp;
	</jstl:if>
	</security:authorize>
	<acme:cancel url="route/list.do" code="route.cancel"/>
	
</form:form>