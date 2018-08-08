<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table pagesize="6" class="displaycomment" keepStatus="true"
	name="subscription" requestURI="${requestURI }" id="row">

	<spring:message code="subscription.keyCode" var="keyCodeHeader" />
	<display:column property="keyCode" title="${keyCodeHeader}" sortable="true" />

	<spring:message var="formatDate" code="subscription.format.date"/>
	
	<spring:message code="subscription.startDate" var="startHeader" />
	<display:column property="startDate" title="${startHeader}" format="${formatDate}" sortable="true" />
	
	<spring:message code="subscription.endDate" var="endHeader" />
	<display:column property="endDate" title="${endHeader}" format="${formatDate}" sortable="true" />

	<spring:message code="subscription.platform" var="platformHeader" />
	<display:column property="platform.name" title="${platformHeader}" sortable="true" />

</display:table>

<a href="subscription/user/create.do"><spring:message code="subscription.create"/></a>
