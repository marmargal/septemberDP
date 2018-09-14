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

<form:form action="${requestURI }" modelAttribute="center" method="POST">
	<form:hidden path="id" />
	<form:hidden path="version" />

	<acme:textarea code="center.name" path="name" />

	<acme:textarea code="center.address" path="address" />

	<acme:textarea code="center.capacity" path="capacity" type="number" />

	<acme:textarea code="center.stock" path="stock" type="number" />

	<acme:textarea code="center.picture" path="picture" type="URL" />

	<acme:textarea code="center.description" path="description" />


	<acme:textarea code="center.warehouse.capacity"
		path="warehouse.capacity" type="number" />
	<acme:textarea code="center.warehouse.stock" path="warehouse.stock"
		type="number" />

	<acme:textarea code="center.warehouse.capacity"
		path="warehouse.capacity" type="number" />

	<acme:textarea code="center.warehouse.dogFood" path="warehouse.dogFood"
		type="number" />
	<acme:textarea code="center.warehouse.birdFood"
		path="warehouse.birdFood" type="number" />
	<acme:textarea code="center.warehouse.catFood" path="warehouse.catFood"
		type="number" />

	<acme:submit name="save" code="center.save" />

	<security:authorize access="hasRole('ADMIN')">
		<jstl:if test="${center.id != 0}">
			<jstl:if test="${employee==true }">
			<input type="submit" name="delete"
				value="<spring:message code="center.delete" />"
				onclick="return confirm('<spring:message code="center.confirm.delete" />')" />&nbsp;
				</jstl:if>
	</jstl:if>
	</security:authorize>
	<security:authorize access="hasRole('BOSS')">
		<jstl:if test="${center.id != 0}">

			<jstl:if test="${employee==true }">
				<input type="submit" name="delete"
					value="<spring:message code="center.delete" />"
					onclick="return confirm('<spring:message code="center.confirm.delete" />')" />&nbsp;
				</jstl:if>
		</jstl:if>
	</security:authorize>
	<br>
	<spring:message code="center.employees" />
</form:form>
