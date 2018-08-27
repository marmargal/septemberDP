<%--
 * list.jsp
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

<display:table pagesize="5" class="compostelas" keepStatus="true"
	name="compostelas" requestURI="${requestURI }" id="row">
	<security:authorize access="hasRole('ADMIN')">

		<acme:column property="user.name" code="compostela.user" />

		<display:column>
			<a href="compostela/administrator/edit.do?compostelaId=${row.id}"><spring:message
					code="compostela.edit" /></a>
		</display:column>

	</security:authorize>
	<security:authorize access="hasRole('USER')">
		<acme:column property="user.name" code="compostela.user" />
		<acme:column property="date" code="compostela.date" />

		<display:column>
			<a href="compostela/user/display.do?compostelaId=${row.id}"><spring:message
					code="compostela.display" /></a>
		</display:column>
	</security:authorize>
</display:table>