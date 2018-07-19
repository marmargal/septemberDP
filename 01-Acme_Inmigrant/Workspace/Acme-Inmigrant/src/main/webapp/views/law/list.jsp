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

<security:authorize access="hasRole('ADMIN')">

	<display:table pagesize="6" class="displayLaw" keepStatus="true"
		name="law" requestURI="${requestURI }" id="row">



		<spring:message code="law.title" var="titleHeader" />
		<display:column property="law.title" title="${lawHeader}"
			sortable="false" />

		<spring:message code="law.text" var="lawHeader" />
		<display:column property="law.text" title="${textHeader}"
			sortable="true" />

		<display:column>
			<a href="law/administrator/display.do?lawId=${row.id}"><spring:message
					code="law.display" /></a>
		</display:column>

		<display:column>
			<a href="law/administrator/edit.do?lawId=${row.id}"> <spring:message
					code="law.edit" />
			</a>
		</display:column>



	</display:table>
</security:authorize>