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

<form:form action="${requestURI }" modelAttribute="amenityForm">
	
	<form:hidden path="id"/>
	
	<acme:textbox code="amenity.name" path="name"/>
	<acme:textbox code="amenity.description" path="description"/>
	<acme:textarea code="amenity.pictures" path="pictures"/>
	<acme:select items="${inns }" itemLabel="name" code="amenity.inn" path="inn"/>

	<acme:submit name="save" code="amenity.submit"/>
	<jstl:if test="${amenityForm.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="amenity.delete" />"
			onclick="return confirm('<spring:message code="amenity.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<acme:cancel url="amenity/list.do" code="amenity.cancel"/>
	
</form:form>