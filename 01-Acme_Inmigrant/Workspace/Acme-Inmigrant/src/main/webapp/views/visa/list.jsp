<%-- list.jsp de visa --%>

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

<display:table name="visas" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	<security:authorize access="hasRole('EXPLORER')">
		<display:column>
			<a href="visa/administrator/edit.do?visaId=${row.id}"> <spring:message
					code="visa.edit" />
			</a>
		</display:column>
	</security:authorize>
	<spring:message code="visa.classes" var="classesHeader" />
	<display:column property="classes" title="${classesHeader}" />

	<spring:message code="visa.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" />

	<spring:message code="visa.price" var="priceHeader" />
	<display:column property="price" title="${priceHeader}" />

	<spring:message code="visa.invalidate" var="invalidateHeader" />
	<display:column property="invalidate" title="${invalidateHeader}" />


</display:table>

<div>
	<a href="visa/administrator/create.do"> <spring:message
			code="visa.create" />
	</a>
</div>
