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
    <link rel = "stylesheet" href = "<c:url value="commentController.css" />" type = "text/css"/>
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

		    <h2>Your comment entities:</h2>
            <div class ="dottedline"></div>

            <div class="commentId">Id</div>
            <div class="username">Username</div>
            <div class="commentContent">Comment</div>
            <div class="article">Article</div>
            <div class="answer">Answer</div>
            <div class ="line"></div>

		    <c:forEach var="ent" items="${comments}" >
                <div class="commentId">${ent.commentId}</div>
                <div class="username">${ent.username}</div>
                <div class="commentContent">${ent.commentContent}</div>
                <div class="article">${ent.article.articleTitle}</div>
                <div class="answer">${ent.answer}</div>
                <div class = "line"></div>
		    </c:forEach>

		    <h2>Delete comment entity:</h2>

            <form action="delete-comment">
                <label for="commentEntity">Choose entity to delete</label>
            	<select id="commentEntity" name="commentId">

            	<c:forEach var="entity" items="${comments}">
            	    <option value="${entity.commentId}">${entity.commentId}</option>
            	</c:forEach>

            	</select>
            	<input type="submit" value="delete">
            </form>

            <div class = "dottedline"></div>
            <h2>Write answer to comment:</h2>

            <div id="save">

                <form action="answer">
            	    <label for="commentEntity">Choose entity: </label>
                    <select id="commentEntity" name="commentId">

                        <c:forEach var="entity" items="${comments}">
                            <option value="${entity.commentId}">${entity.commentId}</option>
                        </c:forEach>

                    </select>

                    <label>Name: <input type="text" name="admin"></label>

            		<div><label for="myAnswer">Content:</label></div>
                    <textarea name="answer" id="myAnswer" rows="4" cols="80"></textarea>

            		<div><input type="submit" value="save"></div>
            	</form>

            </div>

		</div>

	</div>
</body>
</html>