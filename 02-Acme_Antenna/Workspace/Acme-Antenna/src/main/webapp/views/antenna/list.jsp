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

<display:table pagesize="6" class="displaycomment" keepStatus="true"
	name="antenna" requestURI="${requestURI }" id="row">
	
	<display:column>
		<a href="antenna/user/display.do?antennaId=${row.id }"><spring:message code="antenna.display"/></a>
	</display:column>
	
	<display:column>
		<a href="antenna/user/edit.do?antennaId=${row.id }"><spring:message code="antenna.edit"/></a>
	</display:column>
	
	<spring:message code="antenna.serialNumber" var="serialNumberHeader" />
	<display:column property="serialNumber" title="${serialNumberHeader}" sortable="true" />

	<spring:message code="antenna.model" var="modelHeader" />
	<display:column property="model" title="${modelHeader}" sortable="true" />

</display:table>

<a href="antenna/user/create.do"><spring:message code="antenna.create"/></a>
