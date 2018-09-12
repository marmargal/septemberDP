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
	name="medicalReports" requestURI="${requestURI }" id="row">

	<security:authorize access="hasRole('ADMIN')">
		<jstl:if test="${viewForDelete == true}">
			<display:column>
				<form name="submitForm" method="POST" action="medicalReport/administrator/delete.do?medicalReportId=${row.id }">
			    	<acme:submit name="delete" code="medicalReport.delete"/>
				</form>
			</display:column>
		</jstl:if>
	</security:authorize>

	<acme:column property="diagnosis" code="medicalReport.diagnosis" />
	<acme:column property="initialState" code="medicalReport.initialState" />
	<acme:column property="treatment" code="medicalReport.treatment" />
	
	<spring:message var="formatDate" code="medicalReport.format.date"/>
	<spring:message code="medicalReport.date" var="momentHeader" />
	<display:column property="date" title="${momentHeader}" format="${formatDate}" sortable="true" />
	
	<acme:column property="diseases" code="medicalReport.diseases" />
	
</display:table>