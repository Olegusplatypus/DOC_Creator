<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html lang="en">
<head>
    <title>Project Page</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<h1>
    <c:choose>
        <c:when test="${!empty projectEntity.id}">
            Edit project
        </c:when>
        <c:otherwise>
            Add project
        </c:otherwise>
    </c:choose>
</h1>

<c:url var="addProject" value="/project/add"></c:url>

<form:form action="${addProject}" method="post" commandName="project">
    <table>
        <tr hidden>
            <td>
                <form:input path="id"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="name">
                    <spring:message text="Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="rootPath">
                    <spring:message text="RootPath"/>
                </form:label>
            </td>
            <td>
                <form:input path="rootPath"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="jarPath">
                    <spring:message text="JarPath"/>
                </form:label>
            </td>
            <td>
                <form:input path="jarPath"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="domainPath">
                    <spring:message text="DomainPath"/>
                </form:label>
            </td>
            <td>
                <form:input path="domainPath"/>
            </td>
        </tr>
    </table>
    <input type="submit"
           value="<spring:message text="Save"/>"/>
</form:form>
<br>
<form>
    <button type="submit" formaction="/">Home</button>
    <button type="submit" formaction="/project/getall">Projects</button>
</form>


</body>

</html>