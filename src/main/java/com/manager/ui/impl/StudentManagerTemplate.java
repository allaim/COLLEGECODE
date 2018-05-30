package com.manager.ui.impl;

import com.manager.command.impl.*;
import com.manager.ui.GenericUITemplate;
import com.manager.ui.UITemplate;

import java.util.Scanner;

public class StudentManagerTemplate extends GenericUITemplate implements UITemplate {

    public StudentManagerTemplate(Scanner lineReader){

        super(lineReader,"Student Manager UI");

        this.commands.put("SA",new CreateStudentCommand(lineReader));
        this.commands.put("SSI",new ShowStudentByIdCommand(lineReader));
        this.commands.put("SSN",new ShowStudentByNameCommand(lineReader));
        this.commands.put("CS",new ShowCourseCommand(lineReader));
        this.commands.put("ES",new EnrollStudentCommand(lineReader));
        this.commands.put("SSE",new ShowStudentsEnrolledByCourseCommand(lineReader));
        
    }

    public void doOptions() {

        println("SA) Create Student");
        println("SSI) Search Student By Id");
        println("SSN) Search Student By Name");
        println("CS) Show All Courses");
        println("ES) Enrol Student in a Course");
        println("SSE) Show Students Enrolled in a Course");
        println("O) Options");
        println("Q) Quit");
    }

}
