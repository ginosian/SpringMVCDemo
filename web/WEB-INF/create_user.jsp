<%--
  Created by IntelliJ IDEA.
  User: Martha
  Date: 6/20/2016
  Time: 4:40 PM
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
  <table align="center" border="0" cellpadding="1" cellspacing="1" style="width: 100%; height: 100%">
    <tbody>
    <tr>
      <td colspan="3" style="text-align: center;">
        <form action="">
          <p>
            User First Name:<br />
            <input id="dfsds" name="username" style="height:12%;width:25%" type="text" /><br />
            User Login:<br />
            <input id="czzce" name="username" style="height:12%;width:25%" type="text" /><br />
            Password:<br />
            <input id="password" name="password" style="height:12%;width:25%" type="password" /></p>
          <input style="width:25%; position:relative; white-space:normal" type="submit" value="Create" />&nbsp;</form>
      </td>
    </tr>
    </tbody>
  </table>
  <p>
    v</p>
</div>
</body>
</html>

