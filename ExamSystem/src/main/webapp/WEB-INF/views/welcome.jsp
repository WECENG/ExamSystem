<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>Welcome</title>
</head>
<style>
    body{
        background:url(/img/bg.jpg);
        width:100%;
        height:100%;
        background-size:50% 50%;
        position:absolute;
    }
    .wel{
        text-align: center;
        font-size:50px;
        color: red;
        vertical-align: middle;
        width: 30%;
        height: 30%;
        top: 50%;
        margin:200px auto 0;
        border: 5px solid #DAA520;
    }
</style>
<body>
    <div class="wel">
    <a href="${pageContext.request.contextPath}/examPool/register">注册</a><br>
    <a href="${pageContext.request.contextPath}/examPool/login">登录</a>
    </div>
</body>
</html>
