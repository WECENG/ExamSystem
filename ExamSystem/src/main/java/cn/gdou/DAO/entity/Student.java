package cn.gdou.DAO.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "tb_student")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(min = 12,max = 12,message = "{admissionNum.size}")
    private String admissionNum;
    @Size(min=2,max=30,message=" {stuName.size} ")
    private String stuName;
    private String password;
    private String mail;
    @Size(min=11,max = 11,message = "{phone.size}")
    private String phone;
    @Size(min = 18,max = 18,message = "{identifyNum.size}")
    private String identifyNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdmissionNum() {
        return admissionNum;
    }

    public void setAdmissionNum(String admissionNum) {
        this.admissionNum = admissionNum;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentifyNum() {
        return identifyNum;
    }

    public void setIdentifyNum(String identifyNum) {
        this.identifyNum = identifyNum;
    }


    public String userDetail() {
        return "准考证号："+admissionNum+"\n"+
                "姓名："+stuName+"\n"+
                "密码："+password+"\n"+
                "邮箱账号："+mail+"\n"+
                "手机号："+phone+"\n"+
                "身份证号："+identifyNum+"\n";

    }
}
