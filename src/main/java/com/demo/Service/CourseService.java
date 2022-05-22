package com.demo.Service;

import com.demo.Model.Course;

import java.util.List;

public interface CourseService {
    List<Course> getCourses();

    void saveCourse(Course course);

    Course getCourse(String id);

    void deleteCourse(String id);
}
