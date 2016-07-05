<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Martha
  Date: 6/8/2016
  Time: 8:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>

<!--Received variables-->

<!--Root paths-->
<c:set var="root" value="${pageContext.request.contextPath}"/>
<c:set var="home" value="${home}"/>

<!--Received data-->
<c:set var="projects" value="${projects}"/>
<c:set var="tasks" value="${tasks}"/>
<c:set var="users" value="${users}"/>

<!--Onclick directing resources to request on object detail pages-->
<c:set var="user_detail_resource" value="${user_detail_resource}"/>
<c:set var="task_detail_resource" value="${task_detail_resource}"/>
<c:set var="project_detail_resource" value="${project_detail_resource}"/>

<!--Directing resources to open creating pages-->
<c:set var="create_user_resource" value="${create_user_resource}"/>
<c:set var="create_project_resource" value="${create_project_resource}"/>
<c:set var="create_task_resource" value="${create_task_resource}"/>

<c:set var="redirect_modify_task_to" value="${redirect_modify_task_to}"/>

  <table align="center" style="width: 100%; height: 100%; table-layout: fixed">
    <tbody>
      <tr>
        <td style="table-layout: fixed; vertical-align: middle; font-size: 36px; text-align: left; width: 33%; background-color: #B0BEC5;">Projects</td>
        <td style="table-layout: fixed; vertical-align: middle; font-size:36px; width: 33%; background-color: #C5E1A5;">Tasks</td>
        <td style="table-layout: fixed; vertical-align: middle; width: 34%; font-size:36px; background-color: #BCAAA4;">Users</td>
      </tr>
      <tr>
        <td style="text-align: left; table-layout: fixed; width: 33%; height: 100%; vertical-align: top; background-color: #ECEFF1">
          <ul type="disc" style="white-space: normal">
            <c:forEach items="${projects}" var="project">
              <li style="word-wrap:break-word; text-align: left; font-size:24px" type="disc"  value="${project.getId()}"
                  onclick="location.href='${root}/${home}/${project_detail_resource}/${project.getId()}'">${project.getStory()}</li>
            </c:forEach>
          </ul>
        </td>
        <td style="text-align: left; width: 33%; table-layout: fixed; height: 100%; vertical-align: top; background-color: #DCEDC8">
          <ul type="disc" style="white-space: normal">
            <c:forEach items="${tasks}" var="task">
              <li style="word-wrap:break-word; text-align: left; font-size:24px" type="disc"
                  onclick="location.href='${root}/${home}/${task_detail_resource}/${task.getId()}'">${task.getStory()}</li>
            </c:forEach>
          </ul>
        </td>
        <td style="text-align: left; vertical-align: top; table-layout: fixed; background-color: #D7CCC8; width: 33%; height: 100%">
          <ul>
            <c:forEach items="${users}" var="user">
              <li style="word-wrap:break-word; text-align: left; font-size:24px" type="disc"
                  onclick="location.href='${root}/${home}/${user_detail_resource}/${user.getId()}'" >${user.getName()}</li>
            </c:forEach>
          </ul>
        </td>
      </tr>
      <tr>
        <td style="table-layout: fixed; text-align: center; vertical-align: middle; height: 50px">
          <input onclick="location.href='${root}/${home}/${create_project_resource}'" style="width:60%;
          position:relative; white-space:normal; background-color: #B0BEC5; font-size:24px" type="button" value="Create project"/></td>
        <td style="table-layout: fixed; text-align: center; vertical-align: middle;">
          <input onclick="location.href='${root}/${home}/${create_task_resource}'" style="width:60%;
          position:relative; white-space:normal; background-color: #C5E1A5; font-size:24px" type="button" value="Create task"/></td>
        <td style="table-layout: fixed; text-align: center; vertical-align: middle;">
          <input onclick="location.href='${root}/${home}/${create_user_resource}'" style="width:60%;
          position:relative; white-space:normal; background-color: #BCAAA4; font-size:24px" type="button" value="Create user"/></td>
      </tr>
      <tr>
          <td colspan="3" style="table-layout: fixed; text-align: right; vertical-align: middle">
              <input onclick="location.href='${root}/logout'" style="width:20%;
          position:relative; white-space:normal; background-color: #B0BEC5; font-size:24px" type="button" value="LOGOUT" /></td>
      </tr>
    </tbody>
  </table>
</body>
</html>
