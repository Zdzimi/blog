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
    <link rel = "stylesheet" href = "<c:url value="paragraphController.css" />" type = "text/css"/>
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

		    <h2>Your paragraph entities:</h2>
            <div class ="dottedline"></div>

            <div class="pId">Id</div>
            <div class="header">Header</div>
            <div class="content">Content</div>
            <div class="article">Article</div>
            <div class="photo">Photo</div>
            <div class ="line"></div>

		    <c:forEach var="ent" items="${paragraphs}" >
                <div class="pId">${ent.pId}</div>
                <div class="header">${ent.header}</div>
                <div class="content">${ent.content}</div>
                <div class="article">${ent.article.articleTitle}</div>
                <div class="photo">${ent.photo.photoId}</div>
                <div class = "line"></div>
		    </c:forEach>

		    <h2>Delete paragraph entity:</h2>

            <form action="deleteParagraph">
                <label for="paragraphEntity">Choose entity to delete</label>
            	<select id="paragraphEntity" name="pId">

            	<c:forEach var="entity" items="${paragraphs}">
            	    <option value="${entity.pId}">${entity.pId}</option>
            	</c:forEach>

            	</select>
            	<input type="submit" value="delete">
            </form>

            <div class = "dottedline"></div>
            <h2>Create new or update paragraph entity:</h2>

            <div id="save">

                <form action="saveParagraphEntity">
            	    <div><label>Id: <input type="number" name="pId"></label>
            		<label>Title: <input type="text" name="header"></label></div>

            		<div><label for="cont">Content:</label></div>
                    <textarea name="content" id="cont" rows="4" cols="80"></textarea>

                    <div>
                        <label for="articleChooser">Choose article:</label>
                        <select id="articleChooser" name="articleTitle">

                        <c:forEach var="entity" items="${articles}">
                            <option value="${entity.articleTitle}">${entity.articleTitle}</option>
                        </c:forEach>
                        </select>
                    </div>

                    <div>
                        <label for="photoChooser">Choose photo:</label>
                        <select id="photoChooser" name="photoId">

                            <option value="0"></option>
                        <c:forEach var="entity" items="${photos}">
                            <option value="${entity.photoId}">${entity.photoId}</option>
                        </c:forEach>
                        </select>
                    </div>

            		<div><input type="submit" value="save"></div>
            	</form>

            </div>

		</div>

	</div>
</body>
</html>