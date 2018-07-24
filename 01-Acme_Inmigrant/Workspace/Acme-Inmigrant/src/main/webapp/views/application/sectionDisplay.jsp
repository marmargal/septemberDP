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

<spring:message code="application.contactSection" var="contactSectionHeader" />
<h1>
	<jstl:out value="${contactSectionHeader}"></jstl:out>
</h1>

<display:table name="contactSection" class="displaytag" id="row">

	<!-- Attributes -->
	
	<spring:message code="contactSection.email" var="emailHeader" />
	<display:column property="email" title="${emailHeader}" sortable="true" />

	<spring:message code="contactSection.phoneNumber" var="phoneNumberHeader" />
	<display:column property="phoneNumber" title="${phoneNumberHeader}" sortable="true" />
	
	<spring:message code="contactSection.pageNumber" var="pageNumberHeader" />
	<display:column property="pageNumber" title="${pageNumberHeader}" sortable="true" />
	
	<security:authorize access="hasRole('IMMIGRANT')">
		<spring:message code="application.edit" />
		<display:column>
				<a href="contactSection/immigrant/edit.do?contactSectionId=${row.id}"> <spring:message code="application.edit" /></a>
		</display:column>
	</security:authorize>

</display:table>

<security:authorize access="hasRole('IMMIGRANT')">
		<a href="contactSection/immigrant/create.do?applicationId=${applicationId}">
			<button>
				<spring:message code="application.create.contactSection" />
			</button>
		</a>
</security:authorize>

<spring:message code="application.workSection" var="workSectionHeader" />
<h1>
	<jstl:out value="${workSectionHeader}"></jstl:out>
</h1>

<display:table name="workSection" class="displaytag" id="row">

	<!-- Attributes -->
	
	<spring:message code="workSection.nameCompany" var="nameCompanyHeader" />
	<display:column property="nameCompany" title="${nameCompanyHeader}" sortable="true" />

	<spring:message code="workSection.position" var="position" />
	<display:column property="position" title="${position}" sortable="true" />
	
	<spring:message code="workSection.startDate" var="startDate" />
	<display:column property="startDate" title="${startDate}" sortable="true" />
	
	<spring:message code="workSection.endDate" var="endDate" />
	<display:column property="endDate" title="${endDate}" sortable="true" />
	
	<security:authorize access="hasRole('IMMIGRANT')">
		<spring:message code="application.edit" />
		<display:column>
				<a href="workSection/immigrant/edit.do?workSectionId=${row.id}"> <spring:message code="application.edit" /></a>
		</display:column>
	</security:authorize>

</display:table>

<security:authorize access="hasRole('IMMIGRANT')">
		<a href="workSection/immigrant/create.do?applicationId=${applicationId}">
			<button>
				<spring:message code="application.create.workSection" />
			</button>
		</a>
</security:authorize>

<spring:message code="application.socialSection" var="socialSection" />
<h1>
	<jstl:out value="${socialSection}"></jstl:out>
</h1>

<display:table name="socialSection" class="displaytag" id="row">

	<!-- Attributes -->
	
	<spring:message code="socialSection.nickName" var="nickName" />
	<display:column property="nickName" title="${nickName}" sortable="true" />

	<spring:message code="socialSection.socialNetwork" var="socialNetwork" />
	<display:column property="socialNetwork" title="${socialNetwork}" sortable="true" />
	
	<spring:message code="socialSection.profileLink" var="profileLink" />
	<display:column property="profileLink" title="${profileLink}" sortable="true" />
	
	<security:authorize access="hasRole('IMMIGRANT')">
		<spring:message code="application.edit" />
		<display:column>
				<a href="socialSection/immigrant/edit.do?socialSectionId=${row.id}"> <spring:message code="application.edit" /></a>
		</display:column>
	</security:authorize>

</display:table>

<security:authorize access="hasRole('IMMIGRANT')">
		<a href="socialSection/immigrant/create.do">
			<button>
				<spring:message code="application.create.socialSection" />
			</button>
		</a>
</security:authorize>

<spring:message code="application.educationSection" var="educationSection" />
<h1>
	<jstl:out value="${educationSection}"></jstl:out>
</h1>

<display:table name="educationSection" class="displaytag" id="row">

	<!-- Attributes -->
	
	<spring:message code="educationSection.nameDegree" var="nameDegree" />
	<display:column property="nameDegree" title="${nameDegree}" sortable="true" />

	<spring:message code="educationSection.institution" var="institution" />
	<display:column property="institution" title="${institution}" sortable="true" />
	
	<spring:message code="educationSection.dateAwarded" var="dateAwarded" />
	<display:column property="dateAwarded" title="${dateAwarded}" sortable="true" />
	
	<spring:message code="educationSection.level" var="level" />
	<display:column property="level" title="${level}" sortable="true" />
	
	<security:authorize access="hasRole('IMMIGRANT')">
		<spring:message code="application.edit" />
		<display:column>
				<a href="educationSection/immigrant/edit.do?educationSectionId=${row.id}"> <spring:message code="application.edit" /></a>
		</display:column>
	</security:authorize>

</display:table>

<security:authorize access="hasRole('IMMIGRANT')">
		<a href="educationSection/immigrant/create.do">
			<button>
				<spring:message code="application.create.educationSection" />
			</button>
		</a>
</security:authorize>
