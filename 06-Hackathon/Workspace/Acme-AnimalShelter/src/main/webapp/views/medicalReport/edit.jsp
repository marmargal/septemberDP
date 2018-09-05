<%-- list.jsp --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${requestUri}" modelAttribute="medicalReport">
	<form:hidden path="id"/>
	<form:hidden path="date"/>
	<form:hidden path="veterinary"/>
	<form:hidden path="pet"/>
	
	<acme:textarea code="medicalReport.pet.name" path="pet.name" readonly="true"/>
	<br/>
	<acme:textarea code="medicalReport.pet.type" path="pet.type" readonly="true"/>
	<br/>
	<acme:textarea code="medicalReport.pet.identifier" path="pet.identifier" readonly="true"/>
	<br/>
	
	
	<acme:textarea code="medicalReport.diagnosis" path="diagnosis"/>
	<br/>
	<acme:textarea code="medicalReport.initialState" path="initialState"/>
	<br/>
	<acme:textarea code="medicalReport.treatment" path="treatment"/>
	<br/>
	<acme:textarea code="medicalReport.diseases" path="diseases"/>
	<br/>
	
	<acme:submit name="save" code="medicalReport.save"/>
	<acme:cancel url="pet/veterinary/list.do" code="medicalReport.cancel"/> 
</form:form>