package cn.gdou.utils;

/**
 * @Auther: werson
 * @Date: 2018/10/29/029 18:12
 * @Description:    试题属性常量
 */
public enum  ExamType {
    EXAM_SIZE(10),  //科目一对应的序号
    QUESTION_SIZE(20);  //科目四对应的序号

    private final int value;

    // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
    ExamType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
