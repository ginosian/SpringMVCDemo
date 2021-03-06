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
            <a href="${root}/${home}">Home</a></li>
        <li>
            <a class="active">Project detail</a></li>
    </ul>
    <form action="${root}/${home}/${modify}" method="post">
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <c:set var="home" value="${home}"/>
        <c:set var="modify" value="${modify}"/>
        <c:set var="task_detail_resource" value="${task_detail_resource}"/>
        <c:set var="create_task_resource" value="${create_task_resource}"/>

        <c:set var="back" value="${back}"/>
        <input name="back" type="hidden" value="${back}" />
        <c:set var="project" value="${project}"/>
        <input name="projectId" type="hidden" value="${projectId}" />
        <c:set var="project_tasks" value="${project_tasks}"/>

        <table align="center" style="width: 100%; height: 95%; background-color: rgb(235, 248, 252); table-layout: fixed">
            <tbody>
                <tr style="text-align: left; table-layout: fixed">
                    <td style="table-layout: fixed;  text-align:center; font-size:24px; width: 40%" valign="top">
                        <p style="color: #2f0407">Project Story:</p><br />
                        <textarea style="font-size: 22px; width: 90%; resize: none" name="project_story" rows="4">${project.getStory()}</textarea>
                        <p style="font-size: 24px; color:#2f0407">Project Description:</p><br />
                        <textarea style="font-size: 22px; width: 90%; resize: none"  name="project_description" rows="8" >${project.getDescription()}</textarea>
                    </td>
                    <td style="table-layout: fixed; text-align:center; font-size:24px; width: 40%; color: #2f0407" valign="top">
                        Project Tasks
                        <ul type="disc" style="white-space: normal; background-color: #dcfcc4">
                            <c:forEach items="${project_tasks}" var="project_task" >
                                <li style="word-wrap:break-word; text-align: left; background-color: #dcfcc4" type="disc"
                                    onclick="location.href='${root}/${home}/${task_detail_resource}/${project_task.getId()}?back=${back}'">
                                        <span style="font-size:24px">${project_task.getStory()}</span><wbr>
                                        <span style="color:#130eb6; font-size:18px">Assignee:</span><wbr>
                                        <span style="color:#130eb6; font-size:24px">${project_task.getUserDTO().getName()}</span>
                                </li>
                                <p>
                                    &nbsp;</p>
                            </c:forEach>
                        </ul>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: center">
                        <c:set var="error" value="${error}"/>
                        <p style="color: #c51202">${error}</p>
                        <input style="width:20%; resize: none; font-size:24px; position:relative; white-space:normal" type="submit" value="SUBMIT" />
                    </td>
                    <td style="text-align: center">
                        <input style="width:20%; resize: none; font-size:24px; position:relative; white-space:normal" type="button" value="Add Task"
                               onclick="location.href='${root}/${home}/${create_task_resource}'"/>
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
<p>
    &nbsp;</p>
</body>
</html>

