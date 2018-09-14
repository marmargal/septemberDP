<%--
 * edit.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<p>
	<b><spring:message code="tutorial.title"/>:&nbsp;</b>
	<jstl:out value="${tutorial.title }"/>
</p>

<jstl:forEach var="p" items="${pictures }" varStatus="status">
	<img class="imagen" src="${p }"/>
	<br/>
</jstl:forEach>

<p>
	<spring:message var="patternDate" code="tutorial.pattern.date"/>
	<b><spring:message code="tutorial.moment"/>:&nbsp;</b><fmt:formatDate value="${tutorial.moment}" pattern="${patternDate}"/>
</p>

<p>
	<b><spring:message code="tutorial.text"/>:&nbsp;</b>
	<jstl:out value="${tutorial.text }"/>
</p>

<security:authorize access="hasRole('USER')">
	<table style="width:100%">
	<jstl:forEach var="comment" items="${comments }" varStatus="status">
	<tr>
	<td><b>${status.index + 1 }</b></td>
	<td>${comment.title}</td>
	<td>${comment.text}</td>
	<td>${comment.moment}</td>
	<td>
	<jstl:forEach var="p" items="${comment.pictures }">
		<img class="imagen" src="${p }"/>	
	</jstl:forEach>
	</td>
	</tr>
	<hr/>
	</jstl:forEach>
	</table>
	<br/>
</security:authorize>

<security:authorize access="hasRole('USER')">
	<b><acme:links url="comment/user/list.do?tutorialId=${tutorial.id }" code="tutorial.comments"/></b>
	<br/>
</security:authorize>

<security:authorize access="hasRole('ADMIN')">
	<b><acme:links url="comment/user/list.do?tutorialId=${tutorial.id }" code="tutorial.comments"/></b>
	<br/>
</security:authorize>
