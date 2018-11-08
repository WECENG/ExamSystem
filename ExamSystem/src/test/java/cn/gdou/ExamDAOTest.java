package cn.gdou;

import cn.gdou.morphia.dao.ExamPoolDao;
import cn.gdou.morphia.model.ExamPool;
import cn.gdou.morphia.service.ExamPoolService;
import cn.gdou.springboot.SpringBootApp;
import cn.gdou.utils.SubjectType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootApp.class)
public class ExamDAOTest {
    @Autowired
    private ExamPoolDao examPoolDao;
    @Test
    public void serviceTest(){
        ExamPool exam=examPoolDao.findExamPool(SubjectType.SUBJECT_ONE.getValue(),1);
        System.out.println(exam);
    }
}
