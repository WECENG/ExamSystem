<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>GameReady page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <%
  		 String username=(String)request.getAttribute("username");
  		 String url="images/"+(int)(Math.random()*5+1)+".jpg";
 	%>
 
  <div align="center">
  <br>
   <img width="200" height="200"  src= <%=url %>>
   <br>
    <%=username %>
    <br>
    <a href="Match.jsp?username=<%=username%>&img=<%=url%>"><input type="button" value="匹配" style="width: 135px; height: 53px"></a>
  	<a href="AIchess.jsp?username=<%=username%>&img=<%=url%>&chessColor=<%="black"%>"><input type="button" value="人机" style="height: 53px; width: 135px; "></a>
  </div>
  </body>
</html>
