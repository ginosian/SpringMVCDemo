<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Martha
  Date: 6/20/2016
  Time: 4:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style type="text/css">
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #333;
        }

        li {
            float: left;
        }

        li a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        a:hover:not(.active) {
            background-color: #111;
        }

        .active {
            background-color:#4CAF50;
        }  </style>
</head>
<body>
<ul>
    <li>
        <a href="${root}/${home}">Home</a></li>
    <li>
        <a class="active">Create project</a></li>
</ul>
    <form action="${root}/${home}/${modify}" method="post">
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <c:set var="home" value="${home}"/>
        <c:set var="modify" value="${modify}"/>
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
        <table align="center" style="width: 100%; height: 100%; background-color: rgb(235, 248, 252)">
            <tbody>
                <tr>
                    <td style="table-layout: fixed; text-align: center; font-size:24px">
                        <p>Project Story:<p/>
                        <p><textarea name="project_story" rows="3" style="width:70%; font-size:24px; resize: none" ></textarea><p/>
                        <p>Project Description:<p/>
                        <p><textarea name="project_description" rows="5" style="width:70%; font-size:24px; resize: none" ></textarea><p/>
                        <c:set var="error" value="${error}"/>
                        <p style="color: #c51202">${error}</p>
                        <input style="width:25%; font-size:24px; position:relative; white-space:normal" type="submit" value="SUBMIT"/>
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</body>
</html>

