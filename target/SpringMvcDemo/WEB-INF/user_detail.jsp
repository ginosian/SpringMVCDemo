<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Martha
  Date: 6/23/2016
  Time: 12:56 PM
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
            <a class="active">User detail</a></li>
    </ul>
    <form action="${root}/${home}" method="get">
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
        <!--Root paths-->
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <c:set var="home" value="${home}"/>
        <c:set var="back" value="${back}"/>
        <input hidden="hidden" name="back" value="${back}"/>

        <!--Received data-->
        <c:set var="user" value="${user}"/>
        <c:set var="map" value="${map}"/>
        <c:set var="button_label" value="${button_label}"/>
        <c:set var="button_redirection_page" value="${button_redirection_page}"/>

        <!--Onclick directing resources to request on object detail pages-->
        <c:set var="task_detail_resource" value="${task_detail_resource}"/>

        <table align="center" style="width: 100%; height: 95%; background-color: rgb(235, 248, 252)">
            <tbody>
                <tr style="text-align: center; font-size:24px; height: 10%">
                    <td style="text-align: center; font-size:24px">
                        <span>User:</span><br />
                        <span style="font-size: 22px; width: 100%">${user.getName()}</span></td>
                </tr>
                <tr>
                    <td style="height: 100px; text-align: center; width: 60%;">
                        <c:set var="redirect_modify_task_to" value="${redirect_modify_task_to}"/>
                        <input name="map" hidden value="${map}">
                        <c:forEach items="${map}" var="entry">
                            <ul id="tasks_list_project" style="text-align: left; background-color: #d2d6cf">
                                <li style="font-size:28px">${entry.key}</li>
                                <p>
                                    &nbsp;</p>
                                <c:forEach items="${entry.value}" var="entry_value">
                                    <li style="text-align: left; font-size:22px"
                                        onclick="location.href='${root}/${home}/${task_detail_resource}/${entry_value.getId()}?back=${back}'">${entry_value.getStory()}</li>
                                    <p>
                                        &nbsp;</p>
                                </c:forEach>
                            </ul>
                            <p>
                                &nbsp;</p>
                        </c:forEach></td>
                </tr>
                <tr>
                    <td style="text-align: center; vertical-align: middle">
                        <input name="back" onclick="location.href='${root}/${button_redirection_page}'" style="width:20%;
                                position:relative; white-space:normal; background-color: #B0BEC5; font-size:24px" type="button" value="${button_label}"/></td>
                </tr>
            </tbody>
        </table>
    </form>
    <p>
        &nbsp;</p>
</body>
</html>