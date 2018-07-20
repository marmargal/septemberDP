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

<spring:message code="application.personalSection" var="personalSectionHeader" />
<h1>
	<jstl:out value="${personalSectionHeader}"></jstl:out>
</h1>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="personalSection" requestURI="${requestURI }" id="row">

	<!-- Attributes -->
	
	<spring:message code="application.personalSection.name" var="nameHeader" />
	<display:column property="names" title="${nameHeader}" sortable="true" />

	<spring:message code="application.personalSection.birthPlace" var="birthPlaceHeader" />
	<display:column property="birthPlace" title="${birthPlaceHeader}" sortable="true" />
	
	<spring:message code="application.personalSection.birthDate" var="birthDateHeader" />
	<display:column property="birthDate" title="${birthDateHeader}" sortable="true" />
	
	<spring:message code="application.personalSection.picture" var="pictureHeader" />
	<display:column property="picture" title="${pictureHeader}" sortable="true" />
	
	<security:authorize access="hasRole('IMMIGRANT')">
		<spring:message code="application.edit" />
		<display:column>
				<a href="personalSection/immigrant/edit.do?personalSectionId=${row.id}"> <spring:message code="application.edit" /></a>
		</display:column>
	</security:authorize>

</display:table>

<security:authorize access="hasRole('IMMIGRANT')">
		<a href="personalSection/immigrant/create.do">
			<button>
				<spring:message code="application.create.personalSection" />
			</button>
		</a>
</security:authorize>
