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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${requestURI }" modelAttribute="requestForm">
	
	<form:hidden path="id"/>
	<form:hidden path="result"/>
	
	<acme:textbox code="request.creditCard.holderName" path="creditCard.holderName" />
	<acme:textbox code="request.creditCard.brandName" path="creditCard.brandName" />
	<acme:textbox code="request.creditCard.number" path="creditCard.number" />
	<acme:textbox code="request.creditCard.expirationMonth" path="creditCard.expirationMonth" />
	<acme:textbox code="request.creditCard.expirationYear" path="creditCard.expirationYear" />
	<acme:textbox code="request.creditCard.Cvv" path="creditCard.cvv" />
	<br/>
	
	<acme:textarea code="request.description" path="description"/>
	<br/>
	
	<acme:select items="${antennas }" itemLabel="model" code="request.antenna" path="antenna.id"/>
	<br/>
	
	
	<acme:submit name="save" code="request.save"/>
	<acme:cancel url="request/user/list.do" code="request.cancel"/>
	
	
</form:form>
