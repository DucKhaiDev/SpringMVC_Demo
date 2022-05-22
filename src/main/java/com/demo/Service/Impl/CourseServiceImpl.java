package com.demo.Service.Impl;

import java.util.List;

import com.demo.Dao.CourseDao;
import com.demo.Model.Course;
import com.demo.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseServiceImpl implements CourseService {
    // Dependency inject the CourseDAO
    @Autowired
    private CourseDao courseDao;

    @Override
    @Transactional
    public List<Course> getCourses() {
        return courseDao.getCourses();
    }

    @Override
    @Transactional
    public void saveCourse(Course theCourse) {
        courseDao.saveCourse(theCourse);

    }

    @Override
    @Transactional
    public Course getCourse(String theId) {
        return courseDao.getCourse(theId);
    }

    @Override
    @Transactional
    public void deleteCourse(String theId) {
        courseDao.deleteCourse(theId);
    }
}