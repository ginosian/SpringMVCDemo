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
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <c:set var="project" value="${project}"/>
        <c:set var="modify" value="${modify}"/>
        <c:set var="redirect_modify_to" value="${redirect_modify_to}"/>
        <c:set var="users" value="${users}"/>
<ul>
    <li>
        <a href="${root}/${home}">Home</a></li>
    <li>
        <a class="active">Create task for project</a></li>
</ul>
    <form action="${root}/${home}/${modify}" method="post">
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
        <input type="hidden" name="home" value="${home}"/>
        <input type="hidden" name="redirect_modify_to" value="${redirect_modify_to}"/>
        <input type="hidden" name="taskId" value="${task.getId()}">
        <table align="center" border="0" cellpadding="0" cellspacing="0" style="width: 100%; height: 95%; background-color: rgb(235, 248, 252)">
            <tbody>
                <tr>
                    <td style="text-align: center; font-size:24px">Project: ${project.getStory()}</td>
                        <input type="hidden" name="projectId" value="${project.getId()}">
                </tr>
                <tr>
                    <td style="text-align: center; font-size:24px">Task Story:</td>
                </tr>
                <tr>
                    <td style="text-align: center">
                        <textarea name="task_story" required="required" rows="1" style="width:70%; font-size:24px; resize: none"></textarea></td>
                </tr>
                <tr>
                    <td style="text-align: center; font-size:24px">Task Description:</td>
                </tr>
                <tr>
                    <td style="text-align: center">
                        <textarea name="task_description" required="required" rows="3" style="width: 70%; resize: none"></textarea></td>
                </tr>
                <tr>
                    <td style="text-align: center; font-size:20px">Choose Assignee</td>
                </tr>
                <tr>
                    <td style="text-align: center;">
                        <select name="userId" required="required" size="6" style="width:25%;font-size:18px">
                            <c:forEach items="${users}" var="user">
                            <option value="${user.getId()}">${user.getName()}</option>
                            </c:forEach>
                        </select></td>
                </tr>
                <tr>
                    <td style="text-align: center">
                        <input style="width:25%; font-size:24px position:relative; white-space:normal" type="submit" value="SUBMIT" /></td>
                </tr>
            </tbody>
        </table>
    </form>
</body>
</html>

