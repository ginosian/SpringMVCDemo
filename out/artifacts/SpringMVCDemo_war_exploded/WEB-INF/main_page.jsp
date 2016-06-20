<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<table border="0" cellpadding="1" cellspacing="1" style="width: 100%; height: 100%">
    <tbody>
    <tr>
        <td colspan="3" style="text-align: center">
            <img alt="Insert image here" src="../resources/image/success_login.png" style="width: 100%; height: 100%;" /></td>
    </tr>
    <tr>
        <td style="text-align: center;">
            <a href="<c:url value="/admin"/>" style="width:25%;  font-size: 25px; position:relative; white-space:normal" type="button" >Admin Page</a>
            <p>
                &nbsp;</p>
            <p style="text-align: center;">
                <a href="<c:url value="/common"/>" style="width:25%;  font-size: 25px; position:relative; white-space:normal" type="button" >User Page</a>
                &nbsp;</p>
            <p style="text-align: center;">
                <a href="<c:url value="/j_spring_security_logout"/>" style="width:25%;  font-size: 25px; position:relative; white-space:normal" type="button" >Logout</a>
            <p>
                &nbsp;</p>
        </td>
    </tr>
    </tbody>
</table>
<p>
    &nbsp;</p>
</body>
</html>
