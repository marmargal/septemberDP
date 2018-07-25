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

<b><spring:message code="requirement.title"/>:&nbsp;</b><jstl:out value="${requirement.title}"/>
<br/>

<b><spring:message code="requirement.description"/>:&nbsp;</b><jstl:out value="${requirement.description}"/>
<br/>

<b><spring:message code="requirement.abrogated"/>:&nbsp;</b><jstl:out value="${requirement.abrogated}"/>
<br/>

<b><spring:message code="requirement.law"/>:&nbsp;</b><jstl:out value="${requirement.law.title}"/>
<br/>
