<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>人机大战</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <%
  			String username=request.getParameter("username");
			String img=request.getParameter("img");
			String chessColor=request.getParameter("chessColor");
			String color=null;
			if(chessColor.equals("white")){
				color="白";
			}else{
				color="黑";
			}
   %>
   
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
    
    <script type="text/javascript">

/**
初始化全局变量
**/
var canvas; //html5画布
var context;
var str=new Array(2);			//存放AI要下棋子的坐标
var winner;									//胜利标志
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


/**鼠标点击事件
 * @param  {[type]} e [description]
 * @return {[type]}   [description]
 **/
function play(e) { //鼠标点击时发生
  //var e=e||event;
  var px = e.clientX -160;			//获取鼠标点击的坐标
  var py = e.clientY ;
  var x = parseInt(px / 40);
  var y = parseInt(py / 40);
  if (px < 0 || py < 0 ) { //鼠标点击棋盘外的区域不响应
    return;						
  }
  if(x > 14 || y > 14 || chessData[x][y] != 0){					//存在棋子的坐标也不响应
  	return;
  }
  if(winner=="白棋胜利!"||winner=="黑棋胜利!"){
  	return;
  }
  		chess("black",x,y);
  		isWin("black",x,y);
  		AIplay();
  		isWin("white",str[0],str[1]);
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
    console.log("电脑在" + x + "," + y + "画了个白棋");
    chessData[x][y] = 1;
  } else {
    console.log("电脑在" + x + "," + y + "画了个黑棋");
    chessData[x][y] = 2;
  }
}

/**五子棋AI
 *思路：对棋盘上的每一个空格进行估分，电脑优先在分值高的点落子
 * 棋型：
 * 〖五连〗只有五枚同色棋子在一条阳线或阴线上相邻成一排
 * 〖成五〗含有五枚同色棋子所形成的连，包括五连和长连。
 * 〖活四〗有两个点可以成五的四。
 * 〖冲四〗只有一个点可以成五的四。
 * 〖死四〗不能成五的四。
 * 〖三〗在一条阳线或阴线上连续相邻的5个点上只有三枚同色棋子的棋型。
 * 〖活三〗再走一着可以形成活四的三。
 * 〖连活三〗即：连的活三（同色棋子在一条阳线或阴线上相邻成一排的活三）。简称“连三”。
 * 〖跳活三〗中间隔有一个空点的活三。简称“跳三”。
 * 〖眠三〗再走一着可以形成冲四的三。
 * 〖死三〗不能成五的三。
 * 〖二〗在一条阳线或阴线上连续相邻的5个点上只有两枚同色棋子的棋型。
 * 〖活二〗再走一着可以形成活三的二。
 * 〖连活二〗即：连的活二（同色棋子在一条阳线或阴线上相邻成一排的活二）。简称“连二”。
 * 〖跳活二〗中间隔有一个空点的活二。简称“跳二”。
 * 〖大跳活二〗中间隔有两个空点的活二。简称“大跳二”。
 * 〖眠二〗再走一着可以形成眠三的二。
 * 〖死二〗不能成五的二。
 * 〖先手〗对方必须应答的着法，相对于先手而言，冲四称为“绝对先手”。
 * 〖三三〗一子落下同时形成两个活三。也称“双三”。
 * 〖四四〗一子落下同时形成两个冲四。也称“双四”。
 * 〖四三〗一子落下同时形成一个冲四和一个活三。
 * 分值表
 * 成5:100000分
 * 活4：10000分
 * 活3+冲4:5000分
 * 眠3+活2：2000分
 * 眠2+眠1:1分
 * 死棋即不能成5的是0分
 * @return {[type]} [description]
 */
function getPosition() {
    var a = new Array(2);
    var score = 0;
    for (var x = 0; x < 15; x++) {
        for (var y = 0; y < 15; y++) {
            if (chessData[x][y] == 0) {
                if (judge(x, y) > score) {
                    score = judge(x, y);
                    a[0] = x;
                    a[1] = y;
                }
            }
        }
    }
    return a;
}
 
function AIplay() {
     str = getPosition();
    // console.log("智能AI将在下面坐标下棋：" + str[0] + "," + str[1]);
    chess("white",str[0], str[1]);
}
 
function judge(x, y) {
    var a = parseInt(leftRight(x, y, 1)) + parseInt(topBottom(x, y, 1)) + parseInt(rightBottom(x, y, 1)) + parseInt(rightTop(x, y, 1))+100; //判断白棋走该位置的得分
    var b = parseInt(leftRight(x, y, 2)) + parseInt(topBottom(x, y, 2)) + parseInt(rightBottom(x, y, 2)) + parseInt(rightTop(x, y, 2)); //判断黑棋走该位置的得分
    var result = a + b;
    // console.log("我计算出了" + x + "," + y + "这个位置的得分为" + result);
    return result; //返回黑白棋下该位置的总和
}
 
