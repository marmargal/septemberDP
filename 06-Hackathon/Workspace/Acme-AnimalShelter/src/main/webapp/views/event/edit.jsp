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

<form:form action="${requestURI }" modelAttribute="event" method="POST">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="publicationDate" />

	<acme:textarea code="event.title" path="title" />
	<acme:textarea code="event.description" path="description" />
	<acme:textarea code="event.donation" path="donation" type="number" />
	<acme:textarea code="event.nameSite" path="nameSite" />
	<acme:textarea code="event.placard" path="placard" />
	<acme:textarea code="event.address" path="address" />
	<acme:date code="event.startDate" path="startDate" placeholder="dd/MM/yyyy HH:mm"/>
	<acme:date code="event.endDate" path="endDate" placeholder="dd/MM/yyyy HH:mm"/>
	<acme:select items="${centers}" itemLabel="name" code="event.center" path="center"/>
	
	<acme:submit name="save" code="event.save" />
	<jstl:if test="${event.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="event.delete" />"
				onclick="return confirm('<spring:message code="event.confirm.delete" />')" />&nbsp;
	</jstl:if>

</form:form>
