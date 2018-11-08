<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Main Page</title>

    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/navigation.css">
    <link rel="stylesheet" href="/css/timeClick.css">
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/art-template.js"></script>
    <script src="/js/timeClick.js"></script>
</head>
<script type="text/javascript">
    function getExam(i) {
        $.ajax({
            type: "POST",
            url: "getExam",
            dataType: "json",
            contentType: "text/plain; charset=utf-8",
            data:"initExam",
            success:function(message) {
                //将返回的数据在页面显示
                console.log(message[i]);
                var result=template("template",message[i]);
                var tag=document.getElementById("examPage");
                tag.innerHTML=result;

                //开启开始答题按钮
                var beginButton=document.getElementById("beginButton");
                beginButton.disabled=false;
            },
            error: function (message) {
                $("#request-process-patent").html("提交数据失败！");
            }
        });
    }

    i=0;

    function updateExam(i) {
        $.ajax({
            type: "POST",
            url: "getExam",
            dataType: "json",
            contentType: "text/plain; charset=utf-8",
            data:"updateExam",
            success:function(message) {
                //将返回的数据在页面显示
                console.log(message);
                if(message[i].toString()==""){
                    return;
                }
                var li = document.createElement("li");
                var ul = document.getElementById("ulExam");
                ul.appendChild(li);
                var button = document.createElement("button");
                button.innerHTML = "科目四试题" + (i + 1);
                button.type = "button";
                button.id = "btn" + i;
                button.onclick = function (ev) {
                    var result = template("template", message[i]);
                    var tag = document.getElementById("examPage");
                    tag.innerHTML = result;
                    //开启开始答题按钮
                    var beginButton=document.getElementById("beginButton");
                    beginButton.disabled=false;
                }
                li.appendChild(button);
                var mes=document.getElementById("examPage");
                mes.innerHTML="试题已更新";
            },
            error: function (message) {
                $("#request-process-patent").html("提交数据失败！");
            }
        });
    }
</script>
<script id="template" type="test/html">
    <form action="${pageContext.request.contextPath}/examPool/correct" id="form" method="get">
    {{each list}}
    <table>
        <tr>
            <td>
                <label>{{$index+1}}、{{$value.title}}</label><br>

                {{if $value.option1==""}} 正确{{/if}}
                <label class="btn btn-primary">A
                <input type="radio" value="1" name="{{$index}}"/></label>
                {{$value.option1}}

                {{if $value.option2==""}} 错误{{/if}}
                <label class="btn btn-primary">B
                <input type="radio" value="2" name="{{$index}}"/></label>
                {{$value.option2}}

                {{if $value.option3!=""}}
                <label class="btn btn-primary">C
                <input type="radio" value="3" name="{{$index}}"/>
                </label>{{$value.option3}}
                {{/if}}

                {{if $value.option4!=""}}
                <label class="btn btn-primary">D
                <input type="radio" value="4" name="{{$index}}"/>
                </label>{{$value.option4}}
                {{/if}}

                <br>{{if $value.picture!=""}}<img async src="{{$value.picture}}" name="图{{$index+1}}"/>{{/if}}<br>
            </td>
        </tr>
    </table>
    {{/each}}
        <input type="hidden" name="type" id="type" value="{{$data.type}}"/>
        <input type="hidden" name="curPage" id="curPage" value="{{$data.curPage}}"/>
        <input  class="btn btn-primary btn-lg" type="submit" name="submit" value="提交"/>
     </form>
</script>

<body background="/images/1.jpg" style="background-size: 200%">
<h1 class="hfor">驾照考试</h1>
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
<!-- 左边栏 -->
<div id="sidebar">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <div class="dropdown">
        <button type="button" class="btn dropdown-toggle" id="dropdownMenu1"
                data-toggle="dropdown">
            试题
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" id="ulExam" role="menu" aria-labelledby="dropdownMenu1">
            <c:forEach var="i" begin="1" end="10">
            <li role="presentation">
                <button role="menuitem" id="exam1" tabindex="-1" onclick="getExam(${i-1})">科目一试题${i}</button>
            </li>
            </c:forEach>
        </ul>
        <button class="btn" type="button" onclick="updateExam(i++)" name="button">更新试题</button>
    </div>
    <div>
        <label style="font-size: 30px">考试时间40分钟</label><br><label style="font-size: 30px">共20道题</label>
        <div class="countdown">
            <p class="mtp"><span class="countdown_text">答题倒计时</span></p>
            <p class="line_height34"><span id="countdown_time"></span><span class="countdown_text">分钟</span></p>
            <button type="button" id="beginButton" disabled="disabled" name="TimeButton" onclick="beginClick()">开始答题计时</button>
        </div>
    </div>
</div>
<!-- 右边栏 -->
<div id="content1">
    <div id="examPage">
    </div>
    <div id="complete" class="pagination"></div>
</div>
</body>
</html>