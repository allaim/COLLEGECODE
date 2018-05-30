package com.manager.command.impl;

import com.manager.dao.DaoCourse;
import com.manager.dao.impl.DaoCourseImpl;
import com.manager.domain.Course;
import com.manager.command.GenericCommand;

import java.util.List;
import java.util.Scanner;

public class ShowCourseCommand extends GenericCommand {

    private DaoCourse daoCourse;

    public ShowCourseCommand(Scanner lineReader) {
        super(lineReader);
        daoCourse = new DaoCourseImpl();
    }

    public Object doCommand() {

        println("Showing All Courses ");

        List<Course> courses = daoCourse.getAllCourses();
        for(Course course : courses){
            println(course.toString());
        }

        return null;
    }

}
