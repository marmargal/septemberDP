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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="category/administrator/edit.do"
	modelAttribute="category">

	<form:hidden path="id" />
	<form:hidden path="rootCategory" />
	<form:hidden path="categories" />
	<form:hidden path="visas" />




	<acme:textbox code="category.name" path="name" />
	<br />


	<acme:select items="${parents}" itemLabel="name"
		code="category.categoryParent" path="categoryParent" />

	<acme:submit name="save" code="category.save" />
	<jstl:if test="${category.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="category.delete" />"
			onclick="return confirm('<spring:message code="category.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<acme:cancel url="category/list.do" code="category.cancel" />

</form:form>
