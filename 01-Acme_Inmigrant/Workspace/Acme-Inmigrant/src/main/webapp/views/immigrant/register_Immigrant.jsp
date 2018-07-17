<%--
 * register_Immigrant.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>
 
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="immigrant/register_Immigrant.do" modelAttribute="immigrantForm">

	<acme:textbox code="actor.username" path="immigrant.userAccount.username" />
	<jstl:choose>
		<jstl:when test="${immigrantForm.immigrant.id==0}">
			<acme:password code="actor.password" path="immigrant.userAccount.password" />
			<br />
			<acme:password code="actor.password" path="confirmPassword" />
			<br />
		</jstl:when>
		<jstl:otherwise></jstl:otherwise>
	</jstl:choose>

	<acme:textbox code="immigrant.name" path="immigrant.name" />
	<acme:textbox code="immigrant.surname" path="immigrant.surname" />
	<acme:textbox code="immigrant.email" path="immigrant.email" />
	<acme:textbox code="immigrant.phoneNumber" path="immigrant.phoneNumber" />
	<acme:textbox code="immigrant.address" path="immigrant.address" />

	<jstl:if test="${immigrantForm.user.id == 0}">
   		<form:label path="terms">
		<spring:message code="immigrant.legal.agree"/><a href="misc/legal.do"><spring:message code="immigrant.legal.info"/></a>
		</form:label>
		<input type="checkbox" id="terms" name="terms" required /> <spring:message code="immigrant.legal.agree" /><br>
		<form:errors cssClass="error" path="terms"/>
   </jstl:if>
	
	<br />
	
	<acme:submit name="save" code="immigrant.submit" />
	<acme:cancel url="/" code="immigrant.cancel" />
	
	<br />
</form:form>

<script type="text/javascript">
$('#form input[type=checkbox]').on('change invalid', function() {
    var campotexto = $(this).get(0);
    campotexto.setCustomValidity('');
    if (!campotexto.validity.valid) {
      campotexto.setCustomValidity('<jstl:out value="${check}"/>');  
    }
});
</script>