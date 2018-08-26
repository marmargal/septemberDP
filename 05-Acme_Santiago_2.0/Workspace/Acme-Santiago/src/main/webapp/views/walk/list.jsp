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

<display:table pagesize="5" class="walks" keepStatus="true" name="walks"
	requestURI="${requestURI }" id="row">
	<security:authorize access="hasRole('USER')">

		<acme:column property="title" code="walk.title" />
		<display:column>
			<%!int count = 0;%>
			<jstl:forEach var="d" items="${row.daysOfEachHike }">
				<%
					count = count + 1;
				%>
				<spring:message code="walk.number.hike" var="numberHike" />
				<jstl:out value="${numberHike }"></jstl:out><%=count%>
				<br />
				<jstl:out value="${d }"></jstl:out>
				<hr />
			</jstl:forEach>
		</display:column>
		<acme:column property="route.name" code="walk.route" />

		<display:column>
			<a href="compostela/user/create.do?walkId=${row.id}"><spring:message
					code="walk.request.compostela" /></a>
		</display:column>
	</security:authorize>
</display:table>