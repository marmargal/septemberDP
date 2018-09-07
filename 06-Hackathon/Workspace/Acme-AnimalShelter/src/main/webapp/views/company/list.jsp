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

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="companys" requestURI="${requestURI }" id="row">
	
	<security:authorize access="hasRole('BOSS')">
		<display:column>
			<jstl:if test="${row.event == null}">
				<acme:links url="company/boss/affiliate.do?companyId=${row.id }" code="company.affiliate" />
			</jstl:if>
		</display:column>
	</security:authorize>

	<acme:column property="name" code="company.name" />
	<acme:column property="description" code="company.description" />
	<acme:column property="articles" code="company.articles" />
	
	<spring:message code="company.logo" var="logoHeader" />
	<display:column title="${logoHeader}" sortable="false">
		<img src="<jstl:out value="${row.logo}"/>" width="200" height="87">
	</display:column>
	
	<security:authorize access="hasRole('BOSS')">
		<display:column>
			<acme:links url="company/boss/edit.do?companyId=${row.id }"
				code="company.edit" />
		</display:column>
	</security:authorize>
</display:table>
<br>
<security:authorize access="hasRole('BOSS')">
	<acme:links url="company/boss/create.do" code="company.create" />
</security:authorize>