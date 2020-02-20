<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset = "utf-8" />
    <title>My blog</title>
    <meta name = "description" content = "My blog application" />
    <meta name = "keywords" content = "blog, blog blog" />
    <meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
    <link rel = "stylesheet" href = "<c:url value="articleController.css" />" type = "text/css"/>
</head>

<body>
    <div id = "container">
		<div id = "logo">
			<p>Hello <security:authentication property="principal.username" /></p>
		</div>

		<div id ="chapters">
		    <c:forEach var="navigation" items="${nav}" >
                <a href="/${navigation}" class="linkTwo" ><div class="optionsL">${navigation}</div></a>
		    </c:forEach>
		        <a href="/logout" class="linkTwo" ><div class="optionsL">logout</div></a>
		</div>

		<div id ="content">

		    <h2>Your article entities:</h2>
            <div class ="dottedline"></div>

            <div class="aId">Id</div>
            <div class="title">Title</div>
            <div class="desc">Description</div>
            <div class="chapter">Chapter</div>
            <div class="paragraphs">Paragraphs</div>
            <div class="comments">Comment</div>
            <div class ="line"></div>

		    <c:forEach var="ent" items="${articles}" >
                <div class="aId">${ent.articleId}</div>
                <div class="title">

                    <c:url var="byArticle" value="/paragraphs-by-article" >
                        <c:param name="articleTitle" value="${ent.articleTitle}" />
                    </c:url>
                    <a href="${byArticle}" class="link">${ent.articleTitle}</a></br>

                </div>
                <div class="desc">${ent.description}</div>
                <div class="chapter">${ent.chapter.chapterTitle}</div>
                <div class="paragraphs">
                    <c:forEach var="paragraph" items="${ent.paragraphs}">
                        ${paragraph.header}</br>
                    </c:forEach>
                </div>
                <div class="comments">

                    <c:url var="commentsByArticle" value="/comments-by-article" >
                        <c:param name="articleTitle" value="${ent.articleTitle}" />
                    </c:url>
                    <a href="${commentsByArticle}" class="link">#</a>

                </div>
                <div class = "line"></div>
		    </c:forEach>

		    <h2>Delete article entity:</h2>

            <form action="delete-article">
                <label for="articleEntity">Choose entity to delete</label>
            	<select id="articleEntity" name="articleTitle">

            	<c:forEach var="entity" items="${articles}">
            	    <option value="${entity.articleTitle}">${entity.articleTitle}</option>
            	</c:forEach>

            	</select>
            	<input type="submit" value="delete">
            </form>

            <div class = "dottedline"></div>
            <h2>Create new or update article entity:</h2>

            <div id="save">

                <form action="save-article">
            	    <div><label>Id: <input type="number" name="articleId"></label>
            		<label>Title: <input type="text" name="articleTitle"></label></div>

            		<div><label for="description">Description:</label></div>
                    <textarea name="description" id="description" rows="4" cols="80"></textarea>

                        <label for="chapterChooser">Choose chapter:</label>
                        <select id="chapterChooser" name="chapterTitle">

                        <c:forEach var="entity" items="${chapters}">
                            <option value="${entity.chapterTitle}">${entity.chapterTitle}</option>
                        </c:forEach>

                        </select>

            		<div><input type="submit" value="save"></div>
            	</form>

            </div>

		</div>

	</div>
</body>
</html>