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

<form:form action="${requestURI }" modelAttribute="pruebaForm">
	
	<form:hidden path="id"/>

	<acme:textbox code="prueba.title" path="title"/>
	<acme:textarea code="prueba.description" path="description"/>
	
	<form:label path="gauge">
  			<spring:message code="prueba.gauge" />:
 		</form:label>
		<form:select path="gauge">
              <form:option value="1"/>
              <form:option value="2"/>
              <form:option value="3"/>
    	</form:select>
    <br />
    
    <acme:select items="${routes}" itemLabel="name" code="prueba.route" path="route"/>
	
	<acme:submit name="save" code="prueba.save"/>
	<acme:cancel url="prueba/user/list.do" code="prueba.cancel"/>
	
</form:form>