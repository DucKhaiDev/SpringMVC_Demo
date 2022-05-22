package com.demo.Service.Impl;

import com.demo.Dao.StudentDao;
import com.demo.Model.Student;
import com.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Student> getStudents() {
        return studentDao.getStudents();
    }

    @Override
    @Transactional
    public void saveStudent(Student student) {
        studentDao.saveStudent(student);
    }

    @Override
    public Student getStudent(String id) {
        return studentDao.getStudent(id);
    }

    @Override
    @Transactional
    public void deleteStudent(String id) {
        studentDao.deleteStudent(id);
    }
}
