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
<form:form action="law/display.do" modelAttribute="law">
<b><spring:message code="law.title"/>:&nbsp;</b><jstl:out value="${law.title}"/>
<br/>

<b><spring:message code="law.text"/>:&nbsp;</b><jstl:out value="${law.text}"/>
<br/>

<spring:message var="patternDate" code="law.pattern.date"/>

<b><spring:message code="law.enactmentDate"/>:&nbsp;</b><fmt:formatDate value="${law.enactmentDate}" pattern="${patternDate}"/>
<br/>
<b><spring:message code="law.abrogationTime"/>:&nbsp;</b><fmt:formatDate value="${law.abrogationTime}" pattern="${patternDate}"/>
<br/>

<acme:cancel url="law/list.do" code="law.cancel"/>

</form:form>