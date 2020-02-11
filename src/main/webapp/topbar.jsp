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
    <link rel = "stylesheet" href = "<c:url value="topbar.css" />" type = "text/css"/>
</head>

<body>
    <div id ="container">
		<div id ="logo">
			<p>Hello World</p>
		</div>

		<div id ="chapters">
		    <c:forEach var="navigation" items="${nav}" >
                <a href="/${navigation}" class="linkTwo" ><div class="optionsL">${navigation}</div></a>
		    </c:forEach>
		</div>

		<div id ="content">

		    <h2>Your top menu entities:</h2>
            <div class ="dottedline"></div>

            <div class="tId">Id</div>
            <div class="title">Title</div>
            <div class="cont">Content</div>
            <div class ="line"></div>

		    <c:forEach var="topbarMenu" items="${top}" >
                <div class="tId">${topbarMenu.tId}</div>
                <div class="title">${topbarMenu.top}</div>
                <div class="cont">${topbarMenu.tContent}</div>
                <div class ="line"></div>
		    </c:forEach>

            <h2>Delete top menu entity:</h2>

		    <form action="deleteTopbar" >
		        <label for="topEntity">Choose entity to delete</label>
		            <select id="topEntity" name="top">

		                <c:forEach var="entity" items="${top}">
		                    <option value="${entity.top}">${entity.top}</option>
		                </c:forEach>

		            </select>
		            <input type="submit" value="delete">
		    </form>

            <div class = "dottedline"></div>
		    <h2>Create new or update top menu entity:</h2>

		    <div id="save">

		        <form action="saveTopEntity">
		            <div><label>Id: <input type="number" name="tId"></label>
		            <label>Top: <input type="text" name="top"></label></div>

		            <div><label for="comment">Content:</label></div>
                    <textarea name="tContent" id="comment" rows="4" cols="80"></textarea>

		            <div><input type="submit" value="save"></div>
		        </form>

		    </div>

		</div>

	</div>
</body>
</html>