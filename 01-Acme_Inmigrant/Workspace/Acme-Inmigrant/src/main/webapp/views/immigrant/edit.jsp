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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${requestURI }" modelAttribute="immigrant">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="userAccount"/>
	<form:hidden path="name" />
	<form:hidden path="surname" />
	<form:hidden path="email" />
	<form:hidden path="phoneNumber" />
	<form:hidden path="address" />
	<form:hidden path="answers" />

<%-- 	<form:select path="investigator">
		<form:options items="${investigator}" itemLabel="name"/>
	</form:select>
	 --%>
		<form:label path="investigator">
		<spring:message code="immigrant.investigator" />
	</form:label>	
	<form:select id="investigator" path="investigator">
		<form:option value="0" label="----" />		
		<form:options items="${investigator}" itemValue="id" itemLabel="name" />
	</form:select>
	<form:errors path="investigator" cssClass="error" />
	
	<input type="submit" name="save" value="<spring:message code="immigrant.submit" />" />&nbsp; 	
	
</form:form>