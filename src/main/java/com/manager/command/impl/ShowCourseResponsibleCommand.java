package com.manager.command.impl;

import com.manager.command.GenericCommand;
import com.manager.dao.DaoCourse;
import com.manager.dao.impl.DaoCourseImpl;
import com.manager.domain.Course;
import com.manager.domain.Faculty;
import com.manager.domain.Student;

import java.util.List;
import java.util.Scanner;

public class ShowCourseResponsibleCommand extends GenericCommand {

    private DaoCourse daoCourse;
    private Faculty faculty;

    public ShowCourseResponsibleCommand(Scanner lineReader, Faculty faculty) {
        super(lineReader);
        daoCourse = new DaoCourseImpl();
        this.faculty = faculty;
    }

    public Object doCommand() {

        println("Showing All Courses you are Responsible ");

        List<Course> courses = daoCourse.cursesResponsible(faculty);
        for(Course course : courses){
            println(course.toString());
        }

        return null;
    }

}
