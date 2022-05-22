package com.demo.Dao.Impl;

import java.util.List;

import com.demo.Dao.CourseDao;
import com.demo.Model.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDaoImpl implements CourseDao {
    // Dependency inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Course> getCourses() {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        // create a query select * form Student
        Query<Course> query = currentSession.createQuery("FROM Course", Course.class);
        // execute query and get result list
        // return the results
        return query.getResultList();
    }

    @Override
    public void saveCourse(Course theCourse) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        // save/upate the Course
        currentSession.saveOrUpdate(theCourse);
    }

    @Override
    public Course getCourse(String id) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        // now retrieve/read from database using the primary key
        // return the course
        return currentSession.get(Course.class, id);
    }

    @Override
    public void deleteCourse(String id) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        // delete student with primary key
        Query<Course> query = currentSession.createQuery("DELETE FROM Course WHERE course_id=:courseId", Course.class);
        // set parameter in query
        query.setParameter("courseId", id);
        // execute query
        query.executeUpdate();
    }
}