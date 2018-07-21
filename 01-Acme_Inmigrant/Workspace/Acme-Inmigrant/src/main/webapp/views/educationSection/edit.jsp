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

<form:form action="educationSection/immigrant/edit.do" modelAttribute="educationSection">
	<security:authorize access="hasRole('IMMIGRANT')">
		<form:hidden path="id" />
		<form:hidden path="version" />

		<acme:textbox path="nameDegree" code="educationSection.nameDegree" />
		<acme:textbox path="institution" code="educationSection.institution" />
		<acme:date code="educationSection.dateAwarded" path="dateAwarded" placeholder="dd/MM/yyyy"/>
		<form:label path="level">
  			<spring:message code="educationSection.level" />:
 		</form:label>
		<form:select path="level">
              <form:option value="NONE"/>
              <form:option value="ELEMENTARY"/>
              <form:option value="PRIMARY"/>
              <form:option value="SECONDARY"/>
              <form:option value="HIGH"/>
              <form:option value="BACHELOR"/>
              <form:option value="UNIVERSITY_DEGREE"/>
              <form:option value="MASTER"/>
              <form:option value="DOCTORATE"/>
    	</form:select>
    	<br>
		
		<acme:submit name="save" code="educationSection.submit" />
		<acme:cancel url="application/display.do" code="educationSection.cancel" />
	</security:authorize>
</form:form>