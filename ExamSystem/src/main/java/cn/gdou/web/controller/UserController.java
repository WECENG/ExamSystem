package cn.gdou.web.controller;

import cn.gdou.DAO.entity.Student;
import cn.gdou.mail.SendMailService;
import cn.gdou.model.LoginCookie;
import cn.gdou.model.Message;
import cn.gdou.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/examPool")
public class UserController {

    @Autowired
    private StudentService service;

    @Autowired
    private SendMailService mailService;

    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    public String welcome(){
        return "welcome";
    }


    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(Model model,Student student){
        return "register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String doRegister(Model model, @Valid Student student, Errors errors){
        if(errors.hasErrors()){
        return "register";
        }
        service.save(student);
        return "redirect:/examPool/login";
    }

    @RequestMapping(value = "/isAdmissionNum",method = RequestMethod.POST,
            consumes = "text/plain",produces = "application/json")
    public @ResponseBody String isAdmissionNum(
            @RequestBody String admissionNum){
        System.out.println(admissionNum);
        if(service.findByAdmissionNum(admissionNum)!=null){
            return "false";
        }else {
            return "true";
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Student student){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String doLogin(RedirectAttributes model, Student student,
        HttpServletResponse response, HttpServletRequest request, HttpSession session){
        Student temp=service.findByAdmissionNum(student.getAdmissionNum());
        /*加密密码*/
        String md5Password=DigestUtils.md5DigestAsHex(student.getPassword().getBytes());
        /*获取session中的验证码*/
        String vcode = (String) session.getAttribute("code");
        /*获取登录时的密码*/
        String user_vcode = request.getParameter("vcode");

        //判断验证码
        if(vcode == null || !vcode.equals(user_vcode)){
            //向request存入错误信息
            model.addFlashAttribute("mes", "验证码错误");
            //跳回login.jsp
            return "redirect:/examPool/login";
        }else if(temp==null){
            model.addFlashAttribute("mes","准考证号不存在。");
            return "redirect:/examPool/login";
        }else if(!student.getStuName().equals(temp.getStuName())||
                 !md5Password.equals(temp.getPassword())){
            model.addFlashAttribute("mes","姓名或密码有误。");
            return "redirect:/examPool/login";
        }else {
            model.addAttribute("mes","登陆成功!");
            try{
            //添加cookie或清除cookie
            LoginCookie.addcookie(student,response,request);
            //设置student session
            session.setAttribute("student",temp);
            }catch (Exception e){
            }
            return "main";
        }

    }

    @RequestMapping(value = "/main")
    public String showmain(Model model,HttpSession session){
        if(session.getAttribute("student")==null){
            model.addAttribute("mes","请先登陆");
            return "login";
        }
        return "main";
    }

    @RequestMapping(value = "/userDetail")
    public String userDetail(Model model,HttpSession session){
        if(session.getAttribute("student")==null){
            model.addAttribute("mes","请先登陆");
            return "login";
        }
        return "profile";
    }

    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public String update(Model model,HttpSession session,Student student){
        if(session.getAttribute("student")==null){
            model.addAttribute("mes","请先登陆");
            return "login";
        }
        return "update";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String doUpdate(RedirectAttributes model, HttpSession session, @Valid Student student, Errors errors){
        if(errors.hasErrors()){
            return "update";
        }
        Student sesStudent=(Student)session.getAttribute("student");
        if(student.getPassword().equals(sesStudent.getPassword())&&
                student.getMail().equals(sesStudent.getMail()) &&
                student.getPhone().equals(sesStudent.getPhone())){
            model.addFlashAttribute("mes","并未修改任何信息。");
            return "redirect:/examPool/update";
        }
        else {
            sesStudent.setPassword(student.getPassword());
            sesStudent.setMail(student.getMail());
            sesStudent.setPhone(student.getPhone());
            //修改session
            session.setAttribute("student",sesStudent);
            //修改数据库
            service.update(sesStudent);
            //发送邮件
            Message message=new Message();
            message.setDate(new Date().toString());
            message.setContent("修改后的用户信息:\n"+sesStudent.userDetail());
            mailService.sendSimpleEmail(sesStudent.getMail(),message);
        model.addFlashAttribute("mes","修改信息成功。");
        }
        return "redirect:/examPool/update";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "welcome";
    }

    @RequestMapping(value = "/password",method = RequestMethod.GET)
    public String password(Student student){
        return "password";
    }

    @RequestMapping(value = "/password",method = RequestMethod.POST)
    public String getPassword(RedirectAttributes model,Student student,HttpSession session){
        Student temp;
        if((temp=service.findByAdmissionNum(student.getAdmissionNum()))!=null){
            if(student.getIdentifyNum().equals(temp.getIdentifyNum())){
                model.addFlashAttribute("student",temp);
                session.setAttribute("student",temp);
                return "update";
            }
        }
        model.addAttribute("mes","信息有误");
        return "password";
    }


}
