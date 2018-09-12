<%-- list.jsp --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="application" requestURI="${requestURI }" id="row">

	<!-- Attributes -->
	
	<security:authorize access="hasRole('OFFICER')">
		
		<jstl:set var="hasAssign" value="false"/>
		<jstl:forEach var="app" items="${applicationsSelfAssigningAllOfficer}">
			<jstl:if test="${row == app}" >	
				<jstl:set var="hasAssign" value="true"/>
			</jstl:if>
		</jstl:forEach>
		
		<jstl:set var="hasDecision" value="false"/>
		<jstl:forEach var="app" items="${applicationsWhitDecisionByOfficer}">
			<jstl:if test="${row == app}" >	
				<jstl:set var="hasDecision" value="true"/>
			</jstl:if>
		</jstl:forEach>
		
		<display:column>
			<jstl:if test="${row.closed == false}" >
				<jstl:if test="${hasAssign == 'false'}" >
					<form name="submitForm" method="POST" action="application/officer/assign.do?applicationId=${row.id }">
				    	<input type="hidden" name="param1" value="param1Value">
				    	<acme:submit name="assign" code="application.assign"/>
					</form>
				</jstl:if>	
				<jstl:if test="${hasAssign == 'true'}" >
					<jstl:if test="${officer == row.officer}" >
						<jstl:if test="${hasDecision == 'false'}" >
							<a href="decision/officer/create.do?applicationId=${row.id }"><spring:message code="application.createDecision"/></a>
							<jstl:out value="${'-'}"></jstl:out>
							<a href="question/officer/list.do?applicationId=${row.id }"><spring:message code="application.questions"/></a>	
						</jstl:if>
						<jstl:if test="${hasDecision == 'true'}" >
							<jstl:forEach var="dec" items="${officer.decision}">														
								<jstl:if test="${dec.application == row}" >
									<jstl:set var="decisionId" value="${dec.id}"/>
								</jstl:if>		
							</jstl:forEach>
							<a href="decision/officer/display.do?decisionId=${decisionId}"><spring:message code="application.decision"/></a>	
						</jstl:if>
					</jstl:if>
				</jstl:if>
			</jstl:if>	
		</display:column>
	</security:authorize>

	<spring:message code="application.ticker" var="tickerHeader" />
	<display:column property="ticker" title="${tickerHeader}"
		sortable="true" />

	<spring:message var="formatDate" code="application.format.date"/>
	<spring:message code="application.openedMoment" var="openedMomentHeader" />
	<display:column property="openedMoment" title="${openedMomentHeader}" format="${formatDate}" sortable="true" />

	<spring:message code="application.closedMoment" var="closedMomentHeader" />
	<display:column property="closedMoment" title="${closedMomentHeader}" format="${formatDate}" sortable="true" />

	<spring:message code="application.creditCard" var="creditCardHeader" />
	<display:column property="creditCard.number"
		title="${creditCardHeader}" sortable="true" />

	<display:column>
		<acme:links url="visa/display.do?visaId=${row.visa.id }"
			code="application.visa" />
	</display:column>
	
	<security:authorize access="hasRole('IMMIGRANT')">
	<display:column>
		<acme:links url="question/immigrant/list.do?applicationId=${row.id }"
			code="application.questions" />
	</display:column>
	</security:authorize>

</display:table>
