package com.manager.command.impl;

import com.manager.command.GenericCommand;
import com.manager.dao.DaoFaculty;
import com.manager.dao.impl.DaoFacultyImpl;
import com.manager.domain.Faculty;

import java.util.List;
import java.util.Scanner;

public class ShowFacultyByNameCommand extends GenericCommand {

    private DaoFaculty daoFaculty;

    public ShowFacultyByNameCommand(Scanner lineReader) {
        super(lineReader);
        daoFaculty = new DaoFacultyImpl();
    }

    public Object doCommand() {

        print("Name: ");
        String name = lineReader.nextLine();

        List<Faculty> Faculties = daoFaculty.searchByName(name);
        if(Faculties.size() == 0) println("Faculty not Found");
        else {
            for(Faculty faculty : Faculties){
                println(faculty.toString());
            }
        }

        return null;
    }
}
