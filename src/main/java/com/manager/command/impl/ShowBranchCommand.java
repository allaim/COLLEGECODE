package com.manager.command.impl;

import com.manager.dao.DaoBranch;
import com.manager.dao.impl.DaoBranchImpl;
import com.manager.command.GenericCommand;
import com.manager.domain.Branch;
import java.util.List;

import java.util.Scanner;

public class ShowBranchCommand extends GenericCommand {

    private DaoBranch daoBranch;

    public ShowBranchCommand(Scanner lineReader) {
        super(lineReader);
        daoBranch = new DaoBranchImpl();
    }

    public Object doCommand() {

        println("Showing All Branches ");

        List<Branch> braches = daoBranch.getAllBranches();
        for(Branch branch : braches){
            println(branch.toString());
        }

        return null;
    }
}
