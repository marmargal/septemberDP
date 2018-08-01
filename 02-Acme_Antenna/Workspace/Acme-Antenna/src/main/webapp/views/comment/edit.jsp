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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('USER')">
	<form:form action="comment/user/create.do" modelAttribute="comment">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="tutorial" />

		<acme:textbox code="comment.title" path="title" />
		<acme:textbox code="comment.text" path="text" />
		<acme:textbox code="comment.pictures" path="pictures" />

		<spring:message code="comment.confirmation.text" />
		<acme:submit name="save" code="comment.submit" />
		<acme:cancel url="tutorial/user/list.do" code="comment.cancel" />
	</form:form>
</security:authorize>


<security:authorize access="hasRole('ADMIN')">
	<form:form action="comment/administrator/edit.do"
		modelAttribute="comment">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="title" />
		<form:hidden path="text" />
		<form:hidden path="pictures" />
		<form:hidden path="tutorial" />
		<form:hidden path="user" />


		<acme:cancel url="/" code="comment.cancel" />
		<input type="submit" name="delete"
				value="<spring:message code="comment.delete" />"
				onclick="return confirm('<spring:message code="comment.confirmation.text.delete" />')" />&nbsp;
	</form:form>
</security:authorize>