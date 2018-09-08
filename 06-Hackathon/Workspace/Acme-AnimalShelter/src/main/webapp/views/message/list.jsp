<%-- list.jsp --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="messages" requestURI="${requestURI }" id="row">
	
	<security:authorize access="hasRole('ADMIN')">
		<jstl:if test="${viewForDelete == true}">
			<display:column>
				<form name="submitForm" method="POST" action="message/administrator/delete.do?messageId=${row.id }">
			    	<acme:submit name="delete" code="message.delete"/>
				</form>
			</display:column>
		</jstl:if>
	</security:authorize>
	
	<acme:column property="folder.actor.name" code="message.sender" />
	
	<spring:message code="message.recipient" var="recipientHeader" />
	<display:column title="${recipientHeader }">
		<jstl:forEach var="folder" items="${row.foldersRecipient}">
			<jstl:out value="${folder.actor.name }"></jstl:out>
		</jstl:forEach>
	</display:column>
	
	<spring:message code="message.moment" var="momentHeader" />
	<spring:message var="formatDate" code="format.date"/>
	<display:column property="moment" title="${momentHeader}" format="${formatDate}" sortable="true" />
	
	<acme:column property="subject" code="message.subject" />
	<acme:column property="body" code="message.body" />
	<acme:column property="priority" code="message.priority" />
	
</display:table>