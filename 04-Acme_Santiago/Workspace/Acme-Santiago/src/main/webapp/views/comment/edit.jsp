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

<form:form action="${requestURI}" modelAttribute="comment">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="user" />
	<form:hidden path="taboo" />

	<security:authorize access="hasRole('USER')">
		<acme:textbox code="comment.title" path="title" />
		<acme:textbox code="comment.text" path="text" />
		<acme:textbox code="comment.pictures" path="pictures" />
		<acme:textbox code="comment.rating" path="rating" />



		<!-- Buttons -->

		<acme:submit name="save" code="comment.submit" />
		<acme:cancel url="/" code="comment.cancel" />
	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">
		<b><spring:message code="comment.title" />:&nbsp;</b>
		<jstl:out value="${comment.title}" />
		<br />

		<b><spring:message code="comment.text" />:&nbsp;</b>
		<jstl:out value="${comment.text}" />
		<br />

		<b><spring:message code="comment.rating" />:&nbsp;</b>
		<jstl:out value="${comment.rating}" />
		<br />

		<b><spring:message code="comment.pictures" />:&nbsp;</b>
		<jstl:forEach items="${comment.pictures }" var="p">
			<img class="imagen" src="${p }" />
			<br />
		</jstl:forEach>
		<br />

		<acme:delete confirmationCode="comment.confirm.delete"
			buttonCode="comment.delete" id="${comment.id}" />
		<acme:cancel url="/" code="comment.cancel" />
	</security:authorize>

</form:form>
