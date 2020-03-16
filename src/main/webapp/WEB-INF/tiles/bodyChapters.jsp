<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

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

                    <c:url var="byChapter" value="/articles-by-chapter" >
                        <c:param name="chapterTitle" value="${ent.chapterTitle}" />
                    </c:url>
                    <a href="${byChapter}" class="link">${ent.chapterTitle}</a>

                </div>
                <div class="articles">
                    <c:forEach var="article" items="${ent.articles}">

                        <c:url var="byArticle" value="/paragraphs-by-article" >
                            <c:param name="articleTitle" value="${article.articleTitle}" />
                        </c:url>
                        <a href="${byArticle}" class="link">${article.articleTitle}</a></br>

                    </c:forEach>
                </div>
                <div class = "line"></div>
		    </c:forEach>

		    <h2>Delete chapter entity:</h2>

            <form action="delete-chapter" method="post">
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

                <form action="save-chapter" method="post">
            	    <div><label>Id: <input type="number" name="chapterId"></label>
            		<label>Title: <input type="text" name="chapterTitle"></label></div>

            		<div><input type="submit" value="save"></div>
            	</form>

            </div>

		</div>