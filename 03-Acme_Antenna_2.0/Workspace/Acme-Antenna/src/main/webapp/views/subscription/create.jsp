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

<form:form action="${requestURI }" modelAttribute="subscriptionForm">
	
	<form:hidden path="id"/>
	
	<acme:textbox code="subscription.creditCard.holderName" path="holderName" />
	<acme:textbox code="subscription.creditCard.brandName" path="brandName" />
	<acme:textbox code="subscription.creditCard.number" path="number" />
	<acme:textbox code="subscription.creditCard.expirationMonth" path="expirationMonth" />
	<acme:textbox code="subscription.creditCard.expirationYear" path="expirationYear" />
	<acme:textbox code="subscription.creditCard.Cvv" path="cvv" />
	
	<acme:date code="subscription.endDate" path="endDate" placeholder="dd/MM/yyyy"/>
	
	<acme:select items="${platforms}" itemLabel="name" code="subscription.platform" path="platform"/>
	
	<acme:submit name="save" code="subscription.save"/>
	<acme:cancel url="subscription/user/list.do" code="subscription.cancel"/>
	
</form:form>
