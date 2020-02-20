<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

        <div id ="logo">
			<p>Hello <security:authentication property="principal.username" /></p>
		</div>

		<div id ="chapters">
		    <c:forEach var="navigation" items="${nav}" >
                <a href="/${navigation}" class="linkTwo" ><div class="optionsL">${navigation}</div></a>
		    </c:forEach>
                <a href="/logout" class="linkTwo" ><div class="optionsL">logout</div></a>
		</div>