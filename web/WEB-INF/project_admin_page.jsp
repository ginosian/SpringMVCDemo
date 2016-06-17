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
<table cellpadding="1" cellspacing="1" style="width: 100%; height: 100%; ">
  <tbody>
  <tr>
    <td colspan="2" style="height: 60px; text-align: center; vertical-align: top;">
      &nbsp;
      <ul>
        <li>
          <a href="#home_tab">Home</a></li>
        <li>
          <a href="#projects_tab">Projects</a></li>
        <li>
          <a class="active" href="#project_tab">Project Name</a></li>
      </ul>
    </td>
  </tr>
  <tr>
    <td colspan="2" style="height: 15px;">
      &nbsp;</td>
  </tr>
  <tr>
    <td colspan="2" style="height: 60px;">
      <div style="background-color: rgb(235, 248, 252); color:black;">
        <h2>
          <span style="font-size:48px;">Project name</span></h2>
        <p>
          London is the capital city of England. It is the most populous city in the United Kingdom, with a metropolitan area of over 13 million inhabitants.</p>
        <p>
          Standing on the River Thames, London has been a major settlement for two millennia, its history going back to its founding by the Romans, who named it Londinium.</p>
      </div>
    </td>
  </tr>
  <tr>
    <td colspan="2" style="height: 30px;">
      &nbsp;</td>
  </tr>
  <tr>
    <td colspan="2" style="height: 60px; text-align: center; background-color: rgb(102, 153, 153);">
      <span style="font-size:48px;">Tasks</span></td>
  </tr>
  <tr>
    <td style="width: 70%; ">
      <div style="width:100%; height:180px; overflow:scroll;">
        <span style="font-size: 26px;">Task name </span><span style="font-size: 26px; color: red;">Assignee</span><span style="font-size: 26px;"> User name</span>
        <p>
          <span style="font-size: 26px;">${id}              </span><span style="font-size: 26px; color: red;">${name}</span><span style="font-size: 26px;"> ${role}</span></p>
        <p>
          <span style="font-size: 26px;">Task name              </span><span style="font-size: 26px; color: red;">Assignee</span><span style="font-size: 26px;"> User name</span></p>
        <p>
          <span style="font-size: 26px;">Task name              </span><span style="font-size: 26px; color: red;">Assignee</span><span style="font-size: 26px;"> User name</span></p>
        <p>
          <span style="font-size: 26px;">Task name              </span><span style="font-size: 26px; color: red;">Assignee</span><span style="font-size: 26px;"> User name</span></p>
        <p>
          <span style="font-size: 26px;">Task name              </span><span style="font-size: 26px; color: red;">Assignee</span><span style="font-size: 26px;"> User name</span></p>
        <p>
          <span style="font-size: 26px;">Task name              </span><span style="font-size: 26px; color: red;">Assignee</span><span style="font-size: 26px;"> User name</span></p>
        <p>
          <span style="font-size: 26px;">Task name              </span><span style="font-size: 26px; color: red;">Assignee</span><span style="font-size: 26px;"> User name</span></p>
        <p>
          <span style="font-size: 26px;">Task name              </span><span style="font-size: 26px; color: red;">Assignee</span><span style="font-size: 26px;"> User name</span></p>
        <p>
          <span style="font-size: 26px;">Task name              </span><span style="font-size: 26px; color: red;">Assignee</span><span style="font-size: 26px;"> User name</span></p>
        <p>
          <span style="font-size: 26px;">Task name              </span><span style="font-size: 26px; color: red;">Assignee</span><span style="font-size: 26px;"> User name</span></p>
      </div>
    </td>
  </tr>
  <tr>
    <td colspan="2">
      &nbsp;</td>
  </tr>
  </tbody>
</table>
<p>
  &nbsp;</p>
</body>
</html>

