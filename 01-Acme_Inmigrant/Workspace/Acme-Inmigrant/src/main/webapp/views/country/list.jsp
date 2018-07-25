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
	name="country" requestURI="${requestURI }" id="row">
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="country/administrator/edit.do?countryId=${row.id }"><spring:message code="country.edit"/></a>
		</display:column>
	</security:authorize>
	
	<display:column>
		<a href="country/display.do?countryId=${row.id}"><spring:message code="country.display"/></a>
	</display:column>	

	<spring:message code="country.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />

	<spring:message code="country.isoCode" var="isoCodeHeader" />
	<display:column property="isoCode" title="${isoCodeHeader}" sortable="true" />

	<display:column>
			<a href="visa/country/list.do?countryId=${row.id}"><spring:message code="country.visas"/></a>
	</display:column>

</display:table>

<security:authorize access="hasRole('ADMIN')">
		<a href="country/administrator/create.do"><spring:message code="country.create"/></a>
		<br/>
</security:authorize>