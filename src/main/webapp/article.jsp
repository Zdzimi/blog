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

		    <span class = "bigtitle">${paragraphList[0].article.articleTitle}</span>
            <div class = "dottedline"></div>

			<c:forEach var="paragraph" items="${paragraphList}">
                <div class="paragraphList">
                    <h2>${paragraph.header}</h2>
                    ${paragraph.content}
                    <div>${paragraph.photo.photoView}</div>
                    <div>${paragraph.photo.photoDescription}</div>
                </div>
            </c:forEach>

            <div id="addComment">

                <form action="add-comment" >

                    <div><label for="comment">Write comment:</label></div>
                    <textarea name="commentContent" id="comment" rows="4" cols="80"></textarea>

                    <input type="hidden" name="articleTitle" value="${paragraphList[0].article.articleTitle}">

                    <div><label>Name: <input type="text" name="username"></label>
                    <input type="submit" value="post comment"></div>
                </form>
            </div>

            <c:forEach var="comment" items="${commentList}">
                <div class="commentList">
                    <div class="comment">
                        <h5>${comment.username}</h5>
                        ${comment.commentContent}
                    </div>
                    <div class="answer">
                        <h5>${comment.admin}</h5>
                        ${comment.answer}
                    </div>
                </div>
            </c:forEach>

		</div>

		<div id = "footer">
			Some footer.................. ;-)
		</div>

	</div>
</body>
</html>