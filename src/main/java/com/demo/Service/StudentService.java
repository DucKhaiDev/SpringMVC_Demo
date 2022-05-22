package com.demo.Service;

import com.demo.Model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents();

    void saveStudent(Student student);

    Student getStudent(String id);

    void deleteStudent(String id);
}
