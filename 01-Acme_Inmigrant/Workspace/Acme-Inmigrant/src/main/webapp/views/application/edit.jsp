<%--
 * edit.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>
<%@page import="services.ApplicationService"%>
<%@page import="domain.Application"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<form:form action="application/immigrant/edit.do" modelAttribute="applicationForm">
	<form:hidden path="id" />
		
	<acme:select items="${visa}" itemLabel="classes" code="application.visa" path="visa" />
	<acme:textbox path="holderName" code="application.creditCard.holderName" />
	<acme:textbox path="brandName" code="application.creditCard.brandName" />
	<acme:textbox path="number" code="application.creditCard.number" />
	
	<acme:textbox path="expirationMonth" code="application.creditCard.expirationMonth"/>
	<acme:textbox path="expirationYear" code="application.creditCard.expirationYear"/>
	<acme:textbox path="cvv" code="application.creditCard.Cvv"/>
	
	<acme:textbox path="names" code="application.personalSection.name" />
	<acme:textbox path="bithPlace" code="application.personalSection.birthPlace" />
	<acme:date code="personalSection.birthDate" path="bithDate" placeholder="dd/MM/yyyy"/>
	<acme:textbox path="picture" code="application.personalSection.picture" />
	
	<acme:textbox path="nickName" code="application.socialSection.nickName" />
	<acme:textbox path="socialNetwork" code="application.socialSection.socialNetwork" />
	<acme:textbox path="profileLink" code="application.socialSection.profileLink" />
	
	<acme:textbox path="tickerApplicationLinked" code="application.linked" />
	<br/>
	<acme:checkbox code="application.close" path="closed"/>
	
	<acme:submit name="save" code="application.submit" />
	<acme:cancel url="application/immigrant/display.do" code="application.cancel" />
	
</form:form>