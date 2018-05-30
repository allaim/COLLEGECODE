package com.manager.command.impl;

import com.manager.command.GenericCommand;
import com.manager.dao.DaoCourse;
import com.manager.dao.impl.DaoCourseImpl;
import com.manager.domain.Course;
import com.manager.domain.Faculty;

import java.util.List;
import java.util.Scanner;

public class MakeGradePublicCommand extends GenericCommand {

    private DaoCourse daoCourse;
    private Faculty faculty;

    public MakeGradePublicCommand(Scanner lineReader, Faculty faculty) {
        super(lineReader);
        daoCourse = new DaoCourseImpl();
        this.faculty = faculty;
    }

    public Object doCommand() {

        print("Course Id: ");
        String courseId = lineReader.nextLine();

        Course course = null;

        try {
            course = daoCourse.searchById(Integer.parseInt(courseId));
            if (course == null) {
                println("Course not Found");
            }else{

                if(course.getFaculty().getId()==faculty.getId()){

                    course.setGradeVisible(Boolean.TRUE);
                    daoCourse.makePublic(course);
                    println("Course is now Public");
                    println(course.toString());

                }else{
                    println("Your are not the responsible for this course");
                }

            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
