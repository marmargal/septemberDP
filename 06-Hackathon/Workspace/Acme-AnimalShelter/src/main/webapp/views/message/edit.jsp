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

<form:form action="${requestUri}" modelAttribute="messageForm">
	<form:hidden path="id"/>
	
	<acme:selectObligatory items="${actors}" itemLabel="name" code="message.recipient" path="recipients"/>
	
	<form:label path="priority">
		<spring:message code="message.priority" />
	</form:label>
	<form:select path="priority">
		<form:option value="LOW" />
		<form:option value="NEUTRAL" />
		<form:option value="HIGH" />
	</form:select>
	<form:errors cssClass="error" path="priority" />
	
	<acme:textbox code="message.subject" path="subject"/>
	<acme:textarea code="message.body" path="body"/>
	
	<acme:submit name="save" code="message.save"/>
	<acme:cancel url="message/actor/listOutBox.do" code="message.cancel"/> 
</form:form>