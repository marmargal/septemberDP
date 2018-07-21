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
	name="answer" requestURI="${requestURI }" id="row">
	
	<spring:message code="answer.reply" var="replyHeader" />
	<display:column property="reply" title="${replyHeader}" sortable="true" />

	<spring:message code="answer.moment" var="momentHeader" />
	<spring:message var="formatDate" code="answer.format.date"/>
	<display:column property="moment" title="${momentHeader}" format="${formatDate}" sortable="true" />

</display:table>

