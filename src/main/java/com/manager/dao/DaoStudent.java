package com.manager.dao;

import com.manager.domain.Course;
import com.manager.domain.Student;

import java.util.List;

public interface DaoStudent {

    public Student save(Student student);

    public Student searchById(Integer id);

    public List<Student> searchByName(String name);
    
    public List<Student> searchByCourseEnrolled(Course course);

}