function leftRight(x, y, num) {
    var death = 0; //0表示两边都没堵住,且可以成5，1表示一边堵住了，可以成5,2表示是死棋，不予考虑
    var live = 0;
    var count = 0;
    var arr = new Array(15);
    for (var i = 0; i< 15; i++) {
        arr[i] = new Array(15);
        for (var j = 0; j < 15; j++) {
            arr[i][j] = chessData[i][j];
        }
    }
    arr[x][y] = num;
    for (var i = x; i >= 0; i--) {
        if (arr[i][y] == num) {
            count++;
        } else if (arr[i][y] == 0) {
            live += 1; //空位标记
            i = -1;
        } else {
            death += 1; //颜色不同是标记一边被堵住
            i = -1;
        }
    }
    for (var i = x; i <= 14; i++) {
        if (arr[i][y] == num) {
            count++;
        } else if (arr[i][y] == 0) {
            live += 1; //空位标记
            i = 100;
        } else {
            death += 1;
            i = 100;
        }
    }
    count -= 1;
    // console.log(x + "," + y + "位置上的左右得分为" + model(count, death));
    return model(count, death);
}
 
function topBottom(x, y, num) {
    var death = 0; //0表示两边都没堵住,且可以成5，1表示一边堵住了，可以成5,2表示是死棋，不予考虑
    var live = 0;
    var count = 0;
    var arr = new Array(15);
    for (var i = 0; i< 15; i++) {
        arr[i] = new Array(15);
        for (var j = 0; j < 15; j++) {
            arr[i][j] = chessData[i][j];
        }
    }
    arr[x][y] = num;
    for (var i = y; i >= 0; i--) {
        if (arr[x][i] == num) {
            count++;
        } else if (arr[x][i] == 0) {
            live += 1; //空位标记
            i = -1;
        } else {
            death += 1;
            i = -1;
        }
    }
    for (var i = y; i <= 14; i++) {
        if (arr[x][i] == num) {
            count++;
        } else if (arr[x][i] == 0) {
            live += 1; //空位标记
            i = 100;
        } else {
            death += 1;
            i = 100;
        }
    }
    count -= 1;
    // console.log(x + "," + y + "位置上的上下斜得分为" + model(count, death));
    return model(count, death);
}
 
function rightBottom(x, y, num) {
    var death = 0; //0表示两边都没堵住,且可以成5，1表示一边堵住了，可以成5,2表示是死棋，不予考虑
    var live = 0;
    var count = 0;
    var arr = new Array(15);
    for (var i = 0; i< 15; i++) {
        arr[i] = new Array(15);
        for (var j = 0; j < 15; j++) {
            arr[i][j] = chessData[i][j];
        }
    }
    arr[x][y] = num;
    for (var i = x, j = y; i >= 0 && j >= 0;) {
        if (arr[i][j] == num) {
            count++;
        } else if (arr[i][j] == 0) {
            live += 1; //空位标记
            i = -1;
        } else {
            death += 1;
            i = -1;
        }
        i--;
        j--;
    }
    for (var i = x, j = y; i <= 14 && j <= 14;) {
        if (arr[i][j] == num) {
            count++;
        } else if (arr[i][j] == 0) {
            live += 1; //空位标记
            i = 100;
        } else {
            death += 1;
            i = 100;
        }
        i++;
        j++;
    }
    count -= 1;
    // console.log(x + "," + y + "位置上的右下斜得分为" + model(count, death));
    return model(count, death);
}
 
function rightTop(x, y, num) {
    var death = 0; //0表示两边都没堵住,且可以成5，1表示一边堵住了，可以成5,2表示是死棋，不予考虑
    var live = 0;
    var count = 0;
    var arr = new Array(15);
    for (var i = 0; i< 15; i++) {
        arr[i] = new Array(15);
        for (var j = 0; j < 15; j++) {
            arr[i][j] = chessData[i][j];
        }
    }
    arr[x][y] = num;
    for (var i = x, j = y; i >= 0 && j <= 14;) {
        if (arr[i][j] == num) {
            count++;
        } else if (arr[i][j] == 0) {
            live += 1; //空位标记
            i = -1;
        } else {
            death += 1;
            i = -1;
        }
        i--;
        j++;
    }
    for (var i = x, j = y; j >= 0 &&i <= 14 ;) {
        if (arr[i][j] == num) {
            count++;
        } else if (arr[i][j] == 0) {
            live += 1; //空位标记
            i = 100;
        } else {
            death += 1;
            i = 100;
        }
        i++;
        j--;
    }
    count -= 1;
    // console.log(x + "," + y + "位置上的右上斜得分为" + model(count, death));
    return model(count, death);
}
/**罗列相等效果的棋型(此处只考虑常见的情况，双成五，双活四等少概率事件不考虑)
 * 必胜棋：成五=活四==双活三=冲四+活三=双冲四
 * 
 * 
 * 
 */
