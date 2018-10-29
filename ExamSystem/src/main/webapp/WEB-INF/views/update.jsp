<%@ page import="cn.gdou.DAO.entity.Student" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/9/009
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User Detail</title>
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


    <%
        Student student=(Student)session.getAttribute("student");
    %>
    <style type="text/css">
        .mes{
            border: pink;
            color:red;
        }
        div.errors{
            border: pink;
            color:red;
        }
    </style>
<style>
    .wel{
        text-align: center;
        font-size:20px;
        color: red;
        vertical-align: middle;
        width: 30%;
        height: 20%;
        margin:200px auto 0;
        border: 5px solid #DAA520;
    }
</style>

    <script type="text/javascript">
        $(document).ready(function() {
            $("form").submit(function () {
                document.getElementById("submit").disabled=true;
            })
        });

    </script>
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
<div class="wel">
<div>
    <div>
        <h3>
            原个人信息
        </h3>
    </div>
    <div>
        <table>
            <tr>
                <th>准考证号</th>
                <td><%=student.getAdmissionNum()%></td>
            </tr>
            <tr>
                <th>原姓名</th>
                <td><%=student.getStuName()%></td>
            </tr>
            <tr>
                <th>原邮箱号</th>
                <td><%=student.getMail()%></td>
            </tr>
            <tr>
                <th>原电话号</th>
                <td><%=student.getPhone()%></td>
            </tr>
            <tr>
                <th>原身份证号</th>
                <td>${sessionScope.student.identifyNum}</td>
            </tr>
        </table>
    </div>
</div><br><br><br>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">
            修改个人信息
        </h3>
    </div>
    <div class="panel-body">
        <div class="mes">
            ${mes}
        </div>
        <form:form method="post" action="${pageContext.request.contextPath}/examPool/update" modelAttribute="student">
            <form:errors path="*" cssClass="errors" element="div"></form:errors>
        <table>
            <tr>
                <th>新密码</th><br>
                <td><form:password  path="password" placeholder="密码8-20位" /></td>
            </tr>
            <tr>
                <th>新邮箱号</th><br>
                <td><form:input  path="mail" placeholder="邮箱号" /></td>
            </tr>
            <tr>
                <th>新电话号码</th><br>
                <td><form:input  path="phone" placeholder="电话号码11位" /></td>
            </tr>
        </table>
            <input type="submit" name="submit"  id="submit" value="确定" />
        </form:form>
    </div>
</div>
</div>
</body>
</html>
