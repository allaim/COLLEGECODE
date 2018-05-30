package com.manager.dao;

import com.manager.domain.Course;
import com.manager.domain.Faculty;
import com.manager.domain.Student;

import java.util.List;

public interface DaoCourse {

    public List<Course> getAllCourses();

    public Course searchById(Integer id);

    public Course save(Course course);

    public List<Course> cursesEnrolled(Student student);
    public List<Course> cursesResponsible(Faculty faculty);

    public void makePublic(Course course);

}

