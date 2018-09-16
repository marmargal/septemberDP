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

<display:table pagesize="5" class="pruebas" keepStatus="true" name="pruebas"
	requestURI="${requestURI }" id="row">
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<jstl:if test="${row.decision == null }">
				<acme:links url="decision/administrator/create.do?pruebaId=${row.id }" code="prueba.create.decision"/>
			</jstl:if>
			<jstl:if test="${row.decision != null }">
				<acme:links url="decision/administrator/display.do?decisionId=${row.decision.id }" code="prueba.display.decision"/>
			</jstl:if>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('USER')">
		<display:column>
			<jstl:if test="${row.decision != null }">
				<acme:links url="decision/user/display.do?decisionId=${row.decision.id }" code="prueba.display.decision"/>
			</jstl:if>
			
			<jstl:if test="${row.decision == null }">
				<jstl:out value="-"></jstl:out>
			</jstl:if>
		</display:column>
		
		<display:column>
			<jstl:if test="${row.route == null }">
				<acme:links url="prueba/user/edit.do?pruebaId=${row.id }" code="prueba.edit"/>
			</jstl:if>
			
			<jstl:if test="${row.route != null }">
				<jstl:out value="-"></jstl:out>
			</jstl:if>
		</display:column>
		
		<display:column>
			<jstl:if test="${row.route == null }">
				<form name="submitForm" method="POST" action="prueba/user/delete.do?pruebaId=${row.id }">
			    	<acme:submit name="delete" code="prueba.delete"/>
				</form>
			</jstl:if>
			
			<jstl:if test="${row.route != null }">
				<jstl:out value="-"></jstl:out>
			</jstl:if>
		</display:column>
		
		<display:column>
			<jstl:if test="${row.route != null }">
				<acme:links url="route/display.do?routeId=${row.route.id }" code="prueba.route"/>
			</jstl:if>
			
			<jstl:if test="${row.route == null }">
				<jstl:out value="-"></jstl:out>
			</jstl:if>
		</display:column>
	</security:authorize>
	
	<acme:column property="tracer" code="prueba.tracer" />
	
	<spring:message var="formatDate" code="prueba.format.date"/>
	<spring:message code="prueba.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}" format="${formatDate}" sortable="true" />
	
	<acme:column property="title" code="prueba.title" />
	<acme:column property="description" code="prueba.description" />
	<acme:column property="gauge" code="prueba.gauge" />
	
</display:table>

<security:authorize access="hasRole('USER')">
	<acme:links url="prueba/user/create.do" code="prueba.create" />
</security:authorize>
