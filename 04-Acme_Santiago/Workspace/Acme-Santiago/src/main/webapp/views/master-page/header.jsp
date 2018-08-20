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
			<security:authorize access="hasRole('ADMIN')">
				<li><a class="fNiv"><spring:message
							code="master.page.administrator" /></a>
					<ul>
						<li class="arrow"></li>
						<li><a href="j_spring_security_logout"><spring:message
									code="master.page.logout" /> </a></li>
						<li><a href="configuration/administrator/edit.do"><spring:message
									code="master.page.edit.configuration" /></a></li>
					</ul></li>
				<li><a href="chirp/administrator/list.do"><spring:message
							code="master.page.chirp.taboo" /></a>
				<li><a href="chirp/administrator/listAll.do"><spring:message
							code="master.page.list.chirp" /></a>
				<li><a href="administrator/list.do"><spring:message
							code="master.page.dashboard" /></a>
				<li><a href="comment/administrator/list.do"><spring:message
							code="master.page.comment.taboo" /></a>
				<li><a href="comment/administrator/listAll.do"><spring:message
							code="master.page.comment" /></a>
			</security:authorize>

			<security:authorize access="hasRole('USER')">
				<li><a href="#" class="fNiv"><spring:message code="master.page.user" /></a>
					<ul>
						<li class="arrow"></li>
						<li><a href="j_spring_security_logout"><spring:message
									code="master.page.logout" /> </a></li>
					</ul></li>
				<li><a href="hike/user/list.do"><spring:message
							code="master.page.hike.list" /></a></li>
				<li><a href="chirp/user/display.do"><spring:message
							code="master.page.chirp" /></a>
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
				<li><a href="route/list.do"><spring:message
							code="master.page.route.search" /></a></li>
				<li><a href="user/list.do"><spring:message
							code="master.page.user.list" /></a></li>
			</security:authorize>

		</ul>
	</div>

	<div>
		<a href="/Acme-Santiago"><img src="images/logo.png"
			alt="Acme-Santiago Co., Inc." /></a>
	</div>
</nav>

<div>
	<a class="language" href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

