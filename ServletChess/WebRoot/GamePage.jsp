<%@page import="javax.annotation.security.RunAs"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="javax.swing.JFrame"%>
<%@page import="cn.entity.ChessModel"%>
<%@page import="dao.ChessDAO"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Game starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<%
			String username=(String)request.getAttribute("username");
			String img=(String)request.getAttribute("img");
			String chessColor=(String)request.getAttribute("chessColor");
			String color=null;
			if(chessColor.equals("white")){
				color="白";
			}else{
				color="黑";
			}
	/* 		String isEnd=(String)application.getAttribute("isEnd"); */
	 %>

  </head>
  
      <style type="text/css">
        html,body
        {
            height: 100%;
            margin: 0px;
        }
        .left
        {
            float: left;
            height: 100%;
            width: 75%;
        }
        .right
        {
            float: right;
            height: 100%;
            width: 25%;
        }        
    </style>
  
 <body onload="startLoad()" style="padding:0px;margin:0px">
 
 	<%-- <form action="servlet/ChessServlet" method="post" id="passForm">		
 	<input id="attri1" type="hidden" name="color">
 	<input id="attri2" type="hidden" name="row">
 	<input id="attri3" type="hidden" name="col">
 	<input  id="attri4" type="hidden" name="action" >
 	<input type="hidden" name="username" value=<%=username %>>
 	<input type="hidden" name="img" value=<%=img %>>
 	<input type="hidden" name="chessColor" value=<%=chessColor %>>
 	</form> --%>
 
 	<div class="left">
  		<canvas width="1024" id="canvas" onmousedown="play(event)" height="768"></canvas>
 	</div>
 	<div  class="right">
 		<p align="center">游戏规则：<br> 黑棋先手，五子相连获胜
 		<br>
 		<br>
 		<img  src=<%=img %> width=200 height=200><br>
 		<%=username %>
 		<br>
 		执<%=color %>棋
 		</p>
 	</div>

 
</body>


<script type="text/javascript">

/**
初始化全局变量
**/
var canvas; //html5画布
var context;
var x;																										//鼠标点击横坐标
var y;																										//鼠标点击纵坐标			
var isEnd;																							//获取结束判断标志
var blackChess="";																			//获取黑棋位置字符串
var whiteChess="";																			//获取白棋位置字符串
var chessData = new Array(15); //二维数组存储棋盘落子信息,初始化数组chessData值为0即此处没有棋子，1为白棋，2为黑棋
for (var x = 0; x < 15; x++) {
  chessData[x] = new Array(15);
  for (var y = 0; y < 15; y++) {
    chessData[x][y] = 0;
  }
}

/**
每次打开网页加载棋盘和参与者信息
**/
function startLoad() {
  drawRect();
  setInterval(function(){refresh();iswin(); diliver("null",x,y);}, 1000);	//每格一秒请求一次服务器
}

/**
棋盘样式信息
 * */
function drawRect() {
  //创建棋盘背景
  canvas = document.getElementById("canvas");
  context = canvas.getContext("2d");
  context.fillStyle = '#FFF500';
  context.fillRect(75,0, 768, 768);
  //棋盘纵横线
  for (var i = 1; i < 16; i++) {
    context.beginPath();
    context.moveTo(40 * i+140, 20);
    context.lineTo(40 * i+140, 580);
    context.closePath();
    context.stroke();
    context.beginPath();
    context.moveTo(180, 40 * i-20);
    context.lineTo(740, 40 * i-20);
    context.closePath();
    context.stroke();
  }
}

/**
 * 禁止页面滚动事件
 * @return {[type]} [description]
 **/
var pageScroll = 0;
window.onscroll = function() {
	pageScroll++;
	scrollTo(0, 0);
	if (pageScroll > 100) { //每当玩家滚动页面滚动条100次提醒
	pageScroll = 0;
	}
}

/**
刷新棋盘中的棋子
*
**/
function refresh(){
	var blackstrs=new Array();
	var whitestrs=new Array();
	blackstrs=blackChess.split(",");
	whitestrs=whiteChess.split(",");
	for(var i=0;i<blackstrs.length-1;i=i+2){
		chess("black",blackstrs[i],blackstrs[i+1]);
	}
	for(var i=0;i<whitestrs.length-1;i=i+2){
		chess("white",whitestrs[i],whitestrs[i+1]);
	}
}


/**判断是否有一方获胜
*
*
**/
function iswin(){
			if(isEnd=="black"){
						console.log("isEnd="+isEnd);
						window.alert("黑方获胜！");
						window.location.href='Login.jsp';
			}
			if(isEnd=="white"){
						console.log("isEnd="+isEnd);
						window.alert("白方获胜！");
						window.location.href='Login.jsp';
			}
}

/**鼠标点击事件
 * @param  {[type]} e [description]
 * @return {[type]}   [description]
 **/
function play(e) { //鼠标点击时发生
  //var e=e||event;
  var px = e.clientX -160;			//获取鼠标点击的坐标
  var py = e.clientY ;
  x = parseInt(px / 40);
  y = parseInt(py / 40);
  if (px < 0 || py < 0 ) { //鼠标点击棋盘外的区域不响应
    return;						
  }
  if(x > 14 || y > 14 || chessData[x][y] != 0){					//存在棋子的坐标也不响应
  	return;
  }			
  var chesscolor="<%=chessColor%>";
  diliver(chesscolor,x,y);							//将js中的变量传递给Servlet
}


/**
绘制棋子
 * 
 **/
function chess(color, x, y) {
  context.fillStyle = color; //绘制棋
  context.beginPath();
  context.arc(x * 40 + 180, y * 40 + 20, 15, 0, Math.PI * 2, true);
  context.closePath();
  context.fill();
  if (color == "white") {
    chessData[x][y] = 1;
  } else {
    chessData[x][y] = 2;
  }
}

/**
*
	将js中的color,row,col变量传给Servlet
	ajax 无刷新异步请求
**/
function diliver(color,x,y){
	//创建对象
	var xhr=new XMLHttpRequest();
	//设置请求行
	xhr.open("post","servlet/ChessServlet",true);
	
	//设置请求头
	xhr.setRequestHeader("Content-type"," application/x-www-form-urlencoded");
	
		//回调函数
	xhr.onreadystatechange=function(){
				if(xhr.readyState==4&&xhr.status==200){
					var getserverdata=xhr.responseText;
					var strs=new Array();
					console.log(getserverdata);
					strs= getserverdata.split(".");
					blackChess=strs[0];
					whiteChess=strs[1];
					isEnd=strs[2];
				}
	}
	
	//设置请求主体，并发送
	xhr.send("color="+color+"&row="+x+"&col="+y+"&action=addchess");
	
}

</script>
</html>
