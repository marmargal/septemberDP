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
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table pagesize="5" class="banners" keepStatus="true"
	name="banners" requestURI="${requestURI }" id="row">

	<acme:column property="picture" code="banner.picture" />
	<acme:column property="targetPage" code="banner.targetPage" />
	<acme:column property="creditCard.number" code="banner.creditCard" />
	<acme:column property="agent.name" code="banner.agent" />
	
	<security:authorize access="hasRole('ADMIN')">
	<display:column>
	<acme:links url="banner/administrator/edit.do?bannerId=${row.id}" code="banner.delete"/>
	</display:column>
	</security:authorize>

</display:table>

<security:authorize access="hasRole('AGENT')">
		<acme:links url="banner/agent/create.do" code="banner.create"/>
</security:authorize>