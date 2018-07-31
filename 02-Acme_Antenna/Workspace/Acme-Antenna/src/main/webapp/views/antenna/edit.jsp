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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${requestURI }" modelAttribute="antennaForm">
	
	<form:hidden path="id"/>
	
	<acme:textbox code="antenna.serialNumber" path="serialNumber"/>
	<br/>
	
	<acme:textbox code="antenna.model" path="model"/>
	<br/>
	
	<acme:textbox code="antenna.latitude" path="latitude"/>
	<br/>
	
	<acme:textbox code="antenna.longitude" path="longitude"/>
	<br/>
	
	<acme:textbox code="antenna.azimuth" path="azimuth"/>
	<br/>
	
	<acme:textbox code="antenna.elevation" path="elevation"/>
	<br/>
	
	<acme:textbox code="antenna.quality" path="quality"/>
	<br/>
	
	<acme:select items="${satellites }" itemLabel="name" code="antenna.satellite" path="satellite"/>
	
	<acme:submit name="save" code="antenna.save"/>
	<jstl:if test="${antennaForm.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="antenna.delete" />"
				onclick="return confirm('<spring:message code="antenna.confirm.delete" />')" />&nbsp;
	</jstl:if>
	
	<acme:cancel url="antenna/user/list.do" code="antenna.cancel"/>
	
</form:form>
