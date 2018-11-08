<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/13/013
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Score</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/art-template.js"></script>
    <link rel="stylesheet" href="/css/navigation.css">
</head>
<script type="test/html" id="template">
    {{each list}}
    <table>
        <tr>
            <td>
                <label>{{$index+1}}、{{$value.title}}</label><br>
                {{if $value.option1==""}} 正确{{/if}}<input type="radio" value="1" name="{{$index}}"/>{{$value.option1}}
                {{if $value.option2==""}} 错误{{/if}}<input type="radio" value="2" name="{{$index}}"/>{{$value.option2}}
                <input type="radio" {{if $value.option3==""}} hidden="hidden"{{/if}} value="3" name="{{$index}}"/>{{$value.option3}}
                <input type="radio" {{if $value.option4==""}} hidden="hidden"{{/if}} value="4" name="{{$index}}"/>{{$value.option4}}
                <br>{{if $value.picture!=""}}<img async src="{{$value.picture}}" name="图{{$index+1}}"/>{{/if}}<br>
                <p style="color:red">答案: {{$value.answer}}</p>
                <p style="color:deeppink">解析：{{$value.explainText}}</p>
            </td>
        </tr>
    </table>
    {{/each}}
</script>

<script type="text/javascript">
        function GetJsonData(type,curPage) {
            var json = {
                "type": type,
                "curPage": curPage,
            };
            return json;
        }
        function explainExam(type,curPage) {
            $.ajax({
                type: "POST",
                url: "explainExam",
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                data:JSON.stringify(GetJsonData(type,curPage)),
                success:function(message) {
                    //将返回的数据在页面显示
                    console.log(message);
                    var result=template("template",message);
                    var tag=document.getElementById("explainExam");
                    tag.innerHTML=result;
                },
                error: function (message) {
                    $("#request-process-patent").html("提交数据失败！");
                }
            });
        }

        function load() {
            var type="${type}";
            var curPage="${curPage}";
            explainExam(type,curPage);
        }
</script>

<body onload="load()">
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
</div >
    <label style="font-size: 3em;color: skyblue;">得分:${score}</label><br>
    <table class="table table-bordered">
        <caption>提交答案</caption>
        <thead>
        <tr>
            <th>题号</th>
            <c:forEach var="i" begin="1" end="20">
                <th>${i}</th>
            </c:forEach>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row">答案</th>
            <c:forEach var="i" items="${answer}">
                <td>${i}</td>
            </c:forEach>
        </tr>
        </tbody>
    </table>
    <div id="explainExam">
    </div>
</body>
</html>
