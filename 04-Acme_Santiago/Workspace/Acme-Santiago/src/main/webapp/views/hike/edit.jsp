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

<form:form action="${requestURI }" modelAttribute="hike">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="route"/>
	<form:hidden path="comments"/>
	
	<acme:textbox code="hike.name" path="name"/>
	<acme:textbox code="hike.length" path="length"/>
	<acme:textbox code="hike.description" path="description"/>
	<acme:textbox code="hike.originCity" path="originCity"/>
	<acme:textbox code="hike.destinationCity" path="destinationCity"/>
	<acme:textbox code="hike.pictures" path="pictures"/>
	<form:label path="dificultLevel">
  			<spring:message code="hike.dificultLevel" />:
 		</form:label>
		<form:select path="dificultLevel">
              <form:option value="EASY"/>
              <form:option value="MEDIUM"/>
              <form:option value="DIFFICULT"/>
    	</form:select>
    <br />
	
	<acme:submit name="save" code="hike.save"/>
	<acme:cancel url="hike/list.do" code="hike.cancel"/>
	
</form:form>