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

<p>
	<b><spring:message code="tutorial.moment"/>:&nbsp;</b>
	<jstl:out value="${tutorial.moment }"/>
</p>

<p>
	<b><spring:message code="tutorial.text"/>:&nbsp;</b>
	<jstl:out value="${tutorial.text }"/>
</p>

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

<jstl:forEach var="picture" items="${pictures }">
	<img src="<jstl:out value="${picture }"/>" 
	width="450" height="174">
</jstl:forEach>
<br/>

<security:authorize access="hasRole('USER')">
	<b><acme:links url="comment/user/list.do?tutorialId=${tutorial.id }" code="tutorial.comments"/></b>
	<br/>
</security:authorize>

<security:authorize access="hasRole('ADMIN')">
	<b><acme:links url="comment/user/list.do?tutorialId=${tutorial.id }" code="tutorial.comments"/></b>
	<br/>
</security:authorize>
