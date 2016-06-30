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
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <c:set var="home" value="${home}"/>
        <c:set var="modify" value="${modify}"/>
        <c:set var="redirect_modify_to" value="${redirect_modify_to}"/>
        <c:set var="task" value="${task}"/>
        <c:set var="users" value="${users}"/>
<ul>
    <li>
        <a href="${root}/${home}">Home</a></li>
    <li>
        <a class="active" >Task detail</a></li>
</ul>
    <form action="${root}/${home}/${modify}" method="post">
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
        <input type="hidden" name="home" value="${home}"/>
        <input type="hidden" name="redirect_modify_to" value="${redirect_modify_to}"/>
        <input type="hidden" name="projectId" value="${task.getProjectDTO().getId()}"/>
        <input type="hidden" name="taskId" value="${task.getId()}">
        <table align="center" style="width: 100%; height: 95%; background-color: rgb(235, 248, 252)">
            <tbody>
                <tr>
                    <td colspan="3"  style="text-align: center; font-size:24px; width: 50%">
                        <span style="font-size:24px">Project:</span>
                        <span style="font-size:24px">${task.getProjectDTO().getStory()}</span>
                    </td>
                </tr>

                <tr>
                    <td colspan="3" style="text-align: center; font-size:24px; width: 50%">
                        <input type="hidden" name="userIdForUserTD" value="${task.getUserDTO().getId()}"/>
                        <p style="text-decoration-color: #2f0407"> Current Assignee <br></p>
                        <p>${task.getUserDTO().getName()}</p>
                </tr>
                <tr>
                    <td style="text-align: center; font-size:24px; width: 50%">
                        <span style="text-decoration-color: #2f0407">Task Story:</span><br />
                        <textarea name="task_story" required="required" rows="4" style="width: 90%; resize: none;">${task.getStory()}</textarea>
                    </td>
                    <td colspan="2" style="text-align: center; font-size:24px; width: 50%">
                        Choose assignee to reassign task
                    </td>
                </tr>
                <tr>
                    <td style="text-align: center; width: 50%;">
                            <span style="font-size: 24px;">Task Description:</span><br />
                            <textarea name="task_description" rows="8" style="width: 90%; resize: none;" required="required">${task.getDescription()}</textarea>
                    </td>
                    <td colspan="2" style="height: 100px; text-align: center; width: 50%;">
                        <select name="userId" size="8" style="width: 75%; font-size: 18px;">
                            <c:forEach items="${users}" var="user">
                            <option value="${user.getId()}">${user.getName()}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" style="text-align: center;">
                        <input style="width:20%; font-size:24px; position:relative; white-space:normal" type="submit" value="SUBMIT" /></td>
                </tr>
            </tbody>
        </table>
    </form>
<p>
    &nbsp;</p>
</body>
</html>

