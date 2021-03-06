<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

        <div id ="content">

		    <h2>Your photos:</h2>
            <div class ="dottedline"></div>

		    <c:forEach var="ent" items="${photos}" >

                <div>Photo_Id: ${ent.photoId}</div>
		        ${ent.photoView}
                <div>Description: ${ent.photoDescription}</div>
                <div>Paragraphs:
                <c:forEach var="paragraph" items="${ent.paragraphs}">
                    ${paragraph.header}
                </c:forEach>
                </div>

                <div class = "line"></div>
		    </c:forEach>

		    <h2>Delete photo:</h2>

            <form action="delete-photo" method="post">
                <label for="photoEntity">Choose photo to delete</label>
            	<select id="photoEntity" name="photoId">

            	    <option value="0"></option>
            	<c:forEach var="entity" items="${photos}">
            	    <option value="${entity.photoId}">${entity.photoId}</option>
            	</c:forEach>

            	</select>
            	<input type="submit" value="delete">
            </form>

            <div class = "dottedline"></div>
            <h2>Add photo:</h2>

            <div id="save">

                <form action="add-photo" method="post">

                    <div><label for="photoPath">Path to file:</label></div>
                    <textarea name="photoPath" id="photoPath" rows="1" cols="80"></textarea>

                    <div><label>Photo height: <input type="number" name="photoHeight"></label></div>

                    <div><label>Photo width: <input type="number" name="photoWidth"></label></div>

                    <div><label for="pDescription">Description:</label></div>
                    <textarea name="photoDescription" id="pDescription" rows="4" cols="80"></textarea>

            		<div><input type="submit" value="save"></div>
            	</form>

            </div>

		</div>