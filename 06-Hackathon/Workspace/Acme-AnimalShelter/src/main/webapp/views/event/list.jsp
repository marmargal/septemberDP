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
	name="events" requestURI="${requestURI }" id="row">

	<acme:column property="title" code="event.title" />
	<acme:column property="description" code="event.description" />
	<acme:column property="donation" code="event.donation" />
	<acme:column property="nameSite" code="event.nameSite" />
	<acme:column property="address" code="event.address" />
	
	<spring:message code="event.placard" var="placardHeader" />
	<display:column title="${placardHeader}" sortable="false">
		<img src="<jstl:out value="${row.placard}"/>" width="200" height="87">
	</display:column>
	
	<spring:message var="formatDate" code="event.format.date"/>
	<spring:message code="event.startDate" var="startDateHeader" />
	<display:column property="startDate" title="${startDateHeader}" format="${formatDate}" sortable="true" />
	
	<spring:message code="event.endDate" var="endDateHeader" />
	<display:column property="endDate" title="${endDateHeader}" format="${formatDate}" sortable="true" />

	<spring:message code="event.publicationDate" var="publicationDateHeader" />
	<display:column property="publicationDate" title="${publicationDateHeader}" format="${formatDate}" sortable="true" />
	
	<security:authorize access="hasRole('VOLUNTARY')">
		<display:column>
			<acme:links url="donation/voluntary/create.do?eventId=${row.id }"
				code="event.donation" />
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('CLIENT')">
		<display:column>
			<acme:links url="donation/client/create.do?eventId=${row.id }"
				code="event.donation" />
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('BOSS')">
		<display:column>
			<acme:links url="event/boss/edit.do?eventId=${row.id }"
				code="event.edit" />
		</display:column>
	</security:authorize>
</display:table>
<br>
<security:authorize access="hasRole('BOSS')">
	<acme:links url="event/boss/create.do" code="event.create" />
</security:authorize>