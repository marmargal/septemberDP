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

<b><spring:message code="amenity.name"/>:&nbsp;</b><jstl:out value="${amenity.name}"/>
<br/>

<b><spring:message code="amenity.description"/>:&nbsp;</b><jstl:out value="${amenity.description}"/>
<br/>

<b><spring:message code="amenity.pictures"/>:&nbsp;</b>
<br/>
<jstl:forEach var="picture" items="${amenity.pictures}">
	<img src="<jstl:out value="${picture}"/>" width="450" height="174">
	<br/>
</jstl:forEach>

<acme:cancel url="amenity/list.do?innId=${amenity.inn.id}" code="amenity.back"/>
