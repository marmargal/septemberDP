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
	name="applications" requestURI="${requestURI }" id="row">

	<security:authorize access="hasRole('ADMIN')">
		<jstl:if test="${viewForDelete == true}">
			<display:column>
				<form name="submitForm" method="POST"
					action="application/administrator/delete.do?applicationId=${row.id }&method=${method}">
					<acme:submit name="delete" code="application.delete" />
				</form>
			</display:column>
		</jstl:if>
	</security:authorize>

	<acme:column property="ticker" code="application.ticker" />
	<acme:column property="createMoment" code="application.createMoment" />
	<acme:column property="closed" code="application.closed" />
	<acme:column property="pet.name" code="application.pet" />

	<security:authorize access="hasRole('EMPLOYEE')">
		<display:column>
			<acme:links url="report/employee/create.do?applicationId=${row.id }" code="report.create" />
		</display:column>
	</security:authorize>
</display:table>