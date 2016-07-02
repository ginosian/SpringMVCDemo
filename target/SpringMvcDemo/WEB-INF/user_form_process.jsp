<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Martha
  Date: 7/2/2016
  Time: 10:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<jsp:useBean id="formHandler" class="com.springmvc.demo.SupportingBeans.CreateUserForm" scope="request">
  <jsp:setProperty name="formHandler" property="*"/>
</jsp:useBean>
<c:set var="name" value="${name}"/>
<c:set var="username" value="${username}"/>
<c:set var="password" value="${password}"/>
<c:set var="role" value="${role}"/>
<%
    formHandler.setName(%>${name});<%
  if (formHandler.validate()) {
%>
<jsp:forward page="${root}/${home}/register"/>
<%
}  else {
%>
<jsp:forward page="${root}/${home}/create_user"/>
<%
  }
%>
