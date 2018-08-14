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

<form method=GET action="route/search.do">
	Search: <input type="text" name="criteria"> <input
		type="submit">
</form>


<display:table pagesize="5" class="routes" keepStatus="true" name="routes"
	requestURI="${requestURI }" id="row">

	<acme:column property="name" code="route.title" />
	<acme:column property="description" code="route.description" />

	<display:column>

		<a href="route/display.do?routeId=${row.id}"><spring:message
				code="route.display" /></a>
	</display:column>
</display:table>