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

<b><spring:message code="antenna.serialNumber"/>:&nbsp;</b><jstl:out value="${antenna.serialNumber}"/>
<br/>

<b><spring:message code="antenna.model"/>:&nbsp;</b><jstl:out value="${antenna.model}"/>
<br/>

<b><spring:message code="antenna.latitude"/>:&nbsp;</b><jstl:out value="${antenna.coordinates.latitude}"/>
<br/>

<b><spring:message code="antenna.longitude"/>:&nbsp;</b><jstl:out value="${antenna.coordinates.longitude}"/>
<br/>

<b><spring:message code="antenna.azimuth"/>:&nbsp;</b><jstl:out value="${antenna.azimuth}"/>
<br/>

<b><spring:message code="antenna.elevation"/>:&nbsp;</b><jstl:out value="${antenna.elevation}"/>
<br/>

<b><spring:message code="antenna.quality"/>:&nbsp;</b><jstl:out value="${antenna.quality}"/>
<br/>

<b><spring:message code="antenna.satellite"/>:&nbsp;</b><jstl:out value="${antenna.satellite.name}"/>
<br/>

<acme:cancel url="antenna/user/list.do" code="antenna.cancel"/>
