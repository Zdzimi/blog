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
    <link rel = "stylesheet" href = "<c:url value="login.css" />" type = "text/css"/>
</head>

<body>
    <div id = "container">
        <div id="log">
            <p>${exception}</p>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <p><a href="/admin">admin</a></p>
            </sec:authorize>
            <p><a href="/">home</a></p>
        </div>
    </div>
</body>
</html>