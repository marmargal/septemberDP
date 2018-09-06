<%-- list.jsp --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<b><spring:message code="notice.type"/>:&nbsp;</b><jstl:out value="${notice.type}"/>
<br/>

<b><spring:message code="notice.description"/>:&nbsp;</b><jstl:out value="${notice.description}"/>
<br/>

<b><spring:message code="notice.gpsCoordinates.latitude"/>:&nbsp;</b><jstl:out value="${notice.gpsCoordinates.latitude}"/>
<br/>

<b><spring:message code="notice.gpsCoordinates.longitude"/>:&nbsp;</b><jstl:out value="${notice.gpsCoordinates.longitude}"/>
<br/>

<b><spring:message code="notice.level"/>:&nbsp;</b><jstl:out value="${notice.level}"/>
<br/>

<b><spring:message code="notice.date"/>:&nbsp;</b><jstl:out value="${notice.date}"/>
<br/>

<b><spring:message code="notice.discarded"/>:&nbsp;</b><jstl:out value="${notice.discarded}"/>
<br/>

<security:authorize access="hasRole('ADMIN')">
	<acme:cancel url="/notice/administrator/list.do" code="notice.back"/>
</security:authorize>
<security:authorize access="hasRole('EMPLOYEE')">
	<acme:cancel url="/notice/employee/list.do" code="notice.back"/>
</security:authorize>
