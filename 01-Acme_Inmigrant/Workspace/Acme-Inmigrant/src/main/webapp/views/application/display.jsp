<%-- display.jsp --%>

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

	<security:authorize access="hasRole('IMMIGRANT')">
		<spring:message code="application.edit" />
		<display:column>
			<jstl:set var="thisImmigrantId" value="${row.immigrant.id }" />
			<jstl:if test="${row.immigrant.id == currentImmigrantId}">
				<a href="application/immigrant/edit.do?applicationId=${row.id}"> <spring:message code="application.edit" /></a>
			</jstl:if>
		</display:column>
	</security:authorize>

</display:table>

<!-- Action links -->

<security:authorize access="hasRole('IMMIGRANT')">
	<acme:links url="application/immigrant/create.do" code="application.create" />
</security:authorize>
