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

<form:form action="${requestURI }" modelAttribute="subscription">
	
	<form:hidden path="id"/>
	
	<acme:textbox path="holderName" code="subscription.creditCard.holderName" />
	<acme:textbox path="brandName" code="subscription.creditCard.brandName" />
	<acme:textbox path="number" code="subscription.creditCard.number" />
	<acme:textbox path="expirationMonth" code="subscription.creditCard.expirationMonth"/>
	<acme:textbox path="expirationYear" code="subscription.creditCard.expirationYear"/>
	<acme:textbox path="cvv" code="subscription.creditCard.Cvv"/>
	
	<acme:date code="subscription.endDate" path="endDate" placeholder="dd/MM/yyyy"/>
	
	<acme:select items="${platforms}" itemLabel="name" code="subscription.platform" path="platform"/>
	
	<acme:submit name="save" code="subscription.save"/>
	<acme:cancel url="subscription/user/list.do" code="subscription.cancel"/>
	
</form:form>
