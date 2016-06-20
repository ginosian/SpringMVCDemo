<%--
  Created by IntelliJ IDEA.
  User: Martha
  Date: 6/8/2016
  Time: 10:24 PM
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
  <h2>
    <span style="font-size:48px;">Project name</span></h2>
  <p>
    London is the capital city of England. It is the most populous city in the United Kingdom, with a metropolitan area of over 13 million inhabitants.</p>
  <p>
    Standing on the River Thames, London has been a major settlement for two millennia, its history going back to its founding by the Romans, who named it Londinium.</p>
</div>
<p>
  &nbsp; <span style="font-size:48px;">Tasks</span></p>
<p>
  &nbsp; ${task.getName} ${task.getUsername} <a class="btn btn-success custom-width" href="&lt;c:url value='/edit-task-${task}' /&gt;">edit</a> &nbsp; &nbsp;</p>
<p>
  <sec:authorize access="hasRole('USER')"> <a href="&lt;c:url value='/newTask' /&gt;">Add New Task</a> </sec:authorize></p>
<table cellpadding="1" cellspacing="1" style="width: 100%; height: 100%; ">
</table>
<p>
  &nbsp;</p>
<div>
  &nbsp;</div>
</body>
</html>
