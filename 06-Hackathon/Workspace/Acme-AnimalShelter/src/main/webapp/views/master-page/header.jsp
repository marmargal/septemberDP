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

<div>
	<a href="/Acme-AnimalShelter"><img src="images/logo.png" alt="Acme-AnimalShelter Co., Inc." /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a></li>
			<li><a href="administrator/edit.do"><spring:message code="master.page.editProfile" /></a></li>
			<li><a class="fNiv"><spring:message	code="master.page.actors" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/listEmployees.do"><spring:message code="master.page.listEmployees" /></a></li>
					<li><a href="administrator/listClients.do"><spring:message code="master.page.listClients" /></a></li>
					<li><a href="administrator/listVoluntaries.do"><spring:message code="master.page.listVoluntaries" /></a></li>
					<li><a href="administrator/listVeterinaries.do"><spring:message code="master.page.listVeterinaries" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.applications" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="application/administrator/listPending.do"><spring:message code="master.page.listPending" /></a></li>
					<li><a href="application/administrator/listClientBan.do"><spring:message code="master.page.listClientBan" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv" href=""><spring:message code="master.page.register" /></a>
				<ul>
					<li><a href="client/register.do"><spring:message code="master.page.register.client" /></a></li>
					<li><a href="voluntary/register.do"><spring:message code="master.page.register.voluntary" /></a></li>
				</ul>
			</li>
			<li><a href="pet/petsWaitingAdoption.do"><spring:message code="master.page.petsWaitingAdoption" /></a></li>
			<li><a href="pet/petsPermitAdoption.do"><spring:message code="master.page.petsPermitAdoption" /></a></li>
			<li><a href="center/list.do"><spring:message code="master.page.centers" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
			<li><a href="pet/petsWaitingAdoption.do"><spring:message code="master.page.petsWaitingAdoption" /></a></li>
			<li><a href="pet/petsPermitAdoption.do"><spring:message code="master.page.petsPermitAdoption" /></a></li>
			<li><a href="center/list.do"><spring:message code="master.page.centers" /></a></li>		
		</security:authorize>
		
		<security:authorize access="hasRole('CLIENT')">
			<li><a href="client/edit.do"><spring:message code="master.page.editProfile" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('VOLUNTARY')">
			<li><a href="voluntary/edit.do"><spring:message code="master.page.editProfile" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('BOSS')">
			<li><a href="boss/edit.do"><spring:message code="master.page.editProfile" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('EMPLOYEE')">
			<li><a href="employee/edit.do"><spring:message code="master.page.editProfile" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('VETERINARY')">
			<li><a href="veterinary/edit.do"><spring:message code="master.page.editProfile" /></a></li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

