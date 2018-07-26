<%-- display.jsp --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="application" requestURI="${requestURI }" id="row">

	<!-- Attributes -->

	<security:authorize access="hasRole('IMMIGRANT')">
		<spring:message code="application.edit" />
		<display:column>
			<jstl:set var="thisImmigrantId" value="${row.immigrant.id }" />
			<jstl:if
				test="${row.immigrant.id == currentImmigrantId && row.closedMoment == null}">
				<a href="application/immigrant/edit.do?applicationId=${row.id}">
					<spring:message code="application.edit" />
				</a>
			</jstl:if>
		</display:column>
	</security:authorize>

	<spring:message code="application.immigrant" var="immigrantHeader" />
	<display:column property="immigrant.userAccount.username" title="${immigrantHeader}"
		sortable="true" />

	<spring:message code="application.ticker" var="tickerHeader" />
	<display:column property="ticker" title="${tickerHeader}"
		sortable="true" />

	<spring:message code="application.openedMoment"
		var="openedMomentHeader" />
	<display:column property="openedMoment" title="${openedMomentHeader}"
		sortable="true" />

	<spring:message code="application.closedMoment"
		var="closedMomentHeader" />
	<display:column property="closedMoment" title="${closedMomentHeader}"
		sortable="true" />

	<spring:message code="application.creditCard" var="creditCardHeader" />
	<display:column property="creditCard.number"
		title="${creditCardHeader}" sortable="true" />

	<display:column>
		<acme:links url="visa/display.do?visaId=${row.visa.id }"
			code="application.visa" />
	</display:column>

	<spring:message code="application.sections" />
	<display:column>
		<a
			href="application/immigrant/sectionDisplay.do?applicationId=${row.id}">
			<spring:message code="application.sections" />
		</a>
	</display:column>

	<spring:message code="application.decision" var="officer.decision" />
	<display:column>
		<jstl:if test="${row.officer.decision.id!=0}">
			<security:authorize access="hasRole('OFFICER')">
				<acme:links url="decision/officer/display.do?decisionId=${row.officer.decision.id }" code="application.decision" />
			</security:authorize>
			<security:authorize access="hasRole('IMMIGRANT')">
				<acme:links url="decision/immigrant/display.do?decisionId=${row.officer.decision.id }" code="application.decision" />
			</security:authorize>
		</jstl:if>
		<jstl:if test="${row.officer.decision.id==0}">
			<security:authorize access="hasRole('OFFICER')">
					<acme:links url="decision/officer/create.do?applicationId=${row.id }" code="application.createDecision" />
			</security:authorize>
		</jstl:if>
	</display:column>

</display:table>

<security:authorize access="hasRole('IMMIGRANT')">
	<a href="application/immigrant/create.do">
		<button>
			<spring:message code="application.create" />
		</button>
	</a>
</security:authorize>