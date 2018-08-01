<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-dark bg-primary">
<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="isAnonymous()">
			<li><a href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv" href=""><spring:message code="master.page.register" /></a>
				<ul>
					<li><a href="immigrant/register.do"><spring:message code="master.page.register.immigrant" /></a></li>
				</ul>
			</li>
			<li><a href="category/list.do"><spring:message code="master.page.hierarchyVisa" /></a></li>
			<li><a href="country/list.do"><spring:message code="master.page.countries" /></a></li>
			<li><a href="visa/list.do"><spring:message code="master.page.findVisa" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li><a href="#" class="fNiv"><security:authentication property="principal.username" /></a>
				<ul>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
			<li><a href="category/list.do"><spring:message code="master.page.hierarchyVisa" /></a></li>
			<li><a href="country/list.do"><spring:message code="master.page.countries" /></a></li>
			<li><a href="visa/list.do"><spring:message code="master.page.findVisa" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('IMMIGRANT')">
			<li><a href="immigrant/edit.do"><spring:message code="master.page.editProfile" /></a></li>
			<li><a href="application/immigrant/create.do"><spring:message code="master.page.application" /></a></li>
			<li><a href="application/immigrant/display.do"><spring:message code="master.page.myApplication" /></a></li>
			<li><a class="fNiv" href="#"><spring:message code="master.page.applicationList" /></a>
				<ul>
					<li><a href="application/immigrant/display.do"><spring:message code="master.page.application.display" /></a></li>
					<li><a href="application/immigrant/listClosed.do"><spring:message code="master.page.register.closedApplication" /></a></li>
					<li><a href="application/immigrant/listAccepted.do"><spring:message code="master.page.register.acceptedApplication" /></a></li>
					<li><a href="application/immigrant/listRejected.do"><spring:message code="master.page.register.rejectedApplication" /></a></li>
					<li><a href="question/immigrant/list.do"><spring:message code="master.page.register.assignApplication" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('OFFICER')">
			<li><a href="officer/edit.do"><spring:message code="master.page.editProfile" /></a></li>
			<li><a class="fNiv" href="#"><spring:message code="master.page.applicationList" /></a>
				<ul>
					<li><a href="application/officer/list.do"><spring:message code="master.page.applicationList" /></a></li>
					<li><a href="application/officer/listAccepted.do"><spring:message code="master.page.register.acceptedApplication" /></a></li>
					<li><a href="application/officer/listRejected.do"><spring:message code="master.page.register.rejectedApplication" /></a></li>
					<li><a href="application/officer/listNoDecision.do"><spring:message code="master.page.register.assignApplication" /></a></li>
				</ul>
			</li>
			<li><a href="immigrant/officer/list.do"><spring:message code="master.page.immigrants" /></a></li>
			<li><a href="investigator/officer/list.do"><spring:message code="master.page.investigator" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('INVESTIGATOR')">
			<li><a href="investigator/edit.do"><spring:message code="master.page.editProfile" /></a></li>
			<li><a href="report/investigator/list.do"><spring:message code="master.page.reports" /></a></li>
			<li><a href="immigrant/investigator/list.do"><spring:message code="master.page.immigrants" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
			<li><a href="administrator/edit.do"><spring:message code="master.page.editProfile" /></a></li>
			<li><a class="fNiv" href=""><spring:message code="master.page.register" /></a>
				<ul>
					<li><a href="officer/administrator/register.do"><spring:message code="master.page.register.officer" /></a></li>
					<li><a href="investigator/administrator/register.do"><spring:message code="master.page.register.investigator" /></a></li>
				</ul>
			</li>
			<li><a href="visa/administrator/list.do"><spring:message code="master.page.visas" /></a></li>
			<li><a href="administrator/list.do"><spring:message code="master.page.dashboard" /></a></li>
			<li><a href="law/list.do"><spring:message code="master.page.laws" /></a></li>
		</security:authorize>
	</ul>
</div>
<div>
	<a href="/Acme-Inmigrant"><img src="images/logo.png" alt="Acme-Inmigrant Co., Inc." /></a>
</div>
</nav>



<div id="language">
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>