package cn.gdou.services.impl;

import cn.gdou.DAO.entity.Student;
import cn.gdou.DAO.repository.StudentRepository;
import cn.gdou.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
@Transactional(transactionManager = "jpaTransaction")
@CacheConfig(cacheNames = "StudentCaches")         //必须与CacheManager中配置的cache一致
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository repository;

    @Override
    @Cacheable(key = "#id",unless ="#result==null")
    public Student findById(int id) {
        return repository.findById(id);
    }

    @Override
    @Cacheable(key = "#admissionNum" , unless ="#result==null")
    public Student findByAdmissionNum(String admissionNum) {
        return repository.findByAdmissionNum(admissionNum);
    }

    @Override
    @Cacheable(key="#result.admissionNum",unless ="#result==null")
    public Student findByIdentifyNum(String identifyNum) {
        return findByIdentifyNum(identifyNum);
    }


    //更新学生信息
    @Override
    @CacheEvict(key = "#student.admissionNum")
    public int update(Student student) {
        /*密码加密*/
        String md5Password=DigestUtils.md5DigestAsHex(student.getPassword().getBytes());
        return repository.update(student.getAdmissionNum(),student.getStuName(),
                md5Password,student.getMail(),student.getPhone(),
                student.getIdentifyNum(),student.getId());
    }

    @Override
    @CacheEvict(key = "#id")
    public int deleteById(int id) {
        return repository.deleteById(id);
    }

    //保存学生信息
    @Override
    @CachePut(key = "#result.admissionNum" ,unless ="#result==null")
    public Student save(Student student) {
        /*密码加密*/
        String md5Password=DigestUtils.md5DigestAsHex(student.getPassword().getBytes());
        student.setPassword(md5Password);
        return repository.save(student);
    }
}
