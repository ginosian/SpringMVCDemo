<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
  <c:set var="root" value="${pageContext.request.contextPath}"/>
  <c:set var="home" value="${home}"/>

<ul>
  <li>
    <a href="${root}/${home}">Home</a></li>
  <li>
    <a class="active" >Create user</a></li>
</ul>

<c:set var="root" value="${pageContext.request.contextPath}"/>
<form:form action="${root}/${home}/${modify}" method="post">
  <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
  <input type="hidden" name="home" value="${home}"/>
  <c:set var="modify" value="${modify}"/>
  <c:set var="redirect_modify_to" value="${redirect_modify_to}"/>
  <input type="hidden" name="redirect_modify_to" value="${redirect_modify_to}"/>
  <table align="center" border="0" cellpadding="1" cellspacing="1" style="width: 100%; background-color: rgb(235, 248, 252); height: 100%">
    <tbody>
    <tr>
      <td colspan="3" style="text-align: center;">
        <br>User First Name:<br/>
        <br><input name="name" style="height:40px; width:25%; resize: none" type="text" required="required"/><br/>
        <br>User Login:<br/>
        <br><input name="username" style="height:40px; width:25%; resize: none" type="email" required="required"/><br/>
        <br>Password:<br/>
        <br><input name="password" style="height:40px; width:25%; resize: none" type="password" required="required"/><br/>
        <br>Approve with authorization:<br/>
          <select name="role" style="height:40px; width:25%; resize: none" required="required">
            <c:forEach items="${roles}" var="role">
              <option>${role.getRole()}</option>
            </c:forEach>
          </select>
          <p>
              &nbsp;</p>
        <br><input style="width:25%; font-size:24px; position:relative; white-space:normal" type="submit" value="Create" /></br>
      </td>
    </tr>
    <tr>
      <td>
        <div>
          <p style="color: #c51202">${error}</p>
        </div>
      </td>
    </tr>
    </tbody>
  </table>


</form:form>
</body>
</html>
