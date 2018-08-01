<%-- list.jsp --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="investigator" requestURI="${requestURI }" id="row">

	<acme:column property="name" code="investigator.name" />
	<acme:column property="surname" code="investigator.surname" />
	<acme:column property="email" code="investigator.email" />
	<acme:column property="phoneNumber" code="investigator.phoneNumber" />
	<acme:column property="address" code="investigator.address" />

	<display:column>
		<a href="report/officer/list.do?investigatorId=${row.id}"><spring:message
 				code="investigator.reports" /></a> 
 	</display:column> 

</display:table>

