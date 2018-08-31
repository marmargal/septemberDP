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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<h1><spring:message code="terms.introduccion"></spring:message></h1>
<p><spring:message code="terms.introduccion.contenido"></spring:message></p>

<h1><spring:message code="terms.condiciones"></spring:message></h1>
<p><spring:message code="terms.condiciones.contenido"></spring:message></p>


<h1><spring:message code="terms.modificaciones"></spring:message></h1>
<p><spring:message code="terms.modificaciones.contenido"></spring:message></p>


<h1><spring:message code="terms.obligaciones"></spring:message></h1>
<p><spring:message code="terms.obligaciones.contenido"></spring:message></p>


<h1><spring:message code="terms.responsabilidad"></spring:message></h1>
<p><spring:message code="terms.responsabilidad.contenido"></spring:message></p>


<h1><spring:message code="terms.legislacion"></spring:message></h1>
<p><spring:message code="terms.legislacion.contenido"></spring:message></p>

<a href="javascript:window.history.back();">&laquo; <spring:message code="terms.back"/></a>











