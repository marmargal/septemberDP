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

<display:table pagesize="5" class="inn" keepStatus="true" name="inn"
	requestURI="${requestURI }" id="row">

	<acme:column property="name" code="inn.name" />
	<acme:column property="badge" code="inn.badge" />
	<acme:column property="address" code="inn.address" />
	<acme:column property="phoneNumber" code="inn.phoneNumber" />
	<acme:column property="email" code="inn.email" />
	<acme:column property="webSite" code="inn.webSite" />
	<acme:column property="creditCard.holderName" code="inn.creditCard" />

</display:table>

