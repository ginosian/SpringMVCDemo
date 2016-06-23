<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Martha
  Date: 6/23/2016
  Time: 12:56 PM
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
<form action="${root}/admin" method="post">
  <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />

  <input name="user_id" hidden value="${user_id}"/>
  <table align="center" style="width: 100%; height: 95%; background-color: rgb(235, 248, 252)">
    <tbody>
    <tr>
      <td style="text-align: center; font-size:24px; width: 40%">
        <span>User:</span><br />
        <textarea style="font-size: 22px; width: 90%" name="user" required="" rows="4">${user}</textarea>
      </td>
    </tr>
    <tr>
      <td style="height: 100px; text-align: center; width: 60%;">
<c:forEach items="${projects}" var="projects" >
        <ul id="tasks_list_project" style="text-align: left; background-color: #dcfcc4">
          <c:forEach items="${projects}" var="project" >
            <li onclick="location.href='${root}/admin/project_task_detail?story=${tasks.getStory()}'"
                style="text-align: left; font-size:24px; background-color: #dcfcc4" id="${tasks.getId()}">
              <span style="font-size:24px" >${tasks.getStory()}</span><wbr>
              <span style="font-size:16px" >Assignee</span><wbr>
              <span style="font-size:24px" >${tasks.getUserDTO().getName()}</span>

            </li>
            <p>
              &nbsp;</p>
          </c:forEach>
        </ul>
  <p>
    &nbsp;</p>
</c:forEach>
        <p>
          &nbsp;</p>
        <input style="width:20%; height:40px; position:relative; white-space:normal" type="button" value="Add Task"
               onclick="location.href='${root}/admin/create_project_task?project_id=${project_id}'"/>
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

