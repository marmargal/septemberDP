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

<display:table pagesize="5" class="advertisements" keepStatus="true" name="advertisements"
	requestURI="${requestURI }" id="row">

	<acme:column property="title" code="advertisement.title" />
	<acme:column property="banner" code="advertisement.banner" />
	<acme:column property="targetPage" code="advertisement.targetPage" />
	<acme:column property="creditCard.number" code="advertisement.creditCard.number" />
	<acme:column property="activeDays" code="advertisement.activeDays" />
	<acme:column property="taboo" code="advertisement.taboo" />
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
				<a href="advertisement/administrator/edit.do?advertisementId=${row.id}"><spring:message
						code="advertisement.edit" /></a>
		</display:column>
	</security:authorize>

</display:table>

<security:authorize access="hasRole('AGENT')">
	<acme:links url="advertisement/agent/create.do" code="advertisement.create" />
</security:authorize>
