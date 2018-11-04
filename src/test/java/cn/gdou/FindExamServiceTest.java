package cn.gdou;

import cn.gdou.utils.SubjectType;
import cn.gdou.morphia.model.ExamPool;
import cn.gdou.morphia.model.Question;
import cn.gdou.morphia.service.ExamPoolService;
import cn.gdou.springboot.SpringBootApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootApp.class)
public class FindExamServiceTest {

    @Autowired
    ExamPoolService examPoolService;


    @Test
    public void findQuestion() {
        ExamPool examPool=examPoolService.getExamPool(SubjectType.SUBJECT_ONE.getValue(),1);
        System.out.println(examPool);

        Question question=examPoolService.getQuestion(examPool,0);
        System.out.println(question);

        String answer=examPoolService.getAnswer(question);
        System.out.println(answer);

    }
}
