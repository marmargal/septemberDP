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

<form:form action="${requestURI }" modelAttribute="decisionForm">
	
	<form:hidden path="id"/>
	<form:hidden path="prueba"/>

	<acme:textbox code="decision.comment" path="comment"/>
	
	<form:label path="approved">
  			<spring:message code="decision.approve" />:
 		</form:label>
		<form:select path="approved">
              <form:option value="true"/>
              <form:option value="false"/>
    	</form:select>
    <br />
    
	<acme:submit name="save" code="decision.save"/>
	<acme:cancel url="redirect:/./" code="decision.cancel"/>
	
</form:form>