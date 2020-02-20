<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

        <div id = "logo">
            <security:authorize access="isAuthenticated()">
                <p>Hello <security:authentication property="principal.username" /></p>
            </security:authorize>
		</div>

		<div id ="topMenu">
			<c:forEach var="topbarEntity" items="${topMenu}">

			    <c:url var="linkTop" value="/hello" >
                    <c:param name="top" value="${topbarEntity}" />
                </c:url>
                <a href="${linkTop}" class="linkTop"><div class ="topbar">${topbarEntity}</div></a>

			</c:forEach>
			    <security:authorize access="isAuthenticated()">
                    <a href="/logout" class="linkTop"><div class ="topbar">logout</div></a>
                </security:authorize>

                <security:authorize access="!isAuthenticated()">
                    <a href="/login" class="linkTop"><div class ="topbar">login</div></a>
                </security:authorize>

			<div style = "clear:both;"></div>
		</div>

		<a href="/registry" class="linkTop"><div id="ad">Subscribe</div></a>

		<div id = "chapters">
		    <c:forEach var="chapterName" items="${chapters}">

		        <c:url var="linkChap" value="/chapter" >
                    <c:param name="title" value="${chapterName}" />
                </c:url>
                <a href="${linkChap}" class="linkTwo"><div class="optionsL">${chapterName}</div></a>

		    </c:forEach>
		</div>