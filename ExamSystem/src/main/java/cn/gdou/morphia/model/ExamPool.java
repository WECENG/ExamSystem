package cn.gdou.morphia.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import java.util.List;

@Entity
public class ExamPool {
    @Id
    private String id;
    @Property("total")
    private Integer type;
    private Integer curPage;
    private List<Question> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public List<Question> getList() {
        return list;
    }

    public void setList(List<Question> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ExamPool{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", curPage=" + curPage +
                ", list=" + list +
                '}';
    }
}
