<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/10/010
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Password</title>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/jquery-3.2.1.min.js"></script>
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

<script type="text/javascript">
    $(document).ready(function() {
        $("form").submit(function () {
            document.getElementById("submit").disabled=true;
        })
    });

</script>
<style type="text/css">
    .mes{
        border: pink;
        color:red;
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
<div class="register-container">
    <p2>找回密码</p2>
    <div class="mes">
        ${mes}
    </div>
    <form action="${pageContext.request.contextPath}/examPool/password" method="post">
        <label>请输入准考证号：</label><input type="text" name="admissionNum"><br>
        <label>请输入身份证号：</label><input type="text" name="identifyNum"><br>
        <input type="submit" id="submit" name="submit" value="提交"/>
    </form>
</div>
</body>
</html>
