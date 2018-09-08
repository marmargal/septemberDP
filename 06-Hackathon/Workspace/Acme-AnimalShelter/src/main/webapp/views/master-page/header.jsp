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
	<ul class="nav">
		<!-- Do not forget the "fNiv" class for the first level links !! -->

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
				<a href="#" class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<security:authorize access="hasRole('ADMIN')">
						<li><a href="configuration/administrator/edit.do"><spring:message code="master.page.editConfiguration" /></a></li>
						<li><a href="dashboard/list.do"><spring:message code="master.page.dashboard" /></a></li>
					</security:authorize>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
			<li><a href="#" class="fNiv"><spring:message	code="master.page.pets" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="pet/petsWaitingAdoption.do"><spring:message code="master.page.petsWaitingAdoption" /></a></li>
					<li><a href="pet/petsPermitAdoption.do"><spring:message code="master.page.petsPermitAdoption" /></a></li>
				</ul>
			</li>
			<li><a href="center/list.do"><spring:message code="master.page.centers" /></a></li>	
			<li><a href="event/list.do"><spring:message code="master.page.event.list" /></a></li>
			<li><a href="company/list.do"><spring:message code="master.page.companies" /></a></li>	
			<li><a href="stand/list.do"><spring:message code="master.page.stands" /></a></li>
		</security:authorize>
		
				<security:authorize access="hasRole('ADMIN')">
			
			<li><a href="administrator/edit.do"><spring:message code="master.page.editProfile" /></a></li>
			<li><a href="" class="fNiv"><spring:message	code="master.page.actors" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/listEmployees.do"><spring:message code="master.page.listEmployees" /></a></li>
					<li><a href="administrator/listClients.do"><spring:message code="master.page.listClients" /></a></li>
					<li><a href="administrator/listVoluntaries.do"><spring:message code="master.page.listVoluntaries" /></a></li>
					<li><a href="administrator/listVeterinaries.do"><spring:message code="master.page.listVeterinaries" /></a></li>
					<li><a href="administrator/listBoss.do"><spring:message code="master.page.listBoss" /></a></li>
				</ul>
			</li>
			<li><a href="" class="fNiv"><spring:message	code="master.page.applications" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="application/administrator/listPending.do"><spring:message code="master.page.listPending" /></a></li>
					<li><a href="application/administrator/listClientBan.do"><spring:message code="master.page.listClientBan" /></a></li>
				</ul>
			</li>
			<li><a href="notice/administrator/list.do"><spring:message code="master.page.notices" /></a></li>
			<li><a href="medicalReport/administrator/list.do"><spring:message code="master.page.medicalReport" /></a></li>
			<li><a href="pet/administrator/list.do"><spring:message code="master.page.allPets" /></a></li>
			<li><a href="" class="fNiv"><spring:message	code="master.page.messages" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="message/administrator/list.do"><spring:message code="master.page.allMessage" /></a></li>
					<li><a href="message/administrator/listDeleted.do"><spring:message code="master.page.messagesInTrash" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		
		<security:authorize access="hasRole('CLIENT')">
			<li><a href="client/edit.do"><spring:message code="master.page.editProfile" /></a></li>
			<li><a href="application/client/list.do"><spring:message code="master.page.applicationListAcept" /></a></li>
			
			
		</security:authorize>
		
		<security:authorize access="hasRole('VOLUNTARY')">
			<li><a href="voluntary/edit.do"><spring:message code="master.page.editProfile" /></a></li>
			<li><a href="notice/voluntary/create.do"><spring:message code="master.page.notice.create" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('BOSS')">
			<li><a href="boss/edit.do"><spring:message code="master.page.editProfile" /></a></li>
			<li><a href="employee/boss/list.do"><spring:message code="master.page.employeesByCentersBoss" /></a></li>
			<li><a class="fNiv"><spring:message	code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="employee/boss/register.do"><spring:message code="master.page.register.employee" /></a></li>
					<li><a href="veterinary/boss/register.do"><spring:message code="master.page.register.veterinary" /></a></li>
					<li><a href="boss/boss/register.do"><spring:message code="master.page.register.boss" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('EMPLOYEE')">
			<li><a href="employee/edit.do"><spring:message code="master.page.editProfile" /></a></li>
			<li><a href="pet/employee/list.do"><spring:message code="master.page.allPets" /></a></li>
			<li><a href="application/employee/list.do"><spring:message code="master.page.applicationsPending" /></a></li>
			
			<li><a href="voluntary/employee/listByStand.do"><spring:message code="master.page.voluntariesByStand" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('VETERINARY')">
			<li><a href="veterinary/edit.do"><spring:message code="master.page.editProfile" /></a></li>
			<li><a href="pet/veterinary/list.do"><spring:message code="master.page.allPets" /></a></li>
		</security:authorize>
	</ul>
</div>

<div class="languaje">
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

