<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Martha
  Date: 6/20/2016
  Time: 4:53 PM
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
    }  </style>
</head>
<body>
<p>
  &nbsp;</p>
<ul>
  <li>
    <a href="#home_tab">Home</a></li>
  <li>
    <a href="#projects_tab">Projects</a></li>
  <li>
    <a class="active" href="#project_tab">Project Name</a></li>
</ul>
<p>
  &nbsp;</p>
<div style="background-color: rgb(235, 248, 252); color:black;">
<form:form action="${root}/admin/add_project" method="post">
  <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
  <table border="0" cellpadding="1" cellspacing="1" style="width: 100%; height: 100%">
    <tbody>
    <tr>
      <td colspan="2" style="text-align: center;">
        Project Name:<br />
        <textarea name="project_story" rows="2" cols="50" style="width: 70%" required></textarea>
      </td>
    </tr>
    <tr>
      <td colspan="2" style="text-align: center;">
        Project Description:<br />
        <textarea name="project_description" rows="5" cols="50" style="width: 70%"></textarea></td>
    </tr>
    <tr>
      <td colspan="2" style="text-align: center;">
        <p>
          &nbsp;</p>
        <input style="width:25%; position:relative; white-space:normal" type="submit" value="SUBMIT" /></td>
    </tr>
    </tbody>
  </table>
</form:form>

</div>
</body>
</html>
