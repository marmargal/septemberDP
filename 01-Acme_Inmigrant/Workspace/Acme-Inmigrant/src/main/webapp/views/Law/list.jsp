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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>



	<display:table pagesize="6" class="displayLaw" keepStatus="true"
		name="law" requestURI="${requestURI }" id="row">


		<spring:message code="law.title" var="lawHeader" />
		<display:column property="title" title="${lawHeader}" sortable="true" />

		<spring:message code="law.text" var="lawHeader" />
		<display:column property="text" title="${lawHeader}" sortable="true" />

		<spring:message code="law.lawParent" var="lawParentHeader" />
		<jstl:if test="${lawParent != \"root\"}">
			<display:column property="lawParent.title" title="${lawParentHeader}"
				sortable="false" />
		</jstl:if>
		
		<spring:message code="law.lawChildren" var="lawChildren"/>
	<display:column title="${lawChildren}">
		<a href="law/display.do?lawId=${row.id}">
			<spring:message code="law.lawChildren.link"/>
		</a>
	</display:column>

		<display:column>
			<a href="law/display.do?lawId=${row.id}"><spring:message
					code="law.display" /></a>
		</display:column>

		<display:column>
			<a href="law/administrator/edit.do?lawId=${row.id}"> <spring:message
					code="law.edit" />
			</a>
		</display:column>
		


	</display:table>
<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="law/administrator/create.do"> <spring:message
				code="law.create" />
		</a>
	</div>
</security:authorize>