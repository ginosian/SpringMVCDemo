<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Martha
  Date: 6/20/2016
  Time: 4:50 PM
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
        }		</style>
</head>
<body>
<ul>
    <li>
        <a href="#home_tab">Home</a></li>
    <li>
        <a href="#projects_tab">Tasks</a></li>
    <li>
        <a class="active" href="#project_tab">Create Task</a></li>
</ul>
<div style="color: black; text-align: center; background-color: rgb(235, 248, 252);">
        <c:set var="root" value="${pageContext.request.contextPath}"/>
    <form:form action="${root}/${home}/${modify}" method="post">

        <c:set var="modify" value="${modify}"/>
        <input type="hidden" name="home" value="${home}"/>
        <!--Received data-->

        <!--Root paths-->
        <c:set var="redirect_modify_to" value="${redirect_modify_to}"/>
        <input type="hidden" name="redirect_modify_to" value="${redirect_modify_to}"/>

        <!--Received data-->
        <c:set var="users" value="${users}"/>
        <c:set var="project" value="${project}"/>


        <!--If modified passes string task_story, task_description, taskId and userId.-->

        <input type="hidden" name="taskId" value="${task.getId()}">


        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
        <table align="center" border="0" cellpadding="0" cellspacing="0" style="width: 100%; height: 100%">
            <tbody>
            <tr>
                <td style="text-align: center; font-size:24px">
                    <span>Project:</span><br />
            </tr>
            <tr>
                <td style="text-align: center; font-size:24px">
                    <span>${project.getStory()}</span>
                    <input type="hidden" name="projectId" value="${project.getId()}">
            </tr>
            <tr>
                <td style="text-align: center; font-size:24px">
                    <span>Task Story:</span><br />
                    <textarea name="task_story" required="" rows="1" style="width: 70%"></textarea></td>
            </tr>
            <tr>
                <td style="text-align: center; font-size:24px">
                    <span>Task Description:</span><br />
                    <textarea name="task_description" rows="3" style="width: 70%"></textarea></td>
            </tr>
            <tr>
                <td style="text-align: center;">
                    <p style="font-size:20px" type="text">
                        Choose Assignee</p>
                </td>
            </tr>
            <tr>
                <td style="height: 150px; text-align: center;">
                    <select name="userId" required size="8" style="width:25%;font-size:18px">
                        <c:forEach items="${users}" var="user">
                        <option value="${user.getId()}">${user.getName()}</option>
                        </c:forEach>
                    </select></td>
            </tr>
            <tr>
                <td tyle="text-align: center;">
                    <input style="width:25%; position:relative; white-space:normal" type="submit" value="SUBMIT" /></td>
            </tr>
            </tbody>
        </table>
    </form:form></div>
</body>
</html>

