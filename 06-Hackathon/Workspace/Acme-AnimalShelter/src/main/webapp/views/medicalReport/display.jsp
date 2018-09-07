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

<b><spring:message code="medicalReport.pet.name"/>:&nbsp;</b><jstl:out value="${medicalReport.pet.name}"/>
<br/>

<b><spring:message code="medicalReport.pet.type"/>:&nbsp;</b><jstl:out value="${medicalReport.pet.type}"/>
<br/>

<b><spring:message code="medicalReport.pet.identifier"/>:&nbsp;</b><jstl:out value="${medicalReport.pet.identifier}"/>
<br/>

<b><spring:message code="medicalReport.diagnosis"/>:&nbsp;</b><jstl:out value="${medicalReport.diagnosis}"/>
<br/>

<b><spring:message code="medicalReport.initialState"/>:&nbsp;</b><jstl:out value="${medicalReport.initialState}"/>
<br/>

<b><spring:message code="medicalReport.treatment"/>:&nbsp;</b><jstl:out value="${medicalReport.treatment}"/>
<br/>

<b><spring:message code="medicalReport.diseases"/>:&nbsp;</b><jstl:out value="${medicalReport.diseases}"/>
<br/>

<security:authorize access="hasRole('VETERINARY')">
	<acme:cancel url="/pet/veterinary/list.do" code="medicalReport.back"/>
</security:authorize>
