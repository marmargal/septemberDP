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

<form:form action="country/administrator/edit.do" modelAttribute="countryForm">
	
	<form:hidden path="id"/>
	
	<acme:textbox code="country.name" path="name"/>
	<br/>
	
	<acme:textbox code="country.isoCode" path="isoCode"/>
	<br/>
	
	<acme:textbox code="country.flag" path="flag"/>
	<br/>
	
	<acme:textbox code="country.link" path="link"/>
	<br/>
	
	<acme:submit name="save" code="country.save"/>
	<jstl:if test="${countryForm.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="country.delete" />"
				onclick="return confirm('<spring:message code="country.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<acme:cancel url="country/list.do" code="country.cancel"/>
	
</form:form>
