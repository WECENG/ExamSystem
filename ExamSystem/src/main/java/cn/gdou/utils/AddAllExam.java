package cn.gdou.utils;

public class AddAllExam {

    public static void addAllExam(){
        int[] type={1,4};   //科目类型
        int page=10;    //添加10套题
        for (int t=0;t<type.length;t++){
            for (int i=1;i<=page;i++){
                AddExamUtil.addExam(type[t],i);
            }
        }

    }
}
