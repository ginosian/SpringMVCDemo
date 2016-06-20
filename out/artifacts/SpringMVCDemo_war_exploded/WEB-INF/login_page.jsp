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
    <c:if test="${param.error != null}">
    <div class="alert alert-danger">
        <p>Invalid username and password.</p>
    </div>
    </c:if>
<table align="center" border="0" cellpadding="1" cellspacing="1" style="width: 100%; height: 100%">
    <tbody>
    <tr>
        <td colspan="3" style="text-align: center">
            <img src="<c:url value="/image/auth_banner.jpeg"/>" style="width: 100%; height: 100%;" /></td>
    </tr>
    <tr>
        <td colspan="3" style="text-align: center;">
            <form action="">
                <p>
                    Username:<br />
                    <input type="text" id="username" name="username" style="height:12%;width:25%"  /><br />
                    Password:<br />
                    <input type="password" id="password" name="password" style="height:12%;width:25%"  /></p>
                    <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
            </form>
        </td>
    </tr>
    <tr>
        <td colspan="3" style="width: 49%; height: 35px; text-align: center;">
            <input style="width:25%; position:relative; white-space:normal" type="submit" value="LOGIN" /></td>
    </tr>
    <tr>
        <td colspan="3" style="text-align: center; height: 100px;">
            &nbsp;</td>
    </tr>
    </tbody>
</table>
</form>
</html>
