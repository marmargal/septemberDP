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
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${requestURI }" modelAttribute="requirementForm">
	
	<form:hidden path="id"/>
	
	<acme:textbox code="requirement.title" path="title"/>
	<br/>
	
	<acme:textarea code="requirement.description" path="description"/>
	<br/>
	
	<acme:checkbox code="requirement.abrogated" path="abrogated"/>
	<br/>
	
	<acme:select items="${laws}" itemLabel="title" code="requirement.law" path="lawId"/>
	
	<acme:submit name="save" code="requirement.save"/>
	<acme:cancel url="requirement/administrator/list.do" code="requirement.cancel"/>
	
	<jstl:if test="${requirementForm.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="requirement.delete" />"
			onclick="return confirm('<spring:message code="requirement.confirm.delete" />')" />&nbsp;
	</jstl:if>	
</form:form>