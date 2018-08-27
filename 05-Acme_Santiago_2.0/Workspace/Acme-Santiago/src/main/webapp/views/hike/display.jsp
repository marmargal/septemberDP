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

<b><spring:message code="hike.name" />:&nbsp;</b>
<jstl:out value="${hike.name}" />
<br />

<b><spring:message code="hike.length" />:&nbsp;</b>
<jstl:out value="${hike.length}" />
<br />

<b><spring:message code="hike.description" />:&nbsp;</b>
<jstl:out value="${hike.description}" />
<br />

<b><spring:message code="hike.originCity" />:&nbsp;</b>
<jstl:out value="${hike.originCity}" />
<br />

<b><spring:message code="hike.destinationCity" />:&nbsp;</b>
<jstl:out value="${hike.destinationCity}" />
<br />

<b><spring:message code="hike.dificultLevel" />:&nbsp;</b>
<jstl:out value="${hike.dificultLevel}" />
<br />
<br />

<display:table name="advertisement" class="displaytag" id="row">

	<acme:column property="title" code="advertisement.title" />
	<acme:column property="banner" code="advertisement.banner" />
	<acme:column property="targetPage" code="advertisement.targetPage" />
	<acme:column property="creditCard.number" code="advertisement.creditCard.number" />
	<acme:column property="activeDays" code="advertisement.activeDays" />
	<acme:column property="taboo" code="advertisement.taboo" />

</display:table>