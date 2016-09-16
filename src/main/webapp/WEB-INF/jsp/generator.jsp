<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>


<html lang="en">
<head>
    <title>Project Page</title>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="<c:url value="/static/js/jsMethods.js" />"></script>
    <%--<script type="text/javascript" src="../js/jsMethods.js" ></script>--%>
</head>
<body onload = "getClassesList()">
<table class="tg">
    <tr>
        <th>Project</th>
        <th>Class</th>
    </tr>
    <tr>
        <th><select id="projectSelector"  name='projectSelector' onchange="getClassesList()">
            <c:forEach items="${allprojects}" var="project">
                <option value="${project.id}">${project.name} </option>
            </c:forEach>
        </select>
        </th>
        <th>
            <select id="classes" name='classes'> </select>
        </th>
    </tr>

</table>

<button onclick="onClickGenerateJSON()">Generate JSON</button>
<button onclick="onClickGetMethods()">Get methods</button>

<br>
<br>
<form>
    <button type="submit" formaction="/">Home</button>
    <button type="submit" formaction="/project/getall/">Projects</button>
</form>

<br>
<h4>Response:</h4>
<br>
<textarea id="printer" rows="100" cols="100" >

</textarea>
<br>
</body>


</html>

