<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Chess Game Login</title>
    
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
    <h1 align="center">登录页面</h1>
    <p>
    <div id="main" align="center">
    			<%
    			if(request.getParameterNames().hasMoreElements()){
    				
    			%>
    						<label>登录失败，账号或密码错误</label>
    			<% 
    				
    			}
    			%>
    			<form action="servlet/ChessServlet" method="post">
    						<p >
    							<label>用户名：</label>
    					            <input name="username" value=""/><br>
    						</p>
    			             <p>
    			             <label> 密码：</label>
    			               <input type="password" name="password" value=""/>
    			             </p>
    			             <p><p>
    			             <input type="submit" name="action" value="登录"/>
    			             <a href="Register.jsp">注册</a>
    			</form>
    			
    </div>
  </body>
</html>
