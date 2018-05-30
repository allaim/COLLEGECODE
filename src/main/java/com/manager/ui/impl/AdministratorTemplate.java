package com.manager.ui.impl;

import com.manager.command.impl.*;
import com.manager.ui.GenericUITemplate;
import com.manager.ui.UITemplate;

import java.util.Scanner;

public class AdministratorTemplate extends GenericUITemplate implements UITemplate {

    public AdministratorTemplate(Scanner lineReader){

        super(lineReader,"Administrator Manager UI");

        this.commands.put("BS",new ShowBranchCommand(lineReader));
        this.commands.put("BA",new CreateBranchCommand(lineReader));
        this.commands.put("CS",new ShowCourseCommand(lineReader));
        this.commands.put("CA",new CreateCourseCommand(lineReader));
        this.commands.put("FA",new CreateFacultyCommand(lineReader));
        this.commands.put("SFI",new ShowFacultyByIdCommand(lineReader));
        this.commands.put("SFN",new ShowFacultyByNameCommand(lineReader));
    }

    public void doOptions() {
        println("BS) Show All Branch");
        println("BA) Create Branch");
        println("CS) Show All Courses");
        println("CA) Create Course");
        println("FA) Create Faculty");
        println("SFI) Search Faculty By Id");
        println("SFN) Search Faculty By Name");
        println("O) Options");
        println("Q) Quit");
    }

}
