package cn.gdou;

import cn.gdou.springboot.SpringBootApp;
import cn.gdou.utils.AddAllExam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootApp.class)
public class RestTest {


    @Test
    public void getAssert() {
        AddAllExam.addAllExam();
    }

}
