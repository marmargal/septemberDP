<%--
 * listTaboo.jsp
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="comment" requestURI="${requestURI}" id="row">

	<!-- Attributes -->

	<security:authorize access="hasRole('ADMIN')">

		<display:column>
			<acme:links url="comment/administrator/edit.do?commentId=${row.id}"
				code="comment.edit" />
		</display:column>

		<acme:column property="title" code="comment.title" />
		<acme:column property="moment" code="comment.moment" />
		<acme:column property="text" code="comment.text" />
		<acme:column code="comment.rating" property="rating" />
		<acme:column property="taboo" code="comment.taboo" />


	</security:authorize>

</display:table>