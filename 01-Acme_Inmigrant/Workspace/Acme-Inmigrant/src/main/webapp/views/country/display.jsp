<%-- edit.jsp de Report --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<b><spring:message code="country.name"/>:&nbsp;</b><jstl:out value="${country.name}"/>
<br/>

<b><spring:message code="country.isoCode"/>:&nbsp;</b><jstl:out value="${country.isoCode}"/>
<br/>

<b><spring:message code="country.flag"/>:&nbsp;</b><jstl:out value="${country.flag}"/>
<br/>

<acme:links url="${country.link }" code="country.link"/>

<security:authorize access="hasRole('ADMIN')">
	<a href="country/administrator/edit.do"><spring:message code="country.edit"/></a>
</security:authorize>
