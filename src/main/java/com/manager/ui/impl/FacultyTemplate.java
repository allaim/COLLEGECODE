package com.manager.ui.impl;

import com.manager.command.impl.InsertGradeCommand;
import com.manager.command.impl.MakeGradePublicCommand;
import com.manager.command.impl.ShowCourseResponsibleCommand;
import com.manager.command.impl.ShowStudentsEnrolledByCourseCommand;
import com.manager.domain.Faculty;
import com.manager.ui.GenericUITemplate;
import com.manager.ui.UITemplate;

import java.util.Scanner;

public class FacultyTemplate extends GenericUITemplate implements UITemplate {

    private Faculty facultyLogged;

    public FacultyTemplate(Scanner lineReader,Faculty facultyLogged){

        super(lineReader,"Faculty UI - "+facultyLogged.getName());

        this.facultyLogged = facultyLogged;

        this.commands.put("IR",new InsertGradeCommand(lineReader,facultyLogged));
        this.commands.put("SR",new ShowCourseResponsibleCommand(lineReader,facultyLogged));
        this.commands.put("SSE",new ShowStudentsEnrolledByCourseCommand(lineReader));
        this.commands.put("MP",new MakeGradePublicCommand(lineReader,facultyLogged));
    }

    public void doOptions() {

        println("SR) Show Courses you are Responsible");
        println("IR) Insert Results");
        println("SSE) Show Students Enrolled in a Course");
        println("MP) Make Grades Public");
        println("O) Options");
        println("Q) Quit");
    }

}
