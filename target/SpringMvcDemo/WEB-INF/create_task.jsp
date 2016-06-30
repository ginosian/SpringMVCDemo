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
        <c:set var="home" value="${home}"/>
        <c:set var="modify" value="${modify}"/>
        <c:set var="redirect_modify_to" value="${redirect_modify_to}"/>
        <input type="hidden" name="redirect_modify_to" value="${redirect_modify_to}"/>
        <c:set var="users" value="${users}"/>
        <c:set var="projects" value="${projects}"/>
<ul>
    <li>
        <a href="${root}/${home}">Home</a></li>
    <li>
        <a class="active">Create Task</a></li>
</ul>
    <form action="${root}/${home}/${modify}" method="post">
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
        <input type="hidden" name="home" value="${home}"/>
        <input type="hidden" name="taskId" value="${task.getId()}">

        <table align="center" style="width: 100%; height: 95%; background-color: rgb(235, 248, 252)">
            <tbody>
                <tr>
                    <td colspan="2" style="text-align: center; font-size:24px">Task Story:</td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center; font-size:24px">
                        <textarea name="task_story" required="required" rows="2" style="width: 70%; resize: none"></textarea></td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center; font-size:24px">Task Description:</td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center; font-size:24px">
                        <textarea name="task_description" rows="3"  style="width: 70%; resize: none" required="required"></textarea></td>
                </tr>
                <tr>
                    <td style="text-align: center; font-size:24px">Choose Project</td>
                    <td style="text-align: center; font-size:24px">Choose Assignee</td>
                </tr>
                <tr>
                    <td style="height: 30%; text-align: center;">
                        <select name="projectId" required="required" size="6" style="width:45%; font-size:18px">
                            <c:forEach items="${projects}" var="project">
                                <option value="${project.getId()}">${project.getStory()}</option>
                            </c:forEach>
                        </select></td>
                    <td style="height: 30%; text-align: center;">
                        <select name="userId" required="required" size="6" style="width:45%; font-size:18px">
                            <c:forEach items="${users}" var="user">
                                <option value=${user.getId()}>${user.getName()}</option>
                            </c:forEach>
                        </select></td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <input style="width:25%;  font-size:24px; position:relative; white-space:normal" type="submit" value="SUBMIT" /></td>
                </tr>
            </tbody>
        </table>
    </form>
</body>
</html>

