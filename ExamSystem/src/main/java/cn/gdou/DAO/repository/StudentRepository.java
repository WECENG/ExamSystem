package cn.gdou.DAO.repository;

import cn.gdou.DAO.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findById(int id);
    Student findByAdmissionNum(String admissionNum);
    Student findByIdentifyNum(String identifyNum);
    @Override
    <S extends Student> S save(S entity);
    @Modifying
    @Query("delete from Student s where s.id=?1")
    int deleteById(int integer);
    //第二个参数值为第一个参数对象的stuName属性值
    @Modifying
    @Query("update Student s set s.admissionNum=?1,s.stuName=?2," +
            "s.password=?3,s.mail=?4,s.phone=?5,s.identifyNum=?6 where s.id=?7")
    int update(String admissionNum,String stuName, String password,
               String mail,String phone,String identifyNum,Integer id);
}
