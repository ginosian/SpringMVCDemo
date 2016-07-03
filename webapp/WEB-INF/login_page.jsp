<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  UserDTO: Martha
  Date: 6/8/2016
  Time: 8:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<form method="post">

<table align="center"style="width: 100%; height: 100%">
    <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
    <tbody>
        <tr>
            <td colspan="3" style="text-align: center">
                <img src="<c:url value="/image/auth_banner.png"/>" style="width: 100%; height: 100%" /></td>
        </tr>
        <tr>
            <td colspan="3" style="text-align:center; font-size:18px">
                <br>Username:<br/>
                <input type="email" id="username" name="username" style="font-size:18px; width:25%; resize: none; background-color: #feffbb"/><br />
                <br>Password:<br />
                <input type="password" id="password" name="password" style="font-size:18px; width:25%; resize: none; background-color: #feffbb"/></p>
                <label><input type="checkbox" name="remember-me"> Remember Me</label>
            </td>
        </tr>
        <tr>
            <td colspan="3" style="text-align: center">
                <input style="width:25%; font-size:20px; position:relative; white-space:normal" type="submit" value="LOGIN"/></td>
        </tr>
        <tr>
            <td colspan="3" style="text-align: center">
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger">
                        <p style="color: #c51202">Invalid username or password.</p>
                    </div>
                </c:if>
            </td>
        </tr>
    </tbody>
</table>
</form>
</html>
