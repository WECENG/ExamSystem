package cn.gdou.web.controller;

import cn.gdou.utils.ExamType;
import cn.gdou.utils.SubjectType;
import cn.gdou.morphia.model.ExamPool;
import cn.gdou.morphia.model.Question;
import cn.gdou.morphia.service.ExamPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/examPool")
public class ExamController {
    @Autowired
    private ExamPoolService service;

    @RequestMapping(value = "/getExam",method = RequestMethod.POST,
    produces = "application/json",consumes = "text/plain")
    public @ResponseBody
    List<ExamPool> initExam(@RequestBody String mes){
        if(mes.equals("initExam")){
            List<ExamPool> list=new ArrayList<>();
            for(int i=1;i<10;i++){
            list.add(service.getExamPool(SubjectType.SUBJECT_ONE.getValue(),i));
            }
            return list;
        }

        if (mes.equals("updateExam")){
            List<ExamPool> list=new ArrayList<>();
            for(int i=1;i<10;i++){
                list.add(service.getExamPool(SubjectType.SUBJECT_FOUR.getValue(),i));
            }
            return list;
        }
        return null;
    }

    @RequestMapping(value = "/correct",method = RequestMethod.GET)
    public String correct(Model model, HttpServletRequest request){
        int type=Integer.parseInt(request.getParameter("type"));
        int curPage=Integer.parseInt(request.getParameter("curPage"));
        ExamPool examPool =service.getExamPool(type,curPage);
//        int questionCount=service.countQuestion(examPool);
        int rightCount=0;
//        System.out.println("题数"+questionCount);
        for(int i = 0; i<ExamType.QUESTION_SIZE.getValue(); i++){
            Question question=service.getQuestion(examPool,i);
            String answer=service.getAnswer(question);
            //前端以问题的序号作为参数将答案传到后台
            String choice=request.getParameter(String.valueOf(i));
            if(answer!=null&&choice!=null&&answer.equals(choice)){
                rightCount++;
            }
        }
        int score=rightCount*5;         //答对一道题得5分
        model.addAttribute("score",score);
        model.addAttribute("type",type);
        model.addAttribute("curPage",curPage);
        return "score";
    }

    @RequestMapping(value = "/explainExam",method = RequestMethod.POST,
    produces = "application/json",consumes = "application/json")
    public @ResponseBody
    ExamPool explain(@RequestBody ExamPool examPool){
        return service.getExamPool(
                examPool.getType(), examPool.getCurPage());
    }
}
