<%--
 * list.jsp
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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table pagesize="5" class="comments" keepStatus="true"
	name="comments" requestURI="${requestURI }" id="row">

	<spring:message code="comment.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"	sortable="true" />
		
	<spring:message var="formatDate" code="comment.format.date"/>
	<spring:message code="comment.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}" format="${formatDate}" sortable="true" />

	<spring:message code="comment.text" var="textHeader" />
	<display:column property="text" title="${textHeader}"	sortable="true" />
	
	<spring:message code="comment.user" var="userHeader" />
	<display:column property="user.name" title="${userHeader}"	sortable="true" />
	
	<display:column>
	<jstl:forEach var="p" items="${row.pictures }">
		<img class="imagen" src="${p }"/>	
	</jstl:forEach>
	</display:column>
	
	<display:column> <acme:links url="comment/user/createReply.do?commentId=${row.id}" code="comment.createReply" /> </display:column>
	<display:column> <acme:links url="comment/user/listReplies.do?commentId=${row.id}" code="comment.reply" /> </display:column>
	
	<security:authorize access="hasRole('ADMIN')">
	<display:column>
	<acme:links url="comment/administrator/edit.do?commentId=${row.id}" code="comment.delete"/>
	</display:column>
	</security:authorize>

</display:table>

<security:authorize access="hasRole('USER')">
	<jstl:if test="${(requestScope['javax.servlet.forward.request_uri'] == '/Acme-Antenna/comment/user/list.do')}">
		<acme:links url="comment/user/create.do?tutorialId=${tutorial.id}" code="comment.create"/>
	</jstl:if>
</security:authorize>