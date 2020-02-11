<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
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
            <h1>Registry:</h1>
            <form action="registry" method="POST">
                <div>
                    <div>
                        <td>Username:</td>
                        <td><input type="text" name="username" value="" /></td>
                    </div></br>
                    <div>
                        <td>Password:</td>
                        <td><input type="password" name="password" value="" /></td>
                    </div></br>
                    <div>
                        <td><input name="submit" type="submit" value="submit" /></td>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>