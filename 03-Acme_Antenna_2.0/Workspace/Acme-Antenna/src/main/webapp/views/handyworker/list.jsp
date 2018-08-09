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

<display:table pagesize="5" class="handyworkers" keepStatus="true" name="handyworkers"
	requestURI="${requestURI }" id="row">

	<acme:column property="name" code="handyworker.name" />
	<acme:column property="surname" code="handyworker.surname" />
	<acme:column property="postalAddress" code="handyworker.address" />
	<acme:column property="email" code="handyworker.email" />
	<acme:column property="phoneNumber" code="handyworker.phoneNumber" />
	<acme:column property="userAccount.username" code="handyworker.username" />

	<display:column>

		<a href="request/handyworker/list.do?phandyworkerId=${row.id}"><spring:message
				code="handyworker.requests" /></a>
	</display:column>
</display:table>