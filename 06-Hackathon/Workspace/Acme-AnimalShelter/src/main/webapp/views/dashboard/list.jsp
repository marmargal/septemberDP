<%-- list.jsp --%>

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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<jstl:if test="${!(dataApplicationPerClient[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataApplicationPerClient" /></b>
			<br />
			<h4>
				<jstl:out value=" MIN: " />
				<jstl:out value="${dataApplicationPerClient[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" MAX: " />
				<jstl:out value="${dataApplicationPerClient[0][1]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataApplicationPerClient[0][2]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataApplicationPerClient[0][3]}" />
				<br />
			</h4>
		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>

<jstl:if test="${!(dataNoticePerVoluntary[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataNoticePerVoluntary" /></b>
			<br />
			<h4>
				<jstl:out value=" MIN: " />
				<jstl:out value="${dataNoticePerVoluntary[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" MAX: " />
				<jstl:out value="${dataNoticePerVoluntary[0][1]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataNoticePerVoluntary[0][2]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataNoticePerVoluntary[0][3]}" />
				<br />
			</h4>
		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>

<jstl:if test="${!(dataApplicationPerEmployee[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataApplicationPerEmployee" /></b>
			<br />
			<h4>
				<jstl:out value=" MIN: " />
				<jstl:out value="${dataApplicationPerEmployee[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" MAX: " />
				<jstl:out value="${dataApplicationPerEmployee[0][1]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataApplicationPerEmployee[0][2]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataApplicationPerEmployee[0][3]}" />
				<br />
			</h4>
		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>

<jstl:if test="${!(dataReportPorEmpleado[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataReportPorEmpleado" /></b>
			<br />
			<h4>
				<jstl:out value=" MIN: " />
				<jstl:out value="${dataReportPorEmpleado[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" MAX: " />
				<jstl:out value="${dataReportPorEmpleado[0][1]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataReportPorEmpleado[0][2]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataReportPorEmpleado[0][3]}" />
				<br />
			</h4>
		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>

<jstl:if test="${!(dataMedicalReportPerVeterinary[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataMedicalReportPerVeterinary" /></b>
			<br />
			<h4>
				<jstl:out value=" MIN: " />
				<jstl:out value="${dataMedicalReportPerVeterinary[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" MAX: " />
				<jstl:out value="${dataMedicalReportPerVeterinary[0][1]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataMedicalReportPerVeterinary[0][2]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataMedicalReportPerVeterinary[0][3]}" />
				<br />
			</h4>
		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>

<jstl:if test="${!(dataApplicationPerClientLastWeek[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataApplicationPerClientLastWeek" /></b>
			<br />
			<h4>
				<jstl:out value=" MIN: " />
				<jstl:out value="${dataApplicationPerClientLastWeek[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" MAX: " />
				<jstl:out value="${dataApplicationPerClientLastWeek[0][1]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataApplicationPerClientLastWeek[0][2]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataApplicationPerClientLastWeek[0][3]}" />
				<br />
			</h4>
		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>

<jstl:if test="${!(dataMedicalReportPerVeterinaryLastWeek[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataMedicalReportPerVeterinaryLastWeek" /></b>
			<br />
			<h4>
				<jstl:out value=" MIN: " />
				<jstl:out value="${dataMedicalReportPerVeterinaryLastWeek[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" MAX: " />
				<jstl:out value="${dataMedicalReportPerVeterinaryLastWeek[0][1]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataMedicalReportPerVeterinaryLastWeek[0][2]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataApplicationPerImmigrant[0][3]}" />
				<br />
			</h4>
		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>

<jstl:if test="${!(dataReportPerEmployeeLastWeek[0] eq null)}">
	<fieldset>
		<div>
			<b><spring:message code="dashboard.dataReportPerEmployeeLastWeek" /></b>
			<br />
			<h4>
				<jstl:out value=" MIN: " />
				<jstl:out value="${dataReportPerEmployeeLastWeek[0][0]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" MAX: " />
				<jstl:out value="${dataReportPerEmployeeLastWeek[0][1]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" AVG: " />
				<jstl:out value="${dataReportPerEmployeeLastWeek[0][2]}" />
				<br />
			</h4>
			<h4>
				<jstl:out value=" STDDEV: " />
				<jstl:out value="${dataReportPerEmployeeLastWeek[0][3]}" />
				<br />
			</h4>
		</div>
	</fieldset>
</jstl:if>

<br />
<%----%>

<fieldset>

	<div>
		<b><spring:message code="dashboard.clientsWithMoreApplications" /></b> <br />
		<h4>

			<jstl:forEach items="${clientsWithMoreApplications }" var="d">
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
		<b><spring:message code="dashboard.veterinariesWithMoreReports" /></b> <br />
		<h4>

			<jstl:forEach items="${veterinariesWithMoreMedicalReport }" var="p">
				<jstl:out value="${p.name }" />
				<br />
			</jstl:forEach>
		</h4>
	</div>
</fieldset>
<br />
<%----%>
<fieldset>

	<div>
		<b><spring:message code="dashboard.employeesWithMoreReports" /></b> <br />
		<h4>

			<jstl:forEach items="${employeesWithMoreReports }" var="d">
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
		<b><spring:message code="dashboard.employeesWithLessReports" /></b> <br />
		<h4>

			<jstl:forEach items="${employeesWithLessReports }" var="p">
				<jstl:out value="${p.name }" />
				<br />
			</jstl:forEach>
		</h4>
	</div>
</fieldset>
<br />
<%----%>

<fieldset>

	<div>
		<b><spring:message code="dashboard.dataUserMore75Chirps" /></b> <br />
		<h4>

			<jstl:forEach items="${dataVeterinaryMore50MedicalReport }" var="p">
				<jstl:out value="${p.name }" />
				<br />
			</jstl:forEach>
		</h4>
	</div>
</fieldset>
<br />
<%----%>