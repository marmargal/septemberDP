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

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="notices" requestURI="${requestURI }" id="row">

	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<form name="submitForm" method="POST" action="notice/administrator/delete.do?noticeId=${row.id }">
		    	<acme:submit name="delete" code="notice.delete"/>
			</form>
		</display:column>
	</security:authorize>

	<display:column>
		<acme:links url="notice/administrator/display.do?noticeId=${row.id }" code="notice.display"/>
	</display:column>
	<acme:column property="type" code="notice.type" />
	<acme:column property="level" code="notice.level" />
	<acme:column property="date" code="notice.date" />
	
</display:table>