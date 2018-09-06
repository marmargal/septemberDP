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

<form:form action="${requestURI }" modelAttribute="stand" method="POST">
	<form:hidden path="id" />
	<form:hidden path="version" />

	<acme:textarea code="stand.numMaxVoluntaries" path="numMaxVoluntaries" type="number" />
	<acme:textarea code="stand.fliers" path="fliers" />
	<acme:selectBoolean items="${invalidate}" code="stand.isOfCompany" path="isOfCompany"/>
		<acme:select items="${companies}" itemLabel="name" code="stand.company" path="company"/>
	
	<acme:submit name="save" code="stand.save" />
	<jstl:if test="${stand.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="stand.delete" />"
				onclick="return confirm('<spring:message code="stand.confirm.delete" />')" />&nbsp;
	</jstl:if>

</form:form>
