package com.manager.command.impl;

import com.manager.command.GenericCommand;
import com.manager.dao.DaoBranch;
import com.manager.dao.DaoCourse;
import com.manager.dao.impl.DaoBranchImpl;
import com.manager.dao.impl.DaoCourseImpl;
import com.manager.domain.Branch;
import com.manager.domain.Course;

import java.util.Scanner;

public class CreateBranchCommand extends GenericCommand {

    private DaoBranch daoBranch;

    public CreateBranchCommand(Scanner lineReader) {

        super(lineReader);
        daoBranch = new DaoBranchImpl();
    }

    public Object doCommand() {

        println("Creating a new Branch ");

        print("Name: ");
        String name = lineReader.nextLine();

        print("Address: ");
        String teacherName = lineReader.nextLine();

        Branch branch = new Branch();
        branch.setName(name);
        branch.setAddress(name);

        daoBranch.save(branch);
        println("Course Created");
        println(branch.toString());

        return null;

    }

}
