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


<b><spring:message code="decision.status"/>:&nbsp;</b>
<jstl:if test="${decision.accept == true}">
	<spring:message code="decision.true"/>
</jstl:if>
<jstl:if test="${decision.accept == false}">
	<spring:message code="decision.false"/>
</jstl:if>
<br/>

<spring:message var="patternDate" code="decision.pattern.date"/>
<b><spring:message code="decision.moment"/>:&nbsp;</b><fmt:formatDate value="${decision.moment}" pattern="${patternDate}"/>
<br/>
<b><spring:message code="decision.comment"/>:&nbsp;</b><jstl:out value="${decision.comment}"/>
<br/>
<b><spring:message code="decision.application"/>:&nbsp;</b><jstl:out value="${decision.application.ticker}"/>
<br/>
<br/>
<security:authorize access="hasRole('OFFICER')">
	<a href="decision/officer/edit.do?decisionId=${decision.id}">
	<spring:message code="decision.edit"/></a>
</security:authorize>