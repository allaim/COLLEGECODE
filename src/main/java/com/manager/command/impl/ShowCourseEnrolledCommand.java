package com.manager.command.impl;

import com.manager.command.GenericCommand;
import com.manager.dao.DaoCourse;
import com.manager.dao.impl.DaoCourseImpl;
import com.manager.domain.Course;
import com.manager.domain.Student;

import java.util.List;
import java.util.Scanner;

public class ShowCourseEnrolledCommand extends GenericCommand {

    private DaoCourse daoCourse;
    private Student student;

    public ShowCourseEnrolledCommand(Scanner lineReader, Student student) {
        super(lineReader);
        daoCourse = new DaoCourseImpl();
        this.student = student;
    }

    public Object doCommand() {

        println("Showing All Courses Enrolled ");

        List<Course> courses = daoCourse.cursesEnrolled(student);
        for(Course course : courses){
            println(course.toString());
        }

        return null;
    }

}
