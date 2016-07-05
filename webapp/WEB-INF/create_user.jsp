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
    <ul>
      <li>
        <a href="${root}/${home}">Home</a></li>
      <li>
        <a class="active" >Create user</a></li>
    </ul>

    <form:form action="${root}/${home}/${modify}" method="post">
      <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
      <c:set var="root" value="${pageContext.request.contextPath}"/>
      <c:set var="home" value="${home}"/>
      <c:set var="modify" value="${modify}"/>
      <c:set var="redirect_modify_to" value="${redirect_modify_to}"/>
      <table align="center" style="width: 100%; background-color: rgb(235, 248, 252); height: 100%; font-size:16px;">
        <tbody>
        <tr>
          <td colspan="3" style="table-layout: fixed; text-align: center;">
            <p>User First Name:</p>
            <p><input maxlength="40" name="name" style="height:40px; width:25%; resize: none" type="text" /></p>
            <p>User Login:</p>
            <p><input maxlength="40" name="username" style="height:40px; width:25%; resize: none" type="email" /></p>
            <p>Password:</p>
            <p><input maxlength="15" name="password" style="height:40px; width:25%; resize: none" type="password" /></p>
            <p>Approve with authorization:</p>
              <select maxlength="15" name="role" style="height:40px; width:25%; resize: none">
                  <option></option>
                <c:forEach items="${roles}" var="role">
                  <option>${role.getRole()}</option>
                </c:forEach>
              </select>
              <c:set var="error" value="${error}"/>
              <p style="color: #c51202">${error}</p><br/>
            <p><input style="width:25%; font-size:24px; position:relative; white-space:normal" type="submit" value="Create" /></p>
          </td>
        </tr>
        </tbody>
      </table>
    </form:form>
</body>
</html>
