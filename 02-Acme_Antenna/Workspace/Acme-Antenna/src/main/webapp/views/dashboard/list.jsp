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
			<b><spring:message code="dashboard.dataQualityPerAntennas" /></b>

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
<%-- <jstl:if test="${!(dataNumAntennasPerModel[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataNumAntennasPerModel" /></b>
			<br />
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataNumAntennasPerModel}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataNumAntennasPerModel}" />
				<br />
			</h4>
			
		</div>
	</fieldset>
</jstl:if>

<br /> --%>


<%----%>
<jstl:if test="${!(Top3AntennaPerpopularity[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.Top3AntennaPerpopularity" /></b>
			<br />
			<h4>
				<jstl:out value=" 1: " />
				<jstl:out value="${Top3AntennaPerpopularity[0].model}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" 2: " />
				<jstl:out value="${Top3AntennaPerpopularity[1].model}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" 3: " />
				<jstl:out value="${Top3AntennaPerpopularity[2].model}" />
				<br />
			</h4>
			
		</div>
	</fieldset>
</jstl:if>

<br /> 
<%----%>
<jstl:if test="${!(dataTutorialPerUser[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataTutorialPerUser" /></b>
			<br />
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataTutorialPerUser[0][0]}" />

				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataTutorialPerUser[0][1]}" />
				<br />
			</h4>
			
		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>
<jstl:if test="${!(dataNumCommentPerTutorial[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataNumCommentPerTutorial" /></b>
			<br />
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataNumCommentPerTutorial[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataNumCommentPerTutorial[0][1]}" />
				<br />
			</h4>
			
		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>
<jstl:if test="${!(actorHasPublished eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.actorHasPublished" /></b>
			<br />
			<h4>
				<jstl:out value=" 1: " />
				<jstl:out value="${actorHasPublished[0].name}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" 2: " />
				<jstl:out value="${actorHasPublished[1].name}" />
				<br />
			</h4>
			
			
		</div>
	</fieldset>
</jstl:if>

<br /> 

<%----%>
<jstl:if test="${!(dataNumRepliesPerComment[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataNumRepliesPerComment" /></b>
			<br />
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataNumRepliesPerComment[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataNumRepliesPerComment[0][1]}" />
				<br />
			</h4>
			
		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>
<jstl:if test="${!(dataNumLengthOfComments[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataNumLengthOfComments" /></b>
			<br />
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataNumLengthOfComments[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataNumLengthOfComments[0][1]}" />
				<br />
			</h4>
			
		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>
<%-- <jstl:if test="${!(dataNumPicturesPerTutorial[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataNumPicturesPerTutorial" /></b>
			<br />
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataNumPicturesPerTutorial[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataNumPicturesPerTutorial[0][1]}" />
				<br />
			</h4>
			
		</div>
	</fieldset>
</jstl:if>

<br /> --%>
<%----%>
<%-- <jstl:if test="${!(dataNumPicturesPerComment[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataNumPicturesPerComment" /></b>
			<br />
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataNumPicturesPerComment[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataNumPicturesPerComment[0][1]}" />
				<br />
			</h4>
			
		</div>
	</fieldset>
</jstl:if>

<br /> --%>
