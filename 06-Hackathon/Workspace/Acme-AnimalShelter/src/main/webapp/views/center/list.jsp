<%-- list.jsp --%>

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
	name="centers" requestURI="${requestURI }" id="row">

	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<form name="submitForm" method="POST"
				action="center/administrator/delete.do?centerId=${row.id }">
				<acme:submit name="delete" code="center.delete" />
			</form>
		</display:column>

	</security:authorize>
	<security:authorize access="hasRole('BOSS')">
		<display:column>
			<form name="submitForm" method="POST"
				action="center/boss/delete.do?centerId=${row.id }">
				<acme:submit name="delete" code="center.delete" />
			</form>
		</display:column>

	</security:authorize>

	<spring:message code="center.picture" var="pictureHeader" />
	<display:column title="${pictureHeader}" sortable="false">
		<img src="<jstl:out value="${row.picture}"/>" width="200" height="87">
	</display:column>

	<acme:column property="description" code="center.description" />
	<security:authorize access="hasRole('BOSS')">
		<display:column>
			<acme:links url="center/boss/edit.do?centerId=${row.id }"
				code="center.edit" />
		</display:column>
	</security:authorize>
</display:table>
<br>
<security:authorize access="hasRole('BOSS')">
	<acme:links url="center/boss/create.do" code="center.create" />
</security:authorize>