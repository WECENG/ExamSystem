package cn.gdou;

import cn.gdou.DAO.entity.Student;
import cn.gdou.services.StudentService;
import cn.gdou.springboot.SpringBootApp;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootApp.class)
public class StudentServiceTest {
    @Autowired
    private StudentService service;

    @Test
    public void updateTest() {
        Student student = new Student();
        student.setAdmissionNum("123456");
        student.setIdentifyNum("654321");
        service.save(student);
        student.setIdentifyNum("789456");
        service.update(student);
    }



    @Test
    public void findStudentTest(){
        Student student= service.findByAdmissionNum("456456456456");
        Assert.assertNotNull(student);
    }
}
