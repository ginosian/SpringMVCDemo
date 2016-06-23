<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Martha
  Date: 6/22/2016
  Time: 11:25 AM
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
    <form action="${root}/admin/add_project_task" method="post">
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />

        <input name="task_id" hidden value="${task_id}"/>
        <table align="center" style="width: 100%; height: 95%; background-color: rgb(235, 248, 252)">
            <tbody>
            <tr style="text-align: center; font-size:24px; width: 100%">
                <td colspan="3" style="text-align: center; font-size:24px; width: 100%">
                    <a style="font-size:24px">Project:</a>
                    <input name="project" hidden style="font-size:24px" value="${project}"/>
                    <a style="font-size:24px">${project}</a>
                </td>
            </tr>
            <tr>
                <td style="text-align: center; font-size:24px; width: 50%">
                    <span style="text-decoration-color: #2f0407">Task Story:</span><br />
                    <textarea name="task_story" required="" rows="4" style="width: 90%"></textarea>
                </td>
                <td colspan="2" style="text-align: center; font-size:24px; width: 50%">
                    Choose assignee to for task
                </td>
            </tr>
            <tr>
                <td style="height: 150px; text-align: center; width: 50%;">
                        <span style="font-size: 24px;">Task Description:</span><br />
                        <textarea name="task_description" rows="8" style="width: 90%"></textarea>
                </td>
                <td colspan="2" style="height: 100px; text-align: center; width: 50%;">
                    <select name="user" size="8" style="width: 80%; font-size: 18px;">
                        <c:forEach items="${users}" var="users">
                        <option>${users.getName()}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="3" style="text-align: center;">
                    <input style="width:20%; height:40px; position:relative; white-space:normal" type="submit" value="SUBMIT" /></td>
            </tr>
            </tbody>
        </table>
    </form>
<p>
    &nbsp;</p>
</body>
</html>

