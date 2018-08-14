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

<div>
	<img src="images/logo.png" alt="Acme-Santiago Co., Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">


		</security:authorize>

		<security:authorize access="hasRole('USER')">

		</security:authorize>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>

			<li><a href="user/register.do"><spring:message
						code="master.page.register.user" /></a></li>
			<li><a href="route/list.do"><spring:message
						code="master.page.route.search" /></a></li>
			<li><a href="user/list.do"><spring:message
						code="master.page.user.list" /></a></li>
		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>

					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>
				</ul></li>
			<li><a href="route/list.do"><spring:message
						code="master.page.route.search" /></a></li>
			<li><a href="user/list.do"><spring:message
						code="master.page.user.list" /></a></li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

