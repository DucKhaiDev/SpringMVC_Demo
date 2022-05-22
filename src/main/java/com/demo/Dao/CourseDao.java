package com.demo.Dao;

import com.demo.Model.Course;

import java.util.List;

public interface CourseDao {
    List<Course> getCourses();

    void saveCourse(Course course);

    Course getCourse(String id);

    void deleteCourse(String id);
}
