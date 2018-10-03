<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Match page</title>
    
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
  		 String username=(String)request.getParameter("username");
  		 String url=(String)request.getParameter("img");
  		 session.setAttribute("chessColor", "white");
 		session.setAttribute("username", username);
 		while(true){
 			if(session.getAttribute("username")!=username){
 				if(request.getSession(true)!=null){		//如果当前会话有效则让它失效
 					session.setMaxInactiveInterval(1);
 					if(session.getAttribute("chessColor").equals("white")){
 						session.setAttribute("chessColor", "black");
 						session.setAttribute("username", username);
 					}else{
 						Thread.sleep(100);
 						session.setAttribute("chessColor", "white");
 					}
 				}
 				request.setAttribute("username", username);
 				request.setAttribute("img", url);
 				request.setAttribute("chessColor", session.getAttribute("chessColor"));
				request.getRequestDispatcher("GamePage.jsp").forward(request, response);				
 				break;
 			}
 		}
%>
  
  </body>
</html>
