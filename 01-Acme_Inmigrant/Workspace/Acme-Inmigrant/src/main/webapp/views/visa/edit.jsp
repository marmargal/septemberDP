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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<form:form action="visa/administrator/edit.do" modelAttribute="visa">

	<form:hidden path="id" />
	<form:hidden path="version" />




	<spring:message var="visaClasses" code="visa.classes" />
	<b><form:label path="Classes" />${visaClasses}:&nbsp;</b>
	<form:input path="Classes" />
	<form:errors path="classes" cssClass="error" />
	<br />

	<spring:message var="visaDescription" code="visa.description" />
	<b><form:label path="Description" />${visaDescription}:&nbsp;</b>
	<form:input path="Description" />
	<form:errors path="description" cssClass="error" />
	<br />

	<spring:message var="visaPrice" code="visa.price" />
	<b><form:label path="Price" />${visaPrice}:&nbsp;</b>
	<form:input path="Price" />
	<form:errors path="price" cssClass="error" />
	<br />



	<acme:select items="${countries}" itemLabel="name" code="visa.country"
		path="country" />


	<acme:select items="${categories}" itemLabel="name"
		code="visa.category" path="category" />
	<jstl:if test="${visa.id != 0}">
		<acme:selectBoolean items="${invalidate}" code="visa.invalidate"
			path="invalidate" />
	</jstl:if>

	<spring:message var="visaSave" code="visa.save" />
	<input type="submit" name="save" value="${visaSave}" />
	


	<spring:message var="visaCancel" code="visa.Cancel" />
	<input type="button" name="cancel" value="${visaCancel}"
		onclick="javascript:relativeRedir('visa/list.do')" />
</form:form>
