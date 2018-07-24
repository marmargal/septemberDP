<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing grid -->

<display:table pagesize="5" class="displaycategory" keepStatus="true"
	name="categories" requestURI="category/list.do" id="row">
	
	<!-- Attributes -->
	
	<security:authorize access="hasRole('ADMIN')">
	<spring:message code="category.edit"/>
	<display:column>
		<a href= "category/administrator/edit.do?categoryId=${row.id}">
		<spring:message code="category.edit"/></a>
	</display:column>
	</security:authorize>
	
	<spring:message code="category.categoryParent" var="categoryParentHeader"/>
	<jstl:if test="${categoryParent != \"root\"}">
	<display:column property="categoryParent.name" title="${categoryParentHeader}" sortable="false"/>
	</jstl:if>
	
	<spring:message code="category.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="false" />
	
	<spring:message code="category.categoryChildren" var="categoryChildren"/>
	<display:column title="${categoryChildren}">
		<a href="category/list.do?categoryId=${row.id}">
			<spring:message code="category.categoryChildren.link"/>
		</a>
	</display:column>
	
	<spring:message code="category.visas" var="visas"/>
	<display:column title="${visas}">
		<a href="visa/category/list.do?categoryId=${row.id }">
			<spring:message code="category.visas"/>
		</a>
	</display:column>
		
</display:table>
<!-- Action links -->
<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="category/administrator/create.do"> <spring:message
				code="category.create" />
		</a>
	</div>
</security:authorize>