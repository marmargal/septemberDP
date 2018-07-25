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

<form:form action="law/administrator/edit.do" modelAttribute="lawForm">

	<form:hidden path="id"/>
	<form:hidden path="enactmentDate"/>
	<form:hidden path="abrogationTime"/>
	
	<acme:textbox code="law.title" path="title" />
	<br />

	<acme:textbox code="law.text" path="text" />
	<br />
	
	<acme:select items="${requirement}" itemLabel="title" code="law.requirement" path="requirement"/>
	
	<acme:select items="${laws}" itemLabel="title" code="law.lawChildren" path="laws"/>

	<acme:submit name="save" code="law.save" />

	<acme:submit name="delete" code="law.delete" />
	<acme:cancel url="law/list.do" code="law.cancel" />

</form:form>