function model(count, death) {
    // console.log("count" + count + "death" + death);
    var LEVEL_ONE = 0;//单子
    var LEVEL_TWO = 1;//眠2，眠1
    var LEVEL_THREE = 1500;//眠3，活2
    var LEVEL_FOER = 4000;//冲4，活3
    var LEVEL_FIVE = 10000;//活4
    var LEVEL_SIX = 100000;//成5
    if (count == 1 && death == 1) {
        return LEVEL_TWO; //眠1
    } else if (count == 2) {
        if (death == 0) {
            return LEVEL_THREE; //活2
        } else if (death == 1) {
            return LEVEL_TWO; //眠2
        } else {
            return LEVEL_ONE; //死棋
        }
    } else if (count == 3) {
        if (death == 0) {
            return LEVEL_FOER; //活3
        } else if (death == 1) {
            return LEVEL_THREE; //眠3
        } else {
            return LEVEL_ONE; //死棋
        }
    } else if (count == 4) {
        if (death == 0) {
            return LEVEL_FIVE; //活4
        } else if (death == 1) {
            return LEVEL_FOER; //冲4
        } else {
            return LEVEL_ONE; //死棋
        }
    } else if (count == 5) {
        return LEVEL_SIX; //成5
    }
    return LEVEL_ONE;
}

/**判断此局游戏是否已有结果
 * 每次落子判断游戏是否胜利
 *
 */
function isWin(color, x, y) {
  console.log("判断" + color + "(" + x + "," + y + ")是否胜利");
  var temp = 2; //默认为黑色
  if (color == "white") {
    temp = 1;
  } //白色
  console.log("temp=" + temp);
  lrCount(temp, x, y);
  tbCount(temp, x, y);
  rtCount(temp, x, y);
  rbCount(temp, x, y);
}
 
function lrCount(temp, x, y) {
  var line = new Array(4);
  var count = 0;
  for (var i = x; i >= 0; i--) {
    line[0] = i;
    line[1] = y;
    if (chessData[i][y] == temp) {
      ++count;
    } else {
      i = -1;
    }
  }
  for (var i = x; i <= 14; i++) {
    line[2] = i;
    line[3] = y;
    if (chessData[i][y] == temp) {
      ++count;
    } else {
      i = 100;
    }
  }
  success(line[0], line[1], line[2], line[3], temp, --count);
}
 
function tbCount(temp, x, y) {
  var line = new Array(4);
  var count = 0;
  for (var i = y; i >= 0; i--) {
    line[0] = x;
    line[1] = i;
    if (chessData[x][i] == temp) {
      ++count;
    } else {
      i = -1;
    }
  }
  for (var i = y; i <= 14; i++) {
    line[2] = x;
    line[3] = i;
    if (chessData[x][i] == temp) {
      ++count;
    } else {
      i = 100;
    }
  }
  success(line[0], line[1], line[2], line[3], temp, --count);
}
 
function rtCount(temp, x, y) {
  var line = new Array(4);
  var count = 0;
 
  for (var i = x, j = y; i <= 14 && j >= 0;) {
    line[0] = i;
    line[1] = j;
    if (chessData[i][j] == temp) {
      ++count;
    } else {
      i = 100;
    }
    i++;
    j--;
  }
  for (var i = x, j = y; i >= 0 && j <= 14;) {
    line[2] = i;
    line[3] = j;
    if (chessData[i][j] == temp) {
      ++count;
    } else {
      i = -1;
    }
    i--;
    j++;
  }
  success(line[0], line[1], line[2], line[3], temp, --count);
}
 
function rbCount(temp, x, y) {
  //右下斜判断
  var line = new Array(4);
  var count = 0;
 
  for (var i = x, j = y; i >= 0 && j >= 0;) {
    line[0] = i;
    line[1] = j;
    if (chessData[i][j] == temp) {
      ++count;
    } else {
      i = -1;
    }
    i--;
    j--;
  }
  for (var i = x, j = y; i <= 14 && j <= 14;) {
    line[2] = i;
    line[3] = j;
    if (chessData[i][j] == temp) {
      ++count;
    } else {
      i = 100;
    }
    i++;
    j++;
  }
  success(line[0], line[1], line[2], line[3], temp, --count);
}
/**判断是否胜利及胜利之后的操作
 * @param  {[type]} turn  [description]
 * @param  {[type]} count [description]
 * @return {[type]}       [description]
 */
function success(a, b, c, d, temp, count) {
  if (count == 5) { //因为落子点重复计算了一次
    winner = "黑棋胜利!";
    if (temp == 1) {
      winner = "白棋胜利!";
    }
    window.alert(winner+"返回登录页面!");
    document.location.href="Login.jsp";
  }
}

</script>
    
  
  <body onload="startLoad()" style="padding:0px;margin:0px">
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
</html>
