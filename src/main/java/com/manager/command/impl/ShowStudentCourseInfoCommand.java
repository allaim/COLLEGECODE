package com.manager.command.impl;

import com.manager.command.GenericCommand;
import com.manager.dao.DaoCourse;
import com.manager.dao.DaoEnrolment;
import com.manager.dao.impl.DaoCourseImpl;
import com.manager.dao.impl.DaoEnrolmentImpl;
import com.manager.domain.Course;
import com.manager.domain.Enrolment;
import com.manager.domain.Student;

import java.util.Scanner;

public class ShowStudentCourseInfoCommand extends GenericCommand {

    private DaoCourse daoCourse;
    private DaoEnrolment daoEnrolment;
    private Student student;

    public ShowStudentCourseInfoCommand(Scanner lineReader, Student student) {
        super(lineReader);
        daoCourse = new DaoCourseImpl();
        daoEnrolment = new DaoEnrolmentImpl();
        this.student = student;
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

                Enrolment enroll = daoEnrolment.getByCourseAndStudent(student,course);

                if(enroll == null){
                    println("You are not enrolled in this course");
                }else{
                    if(course.getGradeVisible())println(enroll.toString());
                    else println("Your grades is not avaiable yet");
                }

            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
