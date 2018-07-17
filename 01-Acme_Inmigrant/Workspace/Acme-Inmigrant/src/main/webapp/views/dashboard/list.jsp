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

<jstl:if test="${!(dataApplicationPerImmigrant[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataApplicationPerImmigrant" /></b>
			<br />
			<h4>
				<jstl:out value=" MIN: " />
				<jstl:out value="${dataApplicationPerImmigrant[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" MAX: " />
				<jstl:out value="${dataApplicationPerImmigrant[0][1]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataApplicationPerImmigrant[0][2]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataApplicationPerImmigrant[0][3]}" />
				<br />
			</h4>
		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>

<jstl:if test="${!(dataApplicationsPerOfficer[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataApplicationsPerOfficer" /></b>
			<br />
			<h4>
				<jstl:out value=" MIN: " />
				<jstl:out value="${dataApplicationsPerOfficer[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" MAX: " />
				<jstl:out value="${dataApplicationsPerOfficer[0][1]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataApplicationsPerOfficer[0][2]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataApplicationsPerOfficer[0][3]}" />
				<br />
			</h4>
		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>

<jstl:if test="${!(dataPricePerVisa[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataPricePerVisa" /></b> <br />
			<h4>
				<jstl:out value=" MIN: " />
				<jstl:out value="${dataPricePerVisa[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" MAX: " />
				<jstl:out value="${dataPricePerVisa[0][1]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataPricePerVisa[0][2]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataPricePerVisa[0][3]}" />
				<br />
			</h4>
		</div>
	</fieldset>
</jstl:if>
<%----%>

<br />
<jstl:if test="${!(dataImmigrantsInvestigated[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataImmigrantsInvestigated" /></b>
			<br />
			<h4>
				<jstl:out value=" MIN: " />
				<jstl:out value="${dataImmigrantsInvestigated[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" MAX: " />
				<jstl:out value="${dataImmigrantsInvestigated[0][1]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataImmigrantsInvestigated[0][2]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataImmigrantsInvestigated[0][3]}" />
				<br />
			</h4>
		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>

<jstl:if test="${!(dataTimeToMakeDecision[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataTimeToMakeDecision" /></b> <br />
			<h4>
				<jstl:out value=" MIN: " />
				<jstl:out value="${dataTimeToMakeDecision[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" MAX: " />
				<jstl:out value="${dataTimeToMakeDecision[0][1]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataTimeToMakeDecision[0][2]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataTimeToMakeDecision[0][3]}" />
				<br />
			</h4>
		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>
<jstl:if test="${!(dataVisasPerCategory[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataVisasPerCategory" /></b> <br />
			<h4>
				<jstl:out value=" MIN: " />
				<jstl:out value="${dataVisasPerCategory[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" MAX: " />
				<jstl:out value="${dataVisasPerCategory[0][1]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataVisasPerCategory[0][2]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataVisasPerCategory[0][3]}" />
				<br />
			</h4>
		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>
<jstl:if test="${!(dataLawsPerCountry[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataLawsPerCountry" /></b> <br />
			<h4>
				<jstl:out value=" MIN: " />
				<jstl:out value="${dataLawsPerCountry[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" MAX: " />
				<jstl:out value="${dataLawsPerCountry[0][1]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataLawsPerCountry[0][2]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataLawsPerCountry[0][3]}" />
				<br />
			</h4>
		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>
<jstl:if test="${!(dataRequirementsPerVisa[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataRequirementsPerVisa" /></b> <br />
			<h4>
				<jstl:out value=" MIN: " />
				<jstl:out value="${dataRequirementsPerVisa[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" MAX: " />
				<jstl:out value="${dataRequirementsPerVisa[0][1]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataRequirementsPerVisa[0][2]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataRequirementsPerVisa[0][3]}" />
				<br />
			</h4>
		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>