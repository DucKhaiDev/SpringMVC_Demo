package com.demo.Dao.Impl;

import java.util.List;

import com.demo.Dao.StudentDao;
import com.demo.Model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl implements StudentDao {
    // Dependency inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Student> getStudents() {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        // create a query select * form Student
        Query<Student> query = currentSession.createQuery("from Student", Student.class);
        // execute query and get result list
        // return the results
        return query.getResultList();
    }

    @Override
    public void saveStudent(Student theStudent) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        // save/upate the Students
        currentSession.saveOrUpdate(theStudent);
    }

    @Override
    public Student getStudent(String id) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        // now retrieve/read from database using the primary key
        // return the student
        return currentSession.get(Student.class, id);
    }

    @Override
    public void deleteStudent(String id) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        // delete student with primary key
        Query<Student> query = currentSession.createQuery("DELETE FROM Student WHERE student_id=:studentId", Student.class);
        query.setParameter("studentId", id);
        // execute query
        query.executeUpdate();
    }
}