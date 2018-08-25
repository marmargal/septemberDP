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

<display:table pagesize="5" class="compostelas" keepStatus="true" name="compostelas"
	requestURI="${requestURI }" id="row">
	<security:authorize access="hasRole('ADMIN')">

	<acme:column property="header" code="compostela.header" />
	<acme:column property="body" code="compostela.body" />
	<acme:column property="footer" code="compostela.footer" />

	<display:column>
		<jstl:forEach var="p" items="${row.logo }">
			<img class="imagen" src="${p }" />
		</jstl:forEach>
	</display:column>

	</security:authorize>
</display:table>