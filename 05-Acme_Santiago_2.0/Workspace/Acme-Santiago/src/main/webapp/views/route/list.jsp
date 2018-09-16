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

<spring:message code="route.searchCriteria" />
<form method=GET action="route/search.do">
	Search: <input type="text" name="criteria"> <input
		type="submit">
</form>
<br />

<spring:message code="route.searchLength" />
<form method=GET action="route/lengthSearch.do">
	MIN: <input type="number" name="min"> MAX:<input type="number"
		name="max"> <input type="submit">
</form>
<br />

<spring:message code="route.searchHikes" />
<form method=GET action="route/hikesSearch.do">
	MIN: <input type="number" name="min"> MAX:<input type="number"
		name="max"> <input type="submit">
</form>
<br />

<display:table pagesize="5" class="routes" keepStatus="true"
	name="routes" requestURI="${requestURI }" id="row">

	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<acme:links url="prueba/administrator/list.do?routeId=${row.id}" code="route.list.pruebas"/>
		</display:column>
	</security:authorize>

	<acme:column property="name" code="route.title" />
	<acme:column property="description" code="route.description" />

	<display:column>
		<a href="route/display.do?routeId=${row.id}"><spring:message
				code="route.display" /></a>
	</display:column>

	<security:authorize access="hasRole('USER')">
		<display:column>
			<a href="comment/user/createRoute.do?routeId=${row.id}"><spring:message
					code="route.leaveComments" /></a>
		</display:column>
		<display:column>
			<a href="comment/user/listRoute.do?routeId=${row.id}"><spring:message
					code="route.comments" /></a>
		</display:column>
		<display:column>
		<jstl:if test="${user == row.user }">	
			<a href="route/user/edit.do?routeId=${row.id}"><spring:message
					code="route.edit" /></a>
		</jstl:if>
		</display:column>
		<display:column>
			<jstl:if test="${user!=null }">
				<jstl:if test="${user == row.user }">	
					<a href="walk/user/create.do?routeId=${row.id}"><spring:message
							code="route.instantiate.walk" /></a>
				</jstl:if>
			</jstl:if>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="route/administrator/edit.do?routeId=${row.id}"><spring:message
					code="route.edit" /></a>
		</display:column>
	</security:authorize>

</display:table>

<security:authorize access="hasRole('USER')">
	<acme:links url="route/user/create.do" code="route.create" />
</security:authorize>
