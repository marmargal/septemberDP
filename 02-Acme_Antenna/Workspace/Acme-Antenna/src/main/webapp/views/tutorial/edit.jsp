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

<form:form action="${requestUri}" modelAttribute="tutorialForm">

	<form:hidden path="id" />

	<br/>
	<acme:textbox code="tutorial.title" path="title" />
	<br/>
	<acme:textarea code="tutorial.text" path="text" />
	<br/>
	<acme:textarea code="tutorial.pictures" path="pictures" />
	<br/>

	<security:authorize access="hasRole('USER')">
		<input type="submit" name="save" 
			value="<spring:message code="tutorial.save"/>"/>&nbsp;
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<input type="submit" name="delete" 
			value="<spring:message code="tutorial.delete"/>"
			onclick="return confirm('<spring:message code="tutorial.confirm.delete"/>')" />&nbsp;
	</security:authorize>
		
	<acme:cancel url="tutorial/list.do" code="tutorial.cancel" />

</form:form>