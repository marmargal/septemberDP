<%-- list.jsp --%>

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

<form:form action="${requestURI }" modelAttribute="pet" method="POST">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="application" />
	<form:hidden path="medicalReport" />
	<form:hidden path="identifier" />

	<acme:textarea code="pet.name" path="name" />
	<form:label path="type">
		<spring:message code="pet.type" />:
	</form:label>
	<form:select path="type">
		<form:option value="DOG" />
		<form:option value="CAT" />
		<form:option value="BIRD" />
	</form:select>
	<acme:textarea code="pet.foodExpense" path="foodExpense" type="number" />
	<acme:selectBoolean items="${invalidate}" code="pet.status" path="status"/>
	<acme:date code="pet.date" path="date" placeholder="dd/MM/yyyy"/>
	<acme:textarea code="pet.age" path="age" type="number" />
	<acme:selectBoolean items="${invalidate}" code="pet.chip" path="chip"/>
	<acme:select items="${centers}" itemLabel="name" code="pet.center" path="center"/>
	
	<acme:submit name="save" code="pet.save" />
	<jstl:if test="${pet.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="pet.delete" />"
				onclick="return confirm('<spring:message code="pet.confirm.delete" />')" />&nbsp;
	</jstl:if>
</form:form>
