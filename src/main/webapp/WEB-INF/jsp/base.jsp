<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset = "utf-8" />
    <meta name = "description" content = "My blog application" />
    <meta name = "keywords" content = "blog, blog blog" />
    <link rel = "stylesheet" href = <tiles:insertAttribute name="css" /> type = "text/css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><tiles:insertAttribute name="title" /></title>
</head>
<body>
    <div id = "container">

        <tiles:insertAttribute name="nav" />
        <tiles:insertAttribute name="body" />
        <tiles:insertAttribute name="footer" />

    </div>
</body>
</html>