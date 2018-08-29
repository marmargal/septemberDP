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

<form:form action="${requestURI }" modelAttribute="walk">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="route"/>
	<form:hidden path="comments"/>
	
	<acme:textbox code="walk.title" path="title"/>
<%-- 	<acme:textbox code="walk.comments" path="comments"/> --%>
	<acme:select items="${inns }" itemLabel="name" code="walk.inn" path="inn"/>
	<acme:textbox code="walk.daysOfEachHike" path="daysOfEachHike" placeholder="dd/mm/yyyy,dd/mm/yyyy"/>
	
	<acme:submit name="save" code="walk.save"/>
	<acme:cancel url="/" code="walk.cancel"/>
	
</form:form>