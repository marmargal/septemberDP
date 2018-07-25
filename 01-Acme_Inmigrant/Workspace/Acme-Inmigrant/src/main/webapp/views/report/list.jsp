<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table pagesize="6" class="displaycomment" keepStatus="true"
	name="report" requestURI="${requestURI }" id="row">
	
	<display:column>
			<a href="report/display.do?reportId=${row.id}"><spring:message code="report.display"/></a>
	</display:column>

	<spring:message code="report.immigrant" var="immigrantHeader" />
	<display:column property="immigrant.name" title="${immigrantHeader}" sortable="true" />

	<spring:message code="report.text" var="textHeader" />
	<display:column property="text" title="${textHeader}" sortable="true" />
	
</display:table>

