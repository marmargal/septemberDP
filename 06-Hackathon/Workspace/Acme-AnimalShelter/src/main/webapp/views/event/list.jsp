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
	
	<acme:column property="startDate" code="event.startDate" />
	<acme:column property="endDate" code="event.endDate" />
	<acme:column property="publicationDate" code="event.publicationDate" />
	
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