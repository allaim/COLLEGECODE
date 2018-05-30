package com.manager.command.impl;

import com.manager.command.GenericCommand;
import com.manager.dao.DaoFaculty;
import com.manager.dao.DaoStudent;
import com.manager.dao.impl.DaoFacultyImpl;
import com.manager.dao.impl.DaoStudentImpl;
import com.manager.domain.Faculty;
import com.manager.domain.Student;

import java.util.Scanner;

public class ShowFacultyByIdCommand extends GenericCommand {

    private DaoFaculty daoFaculty;

    public ShowFacultyByIdCommand(Scanner lineReader) {
        super(lineReader);
        daoFaculty = new DaoFacultyImpl();
    }

    public Object doCommand() {

        print("Id: ");
        String id = lineReader.nextLine();

        Faculty faculty = daoFaculty.searchById(Integer.parseInt(id));
        if(faculty == null) println("Faculty not Found");
        else println(faculty.toString());

        return null;
    }
}
