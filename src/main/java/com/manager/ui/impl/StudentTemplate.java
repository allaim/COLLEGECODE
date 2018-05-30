package com.manager.ui.impl;

import com.manager.command.impl.ShowCourseEnrolledCommand;
import com.manager.command.impl.ShowStudentCourseInfoCommand;
import com.manager.domain.Student;
import com.manager.ui.GenericUITemplate;
import com.manager.ui.UITemplate;

import java.util.Scanner;

public class StudentTemplate extends GenericUITemplate implements UITemplate {

    private Student studentLogged;

    public StudentTemplate(Scanner lineReader,Student studentLogged){

        super(lineReader,"Student UI - "+studentLogged.getName());

        this.studentLogged = studentLogged;

        this.commands.put("SE",new ShowCourseEnrolledCommand(lineReader,studentLogged));
        this.commands.put("SI",new ShowStudentCourseInfoCommand(lineReader,studentLogged));
    }

    public void doOptions() {

        println("SE) Show Courses Enrolled");
        println("SI) Show Info About a Course");
        println("O) Options");
        println("Q) Quit");
    }

}
