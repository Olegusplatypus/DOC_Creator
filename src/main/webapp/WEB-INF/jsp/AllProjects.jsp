<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html lang="en">
<head>
    <title>Project Page</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
    </style>
</head>
<body>

<h3>Projects</h3>
<%--<a href="/"><img src="https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcToQptOdZd_p4IChJScY76fiv8k6-7tTQBofKaWs5jnTBOJjSe5" width="70" height="70" alt="Home"/></a>--%>
<form>
    <button type="submit" formaction="/project/save/">Add Project</button>
    <button type="submit"  formaction="/">Home</button>
    <button type="submit" formaction="/project/generator/">Generator</button>
</form>
<table class="tg">
        <tr>
            <th width="120">Project Name</th>
            <th width="120">Root path</th>
            <th width="120">Jar path</th>
            <th width="120">Domain path</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${allprojects}" var="projectEntity">
            <tr>
                <td>${projectEntity.name}</td>
                <td>${projectEntity.rootPath}</td>
                <td>${projectEntity.jarPath}</td>
                <td>${projectEntity.domainPath}</td>
                <td><a href="<c:url value='edit/${projectEntity.id}' />" >Edit</a></td>
                <td><a href="<c:url value='remove/${projectEntity.id}' />" >Delete</a></td>
            </tr>
        </c:forEach>

    </table>

</body>

</html>