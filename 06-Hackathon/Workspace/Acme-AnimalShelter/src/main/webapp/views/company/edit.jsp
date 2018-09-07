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

<form:form action="${requestURI }" modelAttribute="company" method="POST">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="event" />

	<acme:textarea code="company.name" path="name" />
	<acme:textarea code="company.description" path="description" />
	<acme:textarea code="company.articles" path="articles" />
	<acme:textarea code="company.logo" path="logo" />
	
	<acme:submit name="save" code="company.save" />
	<jstl:if test="${company.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="company.delete" />"
				onclick="return confirm('<spring:message code="company.confirm.delete" />')" />&nbsp;
	</jstl:if>

</form:form>
