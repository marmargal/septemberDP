Alfonso Soldado, [16.09.18 19:11]
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

<display:table pagesize="5" class="cambios" keepStatus="true"
	name="cambios" requestURI="${requestUri }" id="row">

	<jstl:if test="${row.gauge == 1}">
		<spring:message code="cambio.gauge1" var="gaugeNumber" />
	</jstl:if>
	<jstl:if test="${row.gauge == 2}">
		<spring:message code="cambio.gauge2" var="gaugeNumber" />
	</jstl:if>
	<jstl:if test="${row.gauge == 3}">
		<spring:message code="cambio.gauge3" var="gaugeNumber" />
	</jstl:if>

	<spring:message code="cambio.identifier" var="atributo1Header" />
	<display:column property="identifier" title="${atributo1Header}"
		sortable="true" class="${gaugeNumber}" />

	<spring:message code="cambio.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true"
		class="${gaugeNumber}" />

	<spring:message code="cambio.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}"
		sortable="true" class="${gaugeNumber}" />

	<spring:message code="cambio.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}"
		sortable="true" class="${gaugeNumber}" />

	<spring:message code="cambio.gauge" var="gaugeHeader" />
	<display:column property="gauge" title="${gaugeHeader}" sortable="true"
		class="${gaugeNumber}" />

	<spring:message code="cambio.route" var="routeHeader" />
	<display:column property="route.name" title="${routeHeader}"
		sortable="true" class="${gaugeNumber}" />

	<security:authorize access="hasRole('USER')">
		<jstl:if test="${row.decision==null }">
		<display:column title="${gaugeHeader}" sortable="true"
		class="${gaugeNumber}" >
			<a href="cambio/user/edit.do?cambioId=${row.id}"><spring:message
					code="cambio.edit" /></a>
		</display:column>
		</jstl:if>
	</security:authorize>

</display:table>

<acme:links code="cambio.create" url="cambio/user/create.do"></acme:links>
