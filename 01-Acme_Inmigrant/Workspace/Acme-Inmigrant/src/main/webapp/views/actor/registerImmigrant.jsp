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

<form:form action="immigrant/register.do" modelAttribute="immigrantForm">

	<acme:textbox code="actor.username" path="actor.userAccount.username" />
	<jstl:choose>
		<jstl:when test="${immigrantForm.immigrant.id==0}">
			<acme:password code="actor.password" path="actor.userAccount.password" />
			<br />
			<acme:password code="actor.password" path="confirmPassword" />
			<br />
		</jstl:when>
		<jstl:otherwise></jstl:otherwise>
	</jstl:choose>

	<acme:textbox code="actor.name" path="actor.name" />
	<acme:textbox code="actor.surname" path="actor.surname" />
	<acme:textbox code="actor.email" path="actor.email" />
	<acme:textbox code="actor.phoneNumber" path="actor.phoneNumber" />
	<acme:textbox code="actor.address" path="actor.address" />

	<jstl:if test="${immigrantForm.user.id == 0}">
   		<form:label path="terms">
		<spring:message code="actor.legal.agree"/><a href="misc/legal.do"><spring:message code="actor.legal.info"/></a>
		</form:label>
		<input type="checkbox" id="terms" name="terms" required /> <spring:message code="actor.legal.agree" /><br>
		<form:errors cssClass="error" path="terms"/>
   </jstl:if>
	
	<br />
	
	<acme:submit name="save" code="actor.submit" />
	<acme:cancel url="/" code="actor.cancel" />
	
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