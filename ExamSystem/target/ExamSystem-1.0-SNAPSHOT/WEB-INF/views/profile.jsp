<%@ page import="cn.gdou.DAO.entity.Student" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/9/009
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<link rel="stylesheet" href="/css/registerstyle.css" />
<!-- 新添加js脚本 -->
<script src="/js/jquery.min.js"></script>
<%--<script src="/js/common.js"></script>--%>
<!--背景图片自动更换-->
<script src="/js/supersized.3.2.7.min.js"></script>
<script src="/js/supersized-init.js"></script>
<!--表单验证-->
<%--<script src="/js/jquery.validate.min.js?var1.14.0"></script>--%>
<link rel="stylesheet" href="/css/navigation.css">

<style>
    .wel{
        text-align: center;
        font-size:20px;
        color: red;
        vertical-align: middle;
        width: 30%;
        height: 30%;
        top: 50%;
        margin:200px auto 0;
        border: 5px solid #DAA520;
    }
</style>
<body background="/images/1.jpg" style="background-size: 200%">
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
        <%
            Student student=(Student) session.getAttribute("student");
        %>
<div class="wel">
        <table>
            <tr>
                <th>姓名</th>
                <td><%=student.getStuName()%></td>
            </tr>
            <tr>
                <th>准考证号</th>
                <td><%=student.getAdmissionNum()%></td>
            </tr>
            <tr>
                <th>邮箱</th>
                <td><%=student.getMail()%></td>
            </tr>
            <tr>
                <th>电话号码</th>
                <td><%=student.getPhone()%></td>
            </tr>
            <tr>
                <th>身份证号码</th>
                <td><%=student.getIdentifyNum()%></td>
            </tr>
        </table>
    </div>
</body>
</html>
