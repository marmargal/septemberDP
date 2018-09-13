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

<form method=GET action="visa/search.do">
	Search: <input type="text" name="criteria"> <input
		type="submit">
</form>


<display:table pagesize="5" class="visas" keepStatus="true" name="visas"
	requestURI="${requestURI }" id="row">




	<security:authorize access="hasRole('ADMIN')">

		<display:column>
			<a href="visa/administrator/edit.do?visaId=${row.id}"> <spring:message
					code="visa.edit" />
			</a>
		</display:column>

	</security:authorize>

	<acme:column property="classes" code="visa.classes" />
	<acme:column property="description" code="visa.description" />


	<spring:message code="format.price" var="patternPrice" />
	<spring:message code="visa.price" var="priceHeader" />
	<display:column property="price" title="${priceHeader}" sortable="true"
		format="${patternPrice}" />

	<acme:column property="invalidate" code="visa.invalidate" />
	<acme:column property="category.name" code="visa.category" />

	<display:column>

		<a href="visa/display.do?visaId=${row.id}"><spring:message
				code="visa.display" /></a>
	</display:column>


	<display:column>

		<a href="requirement/list.do?visaId=${row.id}"><spring:message
				code="visa.requirements" /></a>
	</display:column>


	<display:column>
		<jstl:if test="${row.country.id != null}">
			<acme:links url="country/display.do?countryId=${row.country.id }"
				code="visa.country" />
		</jstl:if>

	</display:column>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<form name="submitForm" method="POST"
				action="visa/administrator/delete.do?visaId=${row.id }">
				<acme:submit name="delete" code="visa.delete" />
			</form>
		</display:column>
	</security:authorize>
	
</display:table>

<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="visa/administrator/create.do"> <spring:message
				code="visa.create" />
		</a>
	</div>
	
	
</security:authorize>
