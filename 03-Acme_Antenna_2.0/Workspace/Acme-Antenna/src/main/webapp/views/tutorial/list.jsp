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

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="tutorial" requestURI="${requestURI }" id="row">

	<spring:message code="tutorial.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"	sortable="true" />
		
	<spring:message code="tutorial.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}"	sortable="true" />

	<spring:message code="tutorial.actor" var="actorHeader" />
	<display:column property="actor.name" title="${actorHeader}"	sortable="true" />
	
	<display:column>
		<acme:links url="tutorial/display.do?tutorialId=${row.id }" code="tutorial.display"/>
	</display:column>

	<security:authorize access="hasRole('USER')">
		<display:column>
			<jstl:if test="${actor == row.actor}">
				<acme:links url="tutorial/user/edit.do?tutorialId=${row.id }" code="tutorial.edit"/>
			</jstl:if>
		</display:column>
	</security:authorize>
	
	<!-- botón de borrar del admin en el list o en el display? -->
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<acme:links url="tutorial/administrator/edit.do?tutorialId=${row.id }" code="tutorial.delete"/>
		</display:column>
	</security:authorize>

</display:table>

<security:authorize access="hasRole('USER')">
	<acme:links url="tutorial/user/create.do?" code="tutorial.create"/>
</security:authorize>