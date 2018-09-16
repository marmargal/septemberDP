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

<form:form action="${requestUri}" modelAttribute="cambio">



	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="identifier" />
	<form:hidden path="moment" />
	<form:hidden path="user" />
	<form:hidden path="decision" />




	<acme:textbox code="cambio.title" path="title" />
	<acme:textarea code="cambio.description" path="description" />
	<acme:textbox code="cambio.gauge" path="gauge" type="number" />
	<acme:select items="${routes}" itemLabel="name" code="cambio.route"
		path="route" />


	<!-- Buttons -->


	<acme:submit name="save" code="cambio.submit" />
	<acme:delete confirmationCode="cambio.confirm.delete"
			buttonCode="cambio.delete" id="${cambio.id}" />
	<acme:cancel url="/" code="cambio.cancel" />

</form:form>
