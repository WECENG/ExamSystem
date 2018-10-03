<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Register page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	 function show()
      { 
         J.dialog.get({id: 'haoyue_creat',title: '注册成功',width: 600,height:400, cover:true});
      }
	</script>
  </head>
  
  <body>
    <h1 align="center">注册页面</h1>
    <p>
    <div id="main" align="center">
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
    			             <a href="javascript:show()"><input type="submit" name="action"  value="注册"/></a>		 
    			</form>
    
    </div>
  </body>
</html>
