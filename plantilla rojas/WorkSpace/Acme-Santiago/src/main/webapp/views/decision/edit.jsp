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

<form:form action="${requestUri}" modelAttribute="decision">



	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="administrator" />
	<form:hidden path="cambio" />




	<acme:textbox code="decision.comment" path="comment" />
	<acme:selectdecision code="decision.approve" path="approve"
		items="${aproves }" />



	<!-- Buttons -->


	<acme:submit name="save" code="decision.submit" />
	<acme:cancel url="/" code="decision.cancel" />

</form:form>
