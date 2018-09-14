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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form:form action="law/administrator/edit.do" modelAttribute="lawForm">

	<form:hidden path="id" />
	<form:hidden path="laws" />

	<acme:textbox code="law.title" path="title" />
	<br />

	<acme:textbox code="law.text" path="text" />
	<br />

	<acme:textbox code="law.enactmentDate" path="enactmentDate" />
	<br />

	
	<acme:textbox code="law.abrogationTime" path="abrogationTime"/>
	<br />

	<acme:selectlaw items="${countries}" itemLabel="name" code="law.country"
		path="country" />

	<acme:selectlaw items="${lawParent}" itemLabel="title"
		code="law.lawParent" path="lawParent" />

	<acme:selectlaw items="${requirement}" itemLabel="title"
		code="law.requirement" path="requirement" />


	<acme:submit name="save" code="law.save" />
	<jstl:if test="${lawForm.id != 0}">
		<acme:submit name="delete" code="law.delete" />
	</jstl:if>
	<acme:cancel url="law/list.do" code="law.cancel" />

</form:form>