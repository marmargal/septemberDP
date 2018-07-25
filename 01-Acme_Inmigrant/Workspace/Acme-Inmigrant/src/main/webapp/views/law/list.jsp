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


	<display:table pagesize="6" class="displayLaw" keepStatus="true"
		name="law" requestURI="${requestURI }" id="row">
		
<spring:message code="law.title" var="lawHeader" />
	<display:column property="title" title="${lawHeader}" sortable="true" />

	<spring:message code="law.text" var="textHeader" />
	<display:column property="text" title="${textHeader}" sortable="true" />
	
		<display:column>
			<acme:links url="law/administrator/display.do?lawId=${row.id}"
				code="law.display" />
		</display:column>

		<display:column>
			<acme:links url="law/administrator/edit.do?lawId=${row.id}"
				code="law.edit" />
		</display:column>



	</display:table>
	<security:authorize access="hasRole('ADMIN')">
		<a href="law/administrator/create.do"><spring:message code="law.create"/></a>
		<br/>
</security:authorize>
