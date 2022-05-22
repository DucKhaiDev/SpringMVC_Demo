package com.demo.Dao;

import com.demo.Model.Student;

import java.util.List;

public interface StudentDao {
    List<Student> getStudents();

    void saveStudent(Student student);

    Student getStudent(String id);

    void deleteStudent(String id);
}
