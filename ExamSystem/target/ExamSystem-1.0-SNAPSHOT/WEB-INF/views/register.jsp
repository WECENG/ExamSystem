<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/9/009
  Time: 8:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生注册</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
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
    div.errors{
        background-color: red;
        border: 2px solid cyan;
    }
</style>
<script type="text/javascript">
    function isAdmissionNum(value) {
        if(value.length!=12){
            document.getElementById("isTrue").innerHTML="准考证号不合法";
            document.getElementById("submit").disabled=true;
            return;
        }
        $.ajax({
            type: "POST",
            url: "isAdmissionNum",
            dataType: "json",
            contentType: "text/plain; charset=utf-8",
            data:value,
            success:function(message) {
                //将返回的数据在页面显示
                var isTrue= window.document.getElementsByName("isTrue");
                console.log(message);
                if(message==true){
                    isTrue="准考证号可用";
                    document.getElementById("submit").disabled=false;
                }else {
                    isTrue="准考证号已存在";
                    document.getElementById("submit").disabled=true;
                }
                document.getElementById("isTrue").innerHTML=isTrue;

            },
            error: function (message) {
                $("#request-process-patent").html("提交数据失败！");
            }
        });
    }
    $(document).ready(function() {
        $("form").submit(function () {
            document.getElementById("submit").disabled=true;
        })
    });
</script>
<body>
<div class="register-container">
    <h1>驾照考试</h1>
    <div id="isTrue"></div>
    <form:form method="post" modelAttribute="student" action="${pageContext.request.contextPath}/examPool/register" id="registerForm" >
        <form:errors path="*" cssClass="errors" element="div"></form:errors>
        <label>准考证号: </label>
        <form:input  name="admissionNum" path="admissionNum"
                     placeholder="准考证号12位"  onblur="isAdmissionNum(this.value)" value=""/><br>
        <label>用户名:
        <form:input  path="stuName" placeholder="姓名" value=""/></label><br>
        <label>密码:
        <form:password  path="password" placeholder="密码8-20位" value=""/></label><br>
        <label>邮箱:
        <form:input  path="mail" placeholder="邮箱号" value=""/></label><br>
        <label>电话:
        <form:input  path="phone" placeholder="电话号码11位" value=""/></label><br>
        <label>身份证号:
        <form:input  path="identifyNum" placeholder="身份证号18位" value=""/></label><br>
        <input type="submit" id="submit" name="submit" value="提交"/>
    </form:form>
</div>

</body>
</html>
