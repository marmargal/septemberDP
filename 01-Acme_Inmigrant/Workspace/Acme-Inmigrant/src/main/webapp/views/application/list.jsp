<%-- list.jsp --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="application" requestURI="${requestURI }" id="row">

	<!-- Attributes -->

	<spring:message code="application.ticker" var="tickerHeader" />
	<display:column property="ticker" title="${tickerHeader}" sortable="true" />
	
	<spring:message code="application.openedMoment" var="openedMomentHeader" />
	<display:column property="openedMoment" title="${openedMomentHeader}" sortable="true" />
	
	<spring:message code="application.closedMoment" var="closedMomentHeader" />
	<display:column property="closedMoment" title="${closedMomentHeader}" sortable="true" />
	
	<spring:message code="application.creditCard" var="creditCardHeader" />
	<display:column property="creditCard.number" title="${creditCardHeader}" sortable="true" />
	
</display:table>
