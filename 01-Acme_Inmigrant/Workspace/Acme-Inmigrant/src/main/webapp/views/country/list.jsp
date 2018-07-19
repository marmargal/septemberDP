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
	name="countries" requestURI="${requestURI }" id="row">
	
	<display:column>
			<a href="visa/list.do?countryId=${row.id}"><spring:message code="country.visas"/></a>
	</display:column>

	<acme:column property="name" code="cauntry.name"/>
	<acme:column property="isoCode" code="cauntry.isoCode"/>

	<display:column>
			<a href="country/display.do?countryId=${row.id}"><spring:message code="country.display"/></a>
	</display:column>

	<security:authorize access="hasRole('ADMIN')">
			<a href="country/administrator/edit.do"><spring:message code="category.create"/></a>
			<br/>
	</security:authorize>
	
</display:table>

