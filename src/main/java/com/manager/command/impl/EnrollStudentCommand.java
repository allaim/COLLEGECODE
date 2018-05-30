package com.manager.command.impl;

import com.manager.command.GenericCommand;
import com.manager.dao.DaoCourse;
import com.manager.dao.DaoEnrolment;
import com.manager.dao.DaoStudent;
import com.manager.dao.impl.DaoCourseImpl;
import com.manager.dao.impl.DaoEnrolmentImpl;
import com.manager.dao.impl.DaoStudentImpl;
import com.manager.domain.Course;
import com.manager.domain.Enrolment;
import com.manager.domain.Faculty;
import com.manager.domain.Student;

import java.util.Scanner;

public class EnrollStudentCommand extends GenericCommand {

    private DaoEnrolment daoEnrolment;
    private DaoStudent daoStudent;
    private DaoCourse daoCourse;

    public EnrollStudentCommand(Scanner lineReader) {

        super(lineReader);
        daoCourse = new DaoCourseImpl();
        daoStudent = new DaoStudentImpl();
        daoEnrolment = new DaoEnrolmentImpl();
    }

    public Object doCommand() {

        Enrolment enrolment = new Enrolment();

        println("Student Id:");
        String studentId = lineReader.nextLine();

        try {
            Student student = daoStudent.searchById(Integer.parseInt(studentId));
            if (student == null) {
                println("Student not Found");
            } else {
                enrolment.setStudent(student);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        println("Course Id:");
        String courseId = lineReader.nextLine();

        try {
            Course course = daoCourse.searchById(Integer.parseInt(courseId));
            if (course == null) {
                println("Course not Found");
            } else {
                enrolment.setCourse(course);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        enrolment.setAtendance(100d);
        enrolment.setExame(0d);
        enrolment.setAssignment(0d);

        daoEnrolment.save(enrolment);

        println("Enrolment Done");
        println(enrolment.toString());

        return null;
    }
}
