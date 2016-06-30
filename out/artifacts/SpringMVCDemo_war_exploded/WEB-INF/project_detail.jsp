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
<body style="background-color: rgb(235, 248, 252)">
<!--Received data-->

<!--Root paths-->
<c:set var="root" value="${pageContext.request.contextPath}"/>
<c:set var="home" value="${home}"/>
<c:set var="modify" value="${modify}"/>
<c:set var="redirect_modify_to" value="${redirect_modify_to}"/>
<c:set var="redirect_modify_task_to" value="${redirect_modify_task_to}"/>

<!--Received data-->
<c:set var="project" value="${project}"/>
<c:set var="project_tasks" value="${project_tasks}"/>

<!--Received data projects list for task_detail resource-->
<c:set var="projects" value="${projects}"/>

<!--Onclick directing resources to request on object detail pages-->
<c:set var="task_detail_resource" value="${task_detail_resource}"/>
<c:set var="create_task_resource" value="${create_task_resource}"/>

<ul>
    <li>
        <a href="${root}/${home}">Home</a></li>
    <li>
        <a class="active">Project detail</a></li>
</ul>
    <form action="${root}/${home}/${modify}" method="post">
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
        <input type="hidden" name="home" value="${home}"/>
        <input type="hidden" name="projectId" value="${project.getId()}"/>
        <input type="hidden" name="redirect_modify_task_to" value="${redirect_modify_task_to}"/>

        <table align="center" style="width: 100%; height: 95%; background-color: rgb(235, 248, 252)">
            <tbody>
                <tr>
                    <td style="text-align: center; font-size:24px; width: 40%">
                        <span>Project Story:</span><br />
                        <textarea style="font-size: 22px; width: 90%; resize: none" name="project_story" required="required" rows="4">${project.getStory()}</textarea>
                    </td>
                    <td colspan="2" style="text-align: center; font-size:24px; width: 40%">
                        Project Tasks
                    </td>

                </tr>
                <tr>
                    <td style="text-align: center; width: 40%;">
                            <span style="font-size: 24px;">Project Description:</span><br />
                            <textarea style="font-size: 22px; width: 90%; resize: none"  name="project_description" rows="8" required="required">${project.getDescription()}</textarea>
                    </td>
                    <td style="height: 100px; text-align: center; width: 60%;">
                        <ul style="text-align: left; background-color: #dcfcc4">
                            <c:forEach items="${project_tasks}" var="project_task" >
                                <li onclick="location.href='${root}/${home}/${task_detail_resource}?taskId=${project_task.getId()}&projectId=${project.getId()}&home=${home}&redirect_modify_task_to=${redirect_modify_task_to}'"
                                    style="text-align: left; font-size:24px; background-color: #dcfcc4">
                                    <span style="font-size:24px" >${project_task.getStory()}</span><wbr>
                                    <span style="font-size:16px" >Assignee</span><wbr>
                                    <span style="font-size:24px" >${project_task.getUserDTO().getName()}</span>

                                </li>
                                <p>
                                    &nbsp;</p>
                            </c:forEach>
                        </ul>
                        <p>
                            &nbsp;</p>
                        <input style="width:20%; resize: none; font-size:24px; position:relative; white-space:normal" type="button" value="Add Task"
                               onclick="location.href='${root}/${home}/${create_task_resource}?&projectId=${project.getId()}&home=${home}&redirect_modify_task_to=${redirect_modify_task_to}'"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" style="text-align: center;">
                        <input style="width:20%; resize: none; font-size:24px; position:relative; white-space:normal" type="submit" value="SUBMIT" /></td>
                </tr>
            </tbody>
        </table>
    </form>
<p>
    &nbsp;</p>
</body>
</html>

