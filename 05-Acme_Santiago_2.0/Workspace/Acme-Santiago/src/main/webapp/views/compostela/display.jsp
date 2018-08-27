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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('USER')">
	<center>
		<img class="imagen" src="images/logo.jpg" />
		<h1>
			<jstl:out value="${user.name }" />
		</h1>
		<h5>
			<jstl:out value="${compostela.date }" />
		</h5>

		<hr />
		<h2>
			<spring:message code="compostela.user.details" />
		</h2>

		<p><b><spring:message code="compostela.user.name" /></b>: <jstl:out value="${user.name }" /></p>
		<p><b><spring:message code="compostela.user.surname" /></b>: <jstl:out value="${user.surname }" /></p>
		<p><b><spring:message code="compostela.user.email" /></b>: <jstl:out value="${user.email }" /></p>
		<p><b><spring:message code="compostela.user.phoneNumber" /></b>: <jstl:out value="${user.phoneNumber }" /></p>
		<p><b><spring:message code="compostela.user.postalAddress" /></b>: <jstl:out value="${user.postalAddress }" /></p>

		<jstl:forEach var="p" items="${user.pictures }">
			<img class="imagen" src="${p }" />
		</jstl:forEach>
		
		<h2>
			<spring:message code="compostela.walk.details" />
		</h2>
		
		<p><jstl:out value="${compostela.walk.title }" /></p>
		<%!int count = 0;%>
			<jstl:forEach var="d" items="${compostela.walk.daysOfEachHike }">
				<%
					count = count + 1;
				%>
				<spring:message code="compostela.number.hike" var="numberHike" />
				<b><jstl:out value="${numberHike }"/> <%=count%>: </b>
				<jstl:out value="${d }"/>
				<br/>
			</jstl:forEach>
		<%
			count = 0;
		%>
		<h1><jstl:out value="${compostela.walk.inn.name}"/></h1>
		<h2>
			<spring:message code="compostela.inn.badges" />
		</h2>
		<img class="imagen" src="${compostela.walk.inn.badge}" />

		<hr />

		<p><spring:message code="compostela.footer.information" /></p>
	</center>
</security:authorize>