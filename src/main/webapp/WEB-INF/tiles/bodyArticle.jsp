<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

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

                <form action="add-comment" method="post">

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