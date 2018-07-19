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

<form:form action="country/admin/edit.do" modelAttribute="countryForm">
	
	<acme:textbox code="country.name" path="name"/>
	<br/>
	
	<acme:textbox code="country.isoCode" path="isoCode"/>
	<br/>
	
	<acme:textbox code="country.flag" path="flag"/>
	<br/>
	
	<acme:textbox code="country.link" path="link"/>
	<br/>
	
	<input type="submit" name="save" value="<spring:message code="country.save" />" />&nbsp;
	<input type="button" name="cancel" value="<spring:message code="country.cancel" />" onclick="javascript: relativeRedir('/');" />
	
</form:form>
