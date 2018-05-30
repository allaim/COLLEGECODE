package com.manager.dao;

import com.manager.domain.Course;
import com.manager.domain.Enrolment;
import com.manager.domain.Student;

public interface DaoEnrolment {

    public Enrolment save(Enrolment enrolment);

    public void updateGrade(Enrolment enrolment);

    public Enrolment getByCourseAndStudent(Student student, Course course);

}
