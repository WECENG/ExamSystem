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

@Service
@Transactional
@CacheConfig(cacheNames = "StudentCache")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository repository;

    @Override
    @Cacheable(key = "#id")
    public Student findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Student findByAdmissionNum(String admissionNum) {
        return repository.findByAdmissionNum(admissionNum);
    }

    @Override
    @Cacheable(key="#identifyNum")
    public Student findByIdentifyNum(String identifyNum) {
        return findByIdentifyNum(identifyNum);
    }


    //更新学生信息
    @Override
    @CacheEvict(key = "#student.admissionNum")
    public int update(Student student) {
        return repository.update(student.getAdmissionNum(),student.getStuName(),
                student.getPassword(),student.getMail(),student.getPhone(),
                student.getIdentifyNum(),student.getId());
    }

    @Override
    @CacheEvict(key = "#student.admissionNum")
    public int deleteById(int id) {
        return repository.deleteById(id);
    }

    //保存学生信息
    @Override
    @CachePut(key = "#result.id")
    public Student save(Student student) {
        return repository.save(student);
    }
}
