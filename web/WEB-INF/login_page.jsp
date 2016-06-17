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
<body>

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
                    Email address:<br />
                    <input id="login_input_field" name='j_username' style="height:12%;width:25%" type="email" /><br />
                    Entrance code:<br />
                    <input id="password_input_field" name='j_password' style="height:12%;width:25%" type="password" /></p>
            </form>
        </td>
    </tr>
    <tr>
        <td colspan="3" style="width: 49%; height: 35px; text-align: center;">
            <input id="submit" name="submit" style="width:25%; position:relative; white-space:normal" type="button" value="LOGIN" /></td>
    </tr>
    <tr>
        <td colspan="3" style="text-align: center; height: 100px;">
            &nbsp;</td>
    </tr>
    </tbody>
</table>
<p>
    &nbsp;</p>
</body>
</html>
