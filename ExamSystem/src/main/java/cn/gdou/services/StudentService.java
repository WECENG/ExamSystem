package cn.gdou.services;

import cn.gdou.DAO.entity.Student;

public interface StudentService {
    Student findById(int id);
    Student findByAdmissionNum(String admissionNum);
    Student findByIdentifyNum(String identifyNum);
    int update(Student student);
    int deleteById(int id);
    Student save(Student student);
}
