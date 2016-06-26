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
<p>
    &nbsp;</p>
<ul>
    <li>
        <a href="#home_tab">Home</a></li>
    <li>
        <a href="#projects_tab">Projects</a></li>
    <li>
        <a class="active" href="#project_tab">Project Name</a></li>
</ul>

<div style="color: black; text-align: center; background-color: rgb(235, 248, 252);">
    <form:form action="${root}/${home}/${modify}" method="post">
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <c:set var="home" value="${home}"/>
        <input type="hidden" name="home" value="${home}"/>
        <c:set var="modify" value="${modify}"/>

        <c:set var="redirect_modify_to" value="${redirect_modify_to}"/>
        <input type="hidden" name="redirect_modify_to" value="${redirect_modify_to}"/>

<div style="background-color: rgb(235, 248, 252); color:black;">
        <table align="center" border="0" cellpadding="0" cellspacing="0" style="width: 100%; height: 70%">
            <tbody>
            <tr>
                <td colspan="2" style="text-align: center;">
                    <span>Project Story:</span><br />
                    <textarea name="project_story" rows="5" style="width: 70%" required></textarea></td>
            </tr>

            <tr>
                <td colspan="2" style="text-align: center;">
                    <span>Project Description:</span><br />
                    <textarea name="project_description" rows="5" style="width: 70%"></textarea></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center;">
                    <input style="width:25%; position:relative; white-space:normal" type="submit" value="SUBMIT" /></td>
            </tr>
            </tbody>
        </table>
    </form:form></div>
</body>
</html>

