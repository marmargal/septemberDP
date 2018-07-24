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
			<li><a href="#"><spring:message code="master.page.hierarchyVisa" /></a></li>
			<li><a href="visa/list.do"><spring:message code="master.page.findVisa" /></a></li>
			<li><a href="#"><spring:message code="master.page.requirements" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li><a href="#" class="fNiv"><security:authentication property="principal.username" /></a>
				<ul>
					<li><a href="#"><spring:message code="master.page.editProfile" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
			<li><a href="#"><spring:message code="master.page.hierarchyVisa" /></a></li>
			<li><a href="visa/list.do"><spring:message code="master.page.findVisa" /></a></li>
			<li><a href="requirement/administrator/list.do"><spring:message code="master.page.requirements" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('IMMIGRANT')">
			<li><a href="#"><spring:message code="master.page.application" /></a></li>
			<li><a href="#"><spring:message code="master.page.myApplication" /></a></li>
			<li><a href="application/immigrant/display.do"><spring:message code="master.page.application.display" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('OFFICER')">
			<li><a class="fNiv" href="#"><spring:message code="master.page.applicationList" /></a>
				<ul>
					<li><a href="immigrant/register.do"><spring:message code="master.page.register.acceptedApplication" /></a></li>
					<li><a href="immigrant/register.do"><spring:message code="master.page.register.rejectedApplication" /></a></li>
					<li><a href="immigrant/register.do"><spring:message code="master.page.register.assignApplication" /></a></li>
				</ul>
			</li>
			<li><a href="question/officer/list.do"><spring:message code="master.page.questions" /></a></li>
			<li><a href="/report/display.do"><spring:message code="master.page.reports" /></a></li>
			<li><a href="immigrant/officer/list.do"><spring:message code="master.page.immigrants" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('INVESTIGATOR')">
			<li><a href="report/investigator/list.do"><spring:message code="master.page.reports" /></a></li>
			<li><a href="immigrant/investigator/list.do"><spring:message code="master.page.immigrants" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv" href=""><spring:message code="master.page.register" /></a>
				<ul>
					<li><a href="officer/register.do"><spring:message code="master.page.register.officer" /></a></li>
					<li><a href="investigator/register.do"><spring:message code="master.page.register.investigator" /></a></li>
				</ul>
			</li>
			<li><a href="#"><spring:message code="master.page.visas" /></a></li>
			<li><a href="#"><spring:message code="master.page.dashboard" /></a></li>
			<li><a href="#"><spring:message code="master.page.countries" /></a></li>
			<li><a href="#"><spring:message code="master.page.laws" /></a></li>
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