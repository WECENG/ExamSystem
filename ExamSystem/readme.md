简单在线驾照考试系统

集成环境：SpringBoot+Spring Data Rest+Spring Mail+Spring Asyn+
         Mongodb+Redis+Mysql

试题的API接口路径："http://api.jisuapi.com/driverexam/query"
    必要参数appkey，可以到该路径下申请appkey.
 
数据获取方式：
本项目通过RestTemplate类发送一个http请求获取api接口数据，并将其存进mongodb数据库.


前端请求数据方式：
页面通过ajax请求后台数据。
请求数据类型为json

后台返回数据方式:
后台通过@ResponseBody注解将javaBean对象转成json格式的数据返回前端。


邮件发送功能：
启用Spring Mail功能。一旦用户修改了个人信息，
服务器将自动发送(异步发送)一封邮件到该用户的邮箱上。

