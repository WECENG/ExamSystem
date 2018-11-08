<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/10/010
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get Your Passord</title>
</head>
<link rel="stylesheet" href="/css/navigation.css">


<body  background="/images/1.jpg" style="background-size: 200%">
<!-- 导航栏 -->
<div id="nav">
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/examPool/login">注销</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/examPool/logout">退出</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/examPool/main">返回主页</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/examPool/userDetail">查看个人信息</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/examPool/update">修改个人信息</a>
        </li>
    </ul>
</div>
    <label>你的密码是：${requestScope.student.password}</label>
</body>
</html>
