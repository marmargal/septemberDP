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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="cambios" requestURI="${requestURI}" id="row">
	
	<!-- Attributes -->
	
		<jstl:if test="${row.gauge == 1}">
    	<spring:message code="cambio.gauge1" var="gaugeNumber" />
 		</jstl:if>
 		<jstl:if test="${row.gauge == 2}">
 	  	<spring:message code="cambio.gauge2" var="gaugeNumber" />
 		</jstl:if>
 		<jstl:if test="${row.gauge == 3}">
   		<spring:message code="cambio.gauge3" var="gaugeNumber" />
 		</jstl:if>

		<spring:message code="cambio.identifier" var="identifierHeader" />
		<display:column property="identifier" title="${identifierHeader}" sortable="false" class="${gaugeNumber}"/>
		
		<spring:message var="formatDate" code="cambio.format.date"/>
		<spring:message code="cambio.moment" var="momentHeader" />
		<display:column property="moment" title="${momentHeader}" format="${formatDate}" sortable="true" class="${gaugeNumber }"/>
		
		<spring:message code="cambio.title" var="titleHeader" />
		<display:column property="title" title="${titleHeader}" sortable="false" class="${gaugeNumber}"/>
		
		<spring:message code="cambio.description" var="descriptionHeader" />
		<display:column property="description" title="${descriptionHeader}" sortable="false" class="${gaugeNumber}"/>
		
		<spring:message code="cambio.gauge" var="gaugeHeader" />
		<display:column property="gauge" title="${gaugeHeader}" sortable="false" class="${gaugeNumber}"/>
		
		
		<security:authorize access="hasRole('USER')">
			<display:column>
				<jstl:if test="${user == row.user && row.approved != null}">	
					<a href="cambio/user/edit.do?cambioId=${row.id}"><spring:message code="route.edit" /></a>
				</jstl:if>
			</display:column>
		</security:authorize>
		
			<security:authorize access="hasRole('ADMIN')">
			<display:column>
				<jstl:if test="${admin == row.administrator && row.approved == null}">
					<a href="cambio/administrator/edit.do?cambioId=${row.id}"><spring:message code="route.edit" /></a>
				</jstl:if>
			</display:column>
		</security:authorize>
		
</display:table>