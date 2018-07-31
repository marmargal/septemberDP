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

<jstl:if test="${!(dataAntennasPerUser[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataAntennasPerUser" /></b>
			<br />
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataAntennasPerUser[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataAntennasPerUser[0][1]}" />
				<br />
			</h4>
			
		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>

<jstl:if test="${!(dataQualityPerAntennas[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataAntennasPerUser" /></b>
			<br />
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataQualityPerAntennas[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataQualityPerAntennas[0][1]}" />
				<br />
			</h4>
			
		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>


<%----%>
<jstl:if test="${!(Top3AntennaPerpopularity[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.Top3AntennaPerpopularity" /></b>
			<br />
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${Top3AntennaPerpopularity[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${Top3AntennaPerpopularity[0][1]}" />
				<br />
			</h4>
			
		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>

<%----%>

<%----%>

<%----%>

<%----%>

<%----%>

<%----%>

<%----%>

<%----%>
<%----%>

<%----%>
