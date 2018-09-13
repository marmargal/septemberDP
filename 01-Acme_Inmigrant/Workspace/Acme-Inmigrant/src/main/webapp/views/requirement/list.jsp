<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table pagesize="6" class="displaycomment" keepStatus="true"
	name="requirement" requestURI="${requestURI }" id="row">
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="requirement/administrator/edit.do?requirementId=${row.id}"><spring:message code="requirement.edit"/></a>
		</display:column>
	</security:authorize>
	
	<display:column>
			<a href="requirement/display.do?requirementId=${row.id}"><spring:message code="requirement.display"/></a>
	</display:column>

	<spring:message code="requirement.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<spring:message code="requirement.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="true" />
	
	<spring:message code="requirement.abrogated" var="abrogatedHeader" />
	<display:column property="abrogated" title="${abrogatedHeader}" sortable="true" />

	<display:column>
			<a href="law/display.do?lawId=${row.law.id}"><spring:message code="requirement.law"/></a>
	</display:column>
	
</display:table>

<security:authorize access="hasRole('ADMIN')">
	<a href="requirement/administrator/create.do"><spring:message code="requirement.create"/></a>
</security:authorize>

