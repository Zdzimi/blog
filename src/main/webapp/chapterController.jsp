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
    <link rel = "stylesheet" href = "<c:url value="chapterController.css" />" type = "text/css"/>
</head>

<body>
    <div id = "container">
		<div id = "logo">
			<p>Hello World</p>
		</div>

		<div id ="chapters">
		    <c:forEach var="navigation" items="${nav}" >
                <a href="/${navigation}" class="linkTwo" ><div class="optionsL">${navigation}</div></a>
		    </c:forEach>
		</div>

		<div id ="content">

		    <h2>Your chapters entities:</h2>
            <div class ="dottedline"></div>

            <div class="chId">Id</div>
            <div class="title">Title</div>
            <div class="articles">Articles</div>
            <div class ="line"></div>

		    <c:forEach var="ent" items="${chapters}" >
                <div class="chId">${ent.chapterId}</div>
                <div class="title">

                    <c:url var="byChapter" value="/articlesByChapter" >
                        <c:param name="chapterTitle" value="${ent.chapterTitle}" />
                    </c:url>
                    <a href="${byChapter}" class="link">${ent.chapterTitle}</a>

                </div>
                <div class="articles">
                    <c:forEach var="article" items="${ent.articles}">

                        <c:url var="byArticle" value="/paragraphsByArticle" >
                            <c:param name="articleTitle" value="${article.articleTitle}" />
                        </c:url>
                        <a href="${byArticle}" class="link">${article.articleTitle}</a></br>

                    </c:forEach>
                </div>
                <div class = "line"></div>
		    </c:forEach>

		    <h2>Delete chapter entity:</h2>

            <form action="deleteChapter">
                <label for="chapterEntity">Choose entity to delete</label>
            	<select id="chapterEntity" name="chapterTit">

            	<c:forEach var="entity" items="${chapters}">
            	    <option value="${entity.chapterTitle}">${entity.chapterTitle}</option>
            	</c:forEach>

            	</select>
            	<input type="submit" value="delete">
            </form>

            <div class = "dottedline"></div>
            <h2>Create new or update chapter entity:</h2>

            <div id="save">

                <form action="saveChapterEntity">
            	    <div><label>Id: <input type="number" name="chapterId"></label>
            		<label>Title: <input type="text" name="chapterTitle"></label></div>

            		<div><input type="submit" value="save"></div>
            	</form>

            </div>

		</div>

	</div>
</body>
</html>