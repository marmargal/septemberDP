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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${requestURI }" modelAttribute="requestForm">
	
	<form:hidden path="id"/>
	<form:hidden path="description"/>
	<form:hidden path="antenna"/>
	<form:hidden path="creditCard"/>
	<form:hidden path="requestHandyworker"/>
	
	<acme:textbox code="request.result" path="result" />
	<br/>
	
	<acme:submit name="save" code="request.save"/>
	<acme:cancel url="request/handyworker/listWithoutServiced.do" code="request.cancel"/>
	
	
</form:form>
