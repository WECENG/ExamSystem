package cn.gdou.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gdou.DAO.entity.Student;

public class LoginCookie {
	
		public static void addcookie(Student student, HttpServletResponse response, HttpServletRequest
		request) throws ServletException,IOException{
			String[] isusecheckbox=request.getParameterValues("isUseCheckBox");
			if(isusecheckbox!=null&&isusecheckbox.length>0){
						javax.servlet.http.Cookie admissionNumcookie=new javax.servlet.http.Cookie("admissionNum",student.getAdmissionNum());
						javax.servlet.http.Cookie usernamecookie=new javax.servlet.http.Cookie("username", student.getStuName());
						javax.servlet.http.Cookie passwordcookie=new javax.servlet.http.Cookie("password", student.getPassword());
						admissionNumcookie.setMaxAge(60*60*24*10);
						usernamecookie.setMaxAge(60*60*24*10);
						passwordcookie.setMaxAge(60*60*24*10);
						response.addCookie(admissionNumcookie);
						response.addCookie(usernamecookie);
						response.addCookie(passwordcookie);
			}else{
					javax.servlet.http.Cookie[]cookies=request.getCookies();
					if(cookies!=null&&cookies.length>0){
						for(javax.servlet.http.Cookie c:cookies){
							if(c.getName().equals("admissionNum")){
								c.setMaxAge(0);
								response.addCookie(c);
							}
							if(c.getName().equals("username")){
								c.setMaxAge(0);
								response.addCookie(c);
							}
							if(c.getName().equals("password")){
								c.setMaxAge(0);
								response.addCookie(c);
							}
						}
					}
			}
		}
}
