<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<c:set var="root" value="${pageContext.request.contextPath}"/>
<form action="${root}/admin" method="get">
  <table align="center" cellpadding="1" cellspacing="1" id="admin_home" style="width: 100%; height: 100%">
    <tbody>
    <tr>
      <th style="vertical-align: middle; height: 50px; text-align: left; width: 30%; background-color: #B0BEC5;">
        <span style="font-size: 36px;">Projects</span></th>
      <td style="vertical-align: middle; width: 40%; background-color: #C5E1A5;">
        <strong><span style="font-size:36px;">Tasks</span></strong></td>
      <td style="vertical-align: middle; background-color: #BCAAA4;">
        <strong><span style="font-size:36px;">Users</span></strong></td>
    </tr>
    <tr>
      <td style="text-align: left; vertical-align: top; background-color: #ECEFF1" style="width: 100%; height: 100%">
        <ul id="project_list_admin">
          <li id="project1" style="text-align:left;">
            <span style="font-size:24px;">Software development</span></li>
          <li id="project2" style="text-align: left;">
            <span style="font-size:24px;">Mobile development</span></li>
          <li id="project3" style="text-align: left;">
            <span style="font-size:24px;">Web development</span></li>
        </ul>
      </td>
      <td style="text-align: left; vertical-align: top; background-color: #DCEDC8" style="width: 100%; height: 100%">
        <ul id="task_list_admin">
          <li id="task1" style="text-align: left;">
            <span style="font-size:24px;"><span style="font-size:24px;">Review, Assessment and Analysis</span></span></li>
          <li id="task2" style="text-align: left;">
            <span style="font-size:24px;"><span style="font-size:24px;">Specification Building</span></span></li>
          <li id="task3" style="text-align: left;">
            <span style="font-size:24px;"><span style="font-size:24px;">Design and development</span></span></li>
          <li id="task4" style="text-align: left;">
            <span style="font-size:24px;"><span style="font-size:24px;">Content writing</span></span></li>
          <li id="task5" style="text-align: left;">
            <span style="font-size:24px;"><span style="font-size:24px;">Coding</span></span></li>
          <li id="task6" style="text-align: left;">
            <span style="font-size:24px;"><span style="font-size:24px;">Testing</span></span></li>
          <li id="task7" style="text-align: left;">
            <span style="font-size:24px;"><span style="font-size:24px;">SEO and Social Media Optimization</span></span></li>
          <li id="task8" style="text-align: left;">
            <span style="font-size:24px;"><span style="font-size:24px;">Maintenance and Updating</span></span></li>
        </ul>
      </td>
      <td style="text-align: left; vertical-align: top; background-color: #D7CCC8; width: 100%; height: 100%">
        <ul id="user_list_admin" >
            <c:forEach items="${users}" var="users">
              <li style="text-align: left" id="${users.getUsername()}">
                <span style="font-size:24px;">${users.getName()}</span>
              </li>
            </c:forEach>
        </ul>
      </td>
    </tr>
    <tr>
      <td style="text-align: center; vertical-align: middle; height: 50px">
        <input name="create_project" onclick="location.href='${root}/admin/create_project'" style="width:60%; position:relative; white-space:normal; background-color: #B0BEC5; font-size:24px" type="button" value="Create projectDTO" /></td>
      <td style="text-align: center; vertical-align: middle;">
        <input name="create_task" style="width:60%; position:relative; white-space:normal; background-color: #C5E1A5; font-size:24px" type="button" value="Create taskDTO" /></td>
      <td style="text-align: center; vertical-align: middle;">
        <input name="create_user" onclick="location.href='${root}/admin/create_user'" style="width:60%; position:relative; white-space:normal; background-color: #BCAAA4; font-size:24px" type="button" value="Create userDTO" /></td>
    </tr>
    </tbody>
  </table>
  <p>
    &nbsp;</p>
</form>
<p>
  &nbsp;</p>
</body>
</html>
