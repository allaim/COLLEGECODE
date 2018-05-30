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

public class InsertGradeCommand extends GenericCommand {

    private DaoCourse daoCourse;
    private DaoEnrolment daoEnrolment;
    private DaoStudent daoStudent;
    private Faculty faculty;

    public InsertGradeCommand(Scanner lineReader, Faculty faculty) {
        super(lineReader);
        daoCourse = new DaoCourseImpl();
        daoEnrolment = new DaoEnrolmentImpl();
        daoStudent = new DaoStudentImpl();
        this.faculty = faculty;
    }

    public Object doCommand() {

        print("Course Id: ");
        String courseId = lineReader.nextLine();

        print("Student Id: ");
        String studentId = lineReader.nextLine();

        Course course = null;
        Student student = null;

        try {
            course = daoCourse.searchById(Integer.parseInt(courseId));
            if (course == null) {
                println("Course not Found");
            }else{
                student = daoStudent.searchById(Integer.parseInt(studentId));

                if (course == null) {
                    println("Student not Found");
                }else {

                    Enrolment enroll = daoEnrolment.getByCourseAndStudent(student, course);

                    if(enroll== null){
                        println("Student not Enrolled in this Course");

                    }else{

                        if(course.getFaculty().getId()==faculty.getId()){

                            print("Assignment Grade: ("+enroll.getAssignment()+")");
                            String assignmentGrade = lineReader.nextLine();

                            print("Exam Grade: ("+enroll.getExame()+")");
                            String examGrade = lineReader.nextLine();

                            print("Attendance : ("+enroll.getAtendance()+")");
                            String attendance = lineReader.nextLine();

                            enroll.setAssignment(Double.parseDouble(assignmentGrade));
                            enroll.setExame(Double.parseDouble(examGrade));
                            enroll.setAtendance(Double.parseDouble(attendance));

                            daoEnrolment.updateGrade(enroll);
                            println("Updated");

                        }else{
                            println("Your are not the responsible for this course");
                        }

                    }

                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
