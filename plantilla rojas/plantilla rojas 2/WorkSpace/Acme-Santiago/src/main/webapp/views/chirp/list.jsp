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
	name="chirp" requestURI="${requestURI}" id="row">
	
	<!-- Attributes -->

	<security:authorize access="hasRole('ADMIN')">
		<display:column><acme:links url="chirp/administrator/edit.do?chirpId=${row.id}" code="chirp.edit" /></display:column>
		<acme:column property="title" code="chirp.title" />
		
		<spring:message var="formatDate" code="chirp.format.date"/>
		<spring:message code="chirp.moment" var="momentHeader" />
		<display:column property="moment" title="${momentHeader}" format="${formatDate}" sortable="true" />
		
		<acme:column property="text" code="chirp.text" />
	
	</security:authorize>
</display:table>