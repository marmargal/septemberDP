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

<display:table pagesize="5" class="hikes" keepStatus="true"
	name="hikes" requestURI="${requestURI }" id="row">

	<acme:column property="name" code="hike.name" />
	<acme:column property="length" code="hike.length" />
	<acme:column property="description" code="hike.description" />
	<acme:column property="originCity" code="hike.originCity" />
	<acme:column property="destinationCity" code="hike.destinationCity" />
	<acme:column property="pictures" code="hike.pictures" />
	<acme:column property="dificultLevel" code="hike.dificultLevel" />

	<security:authorize access="hasRole('USER')">
	<display:column>
			<a href="hike/user/edit.do?hikeId=${row.id}"><spring:message
					code="hike.edit" /></a>
		</display:column>
	</security:authorize>
</display:table>

<security:authorize access="hasRole('USER')">
	<acme:links url="hike/user/create.do" code="hike.create" />
</security:authorize>
