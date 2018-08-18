<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>





<jstl:if test="${!(dataRoutesPerUser[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataRoutesPerUser" /></b> <br />
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataRoutesPerUser[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataRoutesPerUser[0][1]}" />
				<br />
			</h4>

		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>

<jstl:if test="${!(dataHikesPerRoute[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataHikesPerRoute" /></b> <br />
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataHikesPerRoute[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataHikesPerRoute[0][1]}" />
				<br />
			</h4>

		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>



<jstl:if test="${!(dataLengthOfRoutes[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataLengthOfRoutes" /></b> <br />
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataLengthOfRoutes[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataLengthOfRoutes[0][1]}" />
				<br />
			</h4>

		</div>
	</fieldset>
</jstl:if>

<br />
<br />
<%----%>
<jstl:if test="${!(dataLengthOfHikes[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataLengthOfHikes" /></b> <br />
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataLengthOfHikes[0][0]}" />

				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataLengthOfHikes[0][1]}" />
				<br />
			</h4>

		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>
<jstl:if test="${!(dataNumChirpsPerUser[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataNumChirpsPerUser" /></b> <br />
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataNumChirpsPerUser }" />
				<br />
			</h4>

		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>


<%----%>
<fieldset>

	<div>
		<b><spring:message code="dashboard.dataUserMore75Chirps" /></b> <br />
		<h4>

			<jstl:forEach items="${dataUserMore75Chirps }" var="d">
				<jstl:out value="${d.name}" />
				<br />
			</jstl:forEach>
		</h4>
	</div>
</fieldset>
<br />
<%----%>
<fieldset>

	<div>
		<b><spring:message code="dashboard.dataUserLess25Chirps" /></b> <br />
		<h4>

			<jstl:forEach items="${dataUserLess25Chirps }" var="p">
				<jstl:out value="${p.name }" />
				<br />
			</jstl:forEach>
		</h4>
	</div>
</fieldset>
<br />
<%----%>





<jstl:if test="${!(dataCommentPerRoute[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataCommentPerRoute" /></b> <br />
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataCommentPerRoute[0] }" />
				<br />
			</h4>


		</div>
	</fieldset>
</jstl:if>


<jstl:if test="${!(dataInnsPerInkeeper[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataInnsPerInkeeper" /></b> <br />
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataInnsPerInkeeper[0][0] }" />
				<br />
			</h4>


		</div>
	</fieldset>
</jstl:if>

<jstl:if test="${!(dataNumUserPerDayInns[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataNumUserPerDayInns" /></b> <br />

			<jstl:forEach items="${dataNumUserPerDayInns }" var="i">
				<h4>
					<jstl:out value=" AVG: " />
					<jstl:out value="${i[0] }" />
					<br />
				</h4>
				<h4>
					<jstl:out value=" STDDEV: " />
					<jstl:out value="${i[1] }" />
					<br />
				</h4>
			</jstl:forEach>

		</div>

	</fieldset>
</jstl:if>

<br />

