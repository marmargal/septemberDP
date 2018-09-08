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
	name="stands" requestURI="${requestURI }" id="row">

	<security:authorize access="hasRole('VOLUNTARY')">
		<display:column>
			<acme:links url="stand/voluntary/join.do?standId=${row.id }"
				code="stand.join" />
		</display:column>
	</security:authorize>


	<security:authorize access="hasRole('BOSS')">
		<display:column>
			<jstl:if test="${row.employee == null}">
				<acme:links url="stand/boss/affiliate.do?standId=${row.id }"
					code="stand.affiliate" />
			</jstl:if>
		</display:column>
	</security:authorize>


	<acme:column property="numMaxVoluntaries"
		code="stand.numMaxVoluntaries" />
	<acme:column property="fliers" code="stand.fliers" />
	<acme:column property="isOfCompany" code="stand.isOfCompany" />
	<acme:column property="employee.name" code="stand.employee" />

	<security:authorize access="hasRole('BOSS')">
		<display:column>
			<acme:links url="stand/boss/edit.do?standId=${row.id }"
				code="stand.edit" />
		</display:column>
	</security:authorize>
</display:table>
<br>
<security:authorize access="hasRole('BOSS')">
	<acme:links url="stand/boss/create.do" code="stand.create" />
</security:authorize>