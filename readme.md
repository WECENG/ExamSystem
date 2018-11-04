简单在线驾照考试系统

集成环境：SpringBoot+Spring Data Rest+Spring Mail+Spring Asyn+
         Mongodb集群+Redis集群+Mysql



Mongodb集群 replica set  要使用Mongodb事务就需要进行Mongodb集群，
并且springboot需要升级到2.1.0版本

Redis cluster集群，master/slaver模式。使用jedis库

MongoDB数据库文件Exam




邮件发送功能：
启用Spring Mail功能。一旦用户修改了个人信息，
服务器将自动发送(异步发送)一封邮件到该用户的邮箱上。

