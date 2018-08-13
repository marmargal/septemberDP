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

<display:table pagesize="5" class="requests" keepStatus="true"
	name="requests" requestURI="${requestURI }" id="row">

	<acme:column property="moment" code="request.moment" />
	<acme:column property="description" code="request.description" />
	<acme:column property="finishMoment" code="request.finishMoment" />
	<acme:column property="result" code="request.result" />
	<acme:column property="antenna.model" code="request.antenna" />
	
	<display:column>
		<a href="antenna/handyworker/display.do?antennaId=${row.antenna.id }"><spring:message code="antenna.display"/></a>
	</display:column>

	<display:column>

		<a href="user/list.do?requestId=${row.id}"><spring:message
				code="request.user" /></a>
	</display:column>

	<security:authorize access="hasRole('HANDYWORKER')">
		<display:column>
			<jstl:if test="${row.handyworker==null }">
			<form name="submitForm" method="POST" action="request/handyworker/assign.do?requestId=${row.id }">
				    	<input type="hidden" name="param1" value="param1Value">
				    	<acme:submit name="assign" code="request.assign"/>
					</form>
			</jstl:if>
		</display:column>
	</security:authorize>
</display:table>