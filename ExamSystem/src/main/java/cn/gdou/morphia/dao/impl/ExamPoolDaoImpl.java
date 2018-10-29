package cn.gdou.morphia.dao.impl;

import cn.gdou.morphia.dao.ExamPoolDao;
import cn.gdou.morphia.model.ExamPool;
import cn.gdou.morphia.dao.store.DatastoreConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Auther: werson
 * @Date: 2018/10/29/029 16:10
 * @Description:    ExamPool对象数据持久层实现类
 */
@Repository
public class ExamPoolDaoImpl implements ExamPoolDao {

    @Autowired
    DatastoreConfig store;

    @Override
    public ExamPool findExamPool(int type, int curPage) {
        return store.getDs().find(ExamPool.class)
                .filter("total =",type)
                .filter("curPage =",curPage).get();
    }
}
