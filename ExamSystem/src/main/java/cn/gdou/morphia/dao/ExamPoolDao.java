package cn.gdou.morphia.dao;

import cn.gdou.morphia.model.ExamPool;

/**
 * @Auther: werson
 * @Date: 2018/10/29/029 16:04
 * @Description:  ExamPool对象的数据持久层接口
 */
public interface ExamPoolDao {
    /**
     *
     * @param type  考试科目类型
     * @param curPage   试题页码，即表示的是第几份试题
     * @return  返回一份试题，每份试题包含20个问题
     */
    ExamPool findExamPool(int type, int curPage);
}
