<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

        <div id = "content">

			<span class = "bigtitle">${content.top}${articleList[0].chapter.chapterTitle}</span>
            <div class = "dottedline"></div>
			${content.tContent}

			<c:forEach var="article" items="${articleList}">
                <c:url var="linkArticle" value="/article" >
                    <c:param name="title" value="${article.articleTitle}" />
                </c:url>
                <a href="${linkArticle}" class="linkTwo">
                    <div class="article">
                        <h2>${article.articleTitle}</h2>
                        ${article.description}
                    </div>
                </a>
			</c:forEach>

		</div>