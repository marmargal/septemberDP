<%--
 * register_Immigrant.jsp
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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table pagesize="6" class="immigrant" keepStatus="true"
	name="immigrant" requestURI="${requestURI }" id="row">

	<security:authorize access="hasRole('OFFICER')">
		<acme:column property="name" code="immigrant.name"/>
		<acme:column property="surname" code="immigrant.surname"/>
		<acme:column property="email" code="immigrant.email"/>
		<acme:column property="phoneNumber" code="immigrant.phoneNumber"/>
		<acme:column property="address" code="immigrant.address"/>
		
		<display:column><acme:links url="immigrant/officer/edit.do?immigrantId=${row.id }" code="immigrant.investigate"/></display:column>
	</security:authorize>
	
</display:table>