package cn.gdou.morphia.service.impl;

import cn.gdou.morphia.dao.ExamPoolDao;
import cn.gdou.morphia.model.ExamPool;
import cn.gdou.morphia.model.Question;
import cn.gdou.morphia.service.ExamPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: werson
 * @Date: 2018/10/29/029 16:49
 * @Description:    ExamPool对象业务逻辑层实现类
 */
@Service
public class ExamPoolServiceImpl implements ExamPoolService {
    @Autowired
    ExamPoolDao examPoolDao;

    /**
     *
     * @param type  考试科目类型
     * @param curPage   试题页码，即表示的是第几份试题
     * @return  获取一份试题
     */
    @Override
    public ExamPool getExamPool(int type, int curPage) {
        return examPoolDao.findExamPool(type,curPage);
    }

    /**
     *
     * @param exam  试题对象
     * @param index  问题的索引，即表示第几个问题，一共有20题
     * @return      一个问题对象
     */
    @Override
    public Question getQuestion(ExamPool exam, int index) {
        return examPoolDao.findExamPool(
                exam.getType(),exam.getCurPage())
                .getList()
                .get(index);
    }

    /**
     *
     * @param question 一个问题
     * @return  一个问题的答案
     */
    @Override
    public String getAnswer(Question question) {
        return question.getAnswer();
    }
}
