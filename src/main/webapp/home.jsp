<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset = "utf-8" />
    <title>My blog</title>
    <meta name = "description" content = "My blog application" />
    <meta name = "keywords" content = "blog, blog blog" />
    <meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
    <link rel = "stylesheet" href = "<c:url value="style.css" />" type = "text/css"/>
</head>

<body>
    <div id = "container">
		<div id = "logo">
			<p>Hello World</p>
		</div>

		<div id ="topMenu">
			<c:forEach var="topbarEntity" items="${topMenu}">

			    <c:url var="linkTop" value="/hello" >
                    <c:param name="top" value="${topbarEntity}" />
                </c:url>
                <a href="${linkTop}" class="linkTop"><div class ="topbar">${topbarEntity}</div></a>

			</c:forEach>
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

		<div id = "footer">
			Some footer.................. ;-)
		</div>

	</div>
</body>
</html>