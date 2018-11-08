<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/9/009
  Time: 8:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Login</title>
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
<style type="text/css">
    .mes{
        border: aqua;
        color: pink;
    }
</style>
<style type="text/css">
#nav ul {
position: relative;
Width:1000px;
height:40px;
margin:30px auto;
padding:0;
list-style:none;
border-top:solid 5px #DAA520;
border-bottom:solid 5px #DAA520;
background:url(/img/lgbg.png)
}
#nav ul li{
width:100px;
float:left;
text-align:center;
font:16px/2.5 "microsoft yahei";
}
#nav ul li a {
color:#800080; text-decoration:none;
}
#nav ul li a:hover {
display:block;  background:#DC143C;
}
#sidebar {
float: left;
width: 30%;
height: 100%;
border: 5px solid #DAA520;
}
#content1 {
float: right;
width: 68%;
border: 5px solid #DAA520;
}
</style>
<body>
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
            <a href="${pageContext.request.contextPath}examPool/userDetail">查看个人信息</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/examPool/update">修改个人信息</a>
        </li>
    </ul>
</div>
<div class="register-container">
    <h1>驾照考试</h1>
    <%
        String message=(String)request.getAttribute("mes");
        request.setAttribute("message",message);
    %>
    <div class="mes">
        ${message}
    </div>
    <form method="post" action="${pageContext.request.contextPath}/examPool/login">
        <label>准考证号</label><input type="text" name="admissionNum" value="${cookie.admissionNum.value}"/><br>
        <label>用户名</label><input type="text" name="stuName" value="${cookie.username.value}"/><br>
        <label>密码</label><br><input type="password" name="password" value="${cookie.password.value}"/><br>
        <input type="submit" name="submit" value="登陆"/><br>
        <a href="${pageContext.request.contextPath}/examPool/password">密码找回</a>
        <span colspan="2"><input type="checkbox" name="isUseCheckBox" checked="checked" ><br>保存用户登录信息持续10天</span>
    </form>
</div>
</body>
</html>
