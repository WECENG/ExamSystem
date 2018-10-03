<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>DoRegister  page</title>
    
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
    <%!
    		String doRegister(HttpServletRequest request){
    			String isRegister=request.getAttribute("isRegister").toString();
    			if(isRegister=="false"){
    					String message="注册失败！用户名已存在";
    					return message;
    			}
    			if(isRegister=="true"){
    					String message="注册成功！";
    					return message;
    			}
    			return null;
    		}
     %>
     <div	align="center">
     	<h1><%=doRegister(request) %></h1><br>
     	<a href="Login.jsp">返回登录页面</a>
     </div>
  </body>
</html>
