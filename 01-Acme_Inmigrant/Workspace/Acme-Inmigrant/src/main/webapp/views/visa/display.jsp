<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<b><spring:message code="visa.classes"/>:&nbsp;</b><jstl:out value="${visa.classes}"/>
<br/>

<b><spring:message code="visa.description"/>:&nbsp;</b><jstl:out value="${visa.description}"/>
<br/>

<b><spring:message code="visa.price"/>:&nbsp;</b><jstl:out value="${visa.price}"/>
<br/>

<b><spring:message code="visa.invalidate"/>:&nbsp;</b><jstl:out value="${visa.invalidate}"/>
<br/>

<b><spring:message code="visa.country"/>:&nbsp;</b><jstl:out value="${visa.country.name}"/>
<br/>

<b><spring:message code="visa.category"/>:&nbsp;</b><jstl:out value="${visa.category.name}"/>
<br/>

<b><spring:message code="visa.creditCard.number"/>:&nbsp;</b><jstl:out value="${visa.creditCard.number}"/>
<br/>
