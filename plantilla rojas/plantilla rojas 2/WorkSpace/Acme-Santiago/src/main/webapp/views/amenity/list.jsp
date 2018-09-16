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

<display:table pagesize="5" class="advertisements" keepStatus="true" name="amenity"
	requestURI="${requestURI }" id="row">

	<display:column>	
		<acme:links url="amenity/display.do?amenityId=${row.id}" code="amenity.display" />
	</display:column>
	<acme:column property="name" code="amenity.name" />
	
	<security:authorize access="hasRole('INNKEEPER')">
		<display:column>
			<acme:links url="amenity/innkeeper/edit.do?amenityId=${row.id}" code="amenity.edit" />
		</display:column>
	</security:authorize>
	
</display:table>

<security:authorize access="hasRole('INNKEEPER')">
	<acme:links url="amenity/innkeeper/create.do" code="amenity.create" />
</security:authorize>