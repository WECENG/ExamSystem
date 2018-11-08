package cn.gdou.utils;

/**
 * @Auther: werson
 * @Date: 2018/10/29/029 02:21
 * @Description:
 */
public enum SubjectType {
    SUBJECT_ONE(1311),  //科目一对应的序号
    SUBJECT_FOUR(836);  //科目四对应的序号

    private final int value;

    // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
    SubjectType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
