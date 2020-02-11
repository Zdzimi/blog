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
    <link rel = "stylesheet" href = "<c:url value="admin.css" />" type = "text/css"/>
</head>

<body>
    <div id = "container">
		<div id = "logo">
			<p>Hello World</p>
		</div>

		<div id = "chapters">
		    <c:forEach var="navigation" items="${nav}">
                <a href="/${navigation}" class="linkTwo"><div class="optionsL">${navigation}</div></a>
		    </c:forEach>
		</div>

		<div id = "content"></div>

	</div>
</body>
</html>