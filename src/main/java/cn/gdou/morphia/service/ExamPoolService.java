package cn.gdou.morphia.service;

import cn.gdou.morphia.model.ExamPool;
import cn.gdou.morphia.model.Question;

/**
 * @Auther: werson
 * @Date: 2018/10/29/029 16:37
 * @Description:    ExamPool对象业务逻辑层接口
 */
public interface ExamPoolService {
    /**
     *
     * @param type  考试科目类型
     * @param curPage   试题页码，即表示的是第几份试题
     * @return  获取一份试题
     */
    ExamPool getExamPool(int type, int curPage);

    /**
     *
     * @param exam  试题对象
     * @param index  问题的索引，即表示第几个问题，一共有20题
     * @return      一个问题对象
     */
    Question getQuestion(ExamPool exam, int index);

    /**
     *
     * @param question 一个问题
     * @return  一个问题的答案
     */
    String getAnswer(Question question);
}
