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


<security:authorize access="hasRole('USER')">

	<form:form action="tutorial/user/edit.do" modelAttribute="tutorialForm">

		<form:hidden path="id" />

		<br />
		<acme:textbox code="tutorial.title" path="title" />
		<br />
		<acme:textarea code="tutorial.text" path="text" />
		<br />
		<acme:textarea code="tutorial.pictures" path="pictures" />
		<br />

		<input type="submit" name="save"
			value="<spring:message code="tutorial.save"/>" />&nbsp;
			</form:form>
			
</security:authorize>

<security:authorize access="hasRole('ADMIN')">

	<form:form action="tutorial/administrator/edit.do" modelAttribute="tutorialForm">
		<form:hidden path="id" />
		<form:hidden path="title"/>
		<form:hidden path="text"/>
		<form:hidden path="pictures"/>
	
		<input type="submit" name="delete"
			value="<spring:message code="tutorial.delete"/>"
			onclick="return confirm('<spring:message code="tutorial.confirm.delete"/>')" />&nbsp;
			</form:form>
			
</security:authorize>
<hr/>
<acme:cancel url="tutorial/list.do" code="tutorial.cancel" />

