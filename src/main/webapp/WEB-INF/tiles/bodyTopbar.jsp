<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

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

		    <form action="delete-topbar" method="post">
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

		        <form action="save-top" method="post">
		            <div><label>Id: <input type="number" name="tId"></label>
		            <label>Top: <input type="text" name="top"></label></div>

		            <div><label for="comment">Content:</label></div>
                    <textarea name="tContent" id="comment" rows="4" cols="80"></textarea>

		            <div><input type="submit" value="save"></div>
		        </form>

		    </div>

		</div>