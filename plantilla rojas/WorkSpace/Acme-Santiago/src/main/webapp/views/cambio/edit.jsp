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



	<acme:textbox code="cambio.title" path="title" />
	<acme:textbox code="cambio.description" path="description" />
	<acme:textbox code="cambio.gauge" path="gauge" type="number" />



	<!-- Buttons -->


	<acme:submit name="save" code="cambio.submit" />
	<acme:cancel url="/" code="cambio.cancel" />

</form:form>
