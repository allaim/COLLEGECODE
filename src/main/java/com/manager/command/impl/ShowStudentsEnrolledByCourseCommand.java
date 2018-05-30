package com.manager.command.impl;

import com.manager.command.GenericCommand;
import com.manager.dao.DaoCourse;
import com.manager.dao.DaoStudent;
import com.manager.dao.impl.DaoCourseImpl;
import com.manager.dao.impl.DaoStudentImpl;
import com.manager.domain.Course;
import com.manager.domain.Student;

import java.util.List;
import java.util.Scanner;

public class ShowStudentsEnrolledByCourseCommand extends GenericCommand {

    private DaoCourse daoCourse;
    private DaoStudent daoStudend;

    public ShowStudentsEnrolledByCourseCommand(Scanner lineReader) {
        super(lineReader);
        daoCourse = new DaoCourseImpl();
        daoStudend = new DaoStudentImpl();
    }

    public Object doCommand() {

        print("Course Id: ");
        String courseId = lineReader.nextLine();
        
        try {
            Course course = daoCourse.searchById(Integer.parseInt(courseId));
            if (course == null) {
                println("Course not Found");
            } else {
                
                List<Student> students = daoStudend.searchByCourseEnrolled(course);
                
                for(Student student : students){
                    System.out.println(student);
                }
                
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

}
