<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table pagesize="6" class="displaycomment" keepStatus="true"
	name="question" requestURI="${requestURI }" id="row">
	
	<security:authorize access="hasRole('IMMIGRANT')">
		
		<jstl:if test="${row.statement == false}">
			<spring:message code="question.answer" var="answerHeader" />
			<display:column title="${answerHeader}">
				<p>-</p>
			</display:column>
		
			<spring:message code="question.create" var="createHeader" />
			<display:column title="${createHeader}">
				<a href="answer/immigrant/edit.do?questionId=${row.id }"><spring:message code="answer.create"/></a>
			</display:column>
		</jstl:if>
		
		<jstl:if test="${row.statement == true}">
			<spring:message code="question.answer" var="answerHeader" />
			<display:column title="${answerHeader}">
				<a href="answer/list.do?questionId=${row.id }"><spring:message code="question.answer"/></a>
			</display:column>
		
			<spring:message code="question.create" var="createHeader" />
			<display:column title="${createHeader}">
				<p>-</p>
			</display:column>
		</jstl:if>

	</security:authorize>
	
	<spring:message code="question.text" var="nameHeader" />
	<display:column property="text" title="${nameHeader}" sortable="true" />

	<spring:message code="question.statement" var="statementHeader" />
	<display:column property="statement" title="${statementHeader}" sortable="true" />

	<spring:message code="question.moment" var="momentHeader" />
	<spring:message var="formatDate" code="question.format.date"/>
	<display:column property="moment" title="${momentHeader}" format="${formatDate}" sortable="true" />

</display:table>

<security:authorize access="hasRole('OFFICER')">
	<a href="question/officer/edit.do?applicationId=${applicationId}"><spring:message code="question.create"/></a>
	<br/>
</security:authorize>