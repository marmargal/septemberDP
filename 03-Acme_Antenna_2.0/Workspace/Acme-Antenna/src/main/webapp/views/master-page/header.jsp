<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-dark bg-primary">
	<div>
		<ul id="jMenu">
			<!-- Do not forget the "fNiv" class for the first level links !! -->
			<security:authorize access="isAnonymous()">
				<li><a class="fNiv" href="security/login.do"><spring:message
							code="master.page.login" /></a></li>
				<li><a class="fNiv" href=""><spring:message
							code="master.page.register" /></a>
					<ul>
						<li><a href="user/register.do"><spring:message
									code="master.page.register.user" /></a></li>
						<li><a href="handyworker/register.do"><spring:message
									code="master.page.register.handyworker" /></a></li>
					</ul></li>
				<li><a href="satellite/list.do"><spring:message
							code="master.page.satellites" /></a></li>
				<li><a href="platform/list.do"><spring:message
							code="master.page.platforms" /></a></li>
				<li><a href="tutorial/list.do"><spring:message
							code="master.page.tutorials" /></a></li>
				<li><a href="handyworker/list.do"><spring:message
							code="master.page.handyworkers" /></a></li>
			</security:authorize>

			<security:authorize access="hasRole('ADMIN')">
				<li><a href="#" class="fNiv"><spring:message
							code="master.page.administrator" /></a>
					<ul>
						<li class="arrow"></li>
						<li><a href="j_spring_security_logout"><spring:message
									code="master.page.logout" /> </a></li>
					</ul></li>
			</security:authorize>

			<security:authorize access="hasRole('USER')">
				<li><a href="#" class="fNiv"> <spring:message
							code="master.page.profile" /> (<security:authentication
							property="principal.username" />)
				</a>
					<ul>
						<li class="arrow"></li>
						<li><a href="actor/user/edit.do"><spring:message
									code="master.page.edit.profile" /></a></li>
						<li><a href="j_spring_security_logout"><spring:message
									code="master.page.logout" /> </a></li>
					</ul></li>
				<li><a href="antenna/user/list.do"><spring:message
							code="master.page.user.antennas" /></a></li>
				<li><a href="subscription/user/list.do"><spring:message
							code="master.page.user.subscription" /></a></li>
			</security:authorize>

			<security:authorize access="hasRole('HANDYWORKER')">
				<li><a href="#" class="fNiv"> <spring:message
							code="master.page.profile" /> (<security:authentication
							property="principal.username" />)
				</a>
					<ul>
						<li class="arrow"></li>
						<li><a href="j_spring_security_logout"><spring:message
									code="master.page.logout" /> </a></li>
					</ul></li>
					
				<li><a href="request/handyworker/list.do"><spring:message
							code="master.page.requests" /></a></li>	
			</security:authorize>

			<security:authorize access="isAuthenticated()">
				<li><a href="satellite/list.do"><spring:message
							code="master.page.satellites" /></a></li>
				<li><a href="platform/list.do"><spring:message
							code="master.page.platforms" /></a></li>
				<li><a href="tutorial/list.do"><spring:message
							code="master.page.tutorials" /></a></li>
				<li><a href="handyworker/list.do"><spring:message
							code="master.page.handyworkers" /></a></li>
			</security:authorize>
		</ul>
	</div>
	<div>
		<a href="/Acme-Antenna"><img class="logo" src="images/logo.png"
			alt="Acme-Antenna Co., Inc." /></a>
	</div>
</nav>

<div>
	<a class="language" href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

