package com.manager.command.impl;

import com.manager.command.GenericCommand;
import com.manager.dao.DaoStudent;
import com.manager.dao.impl.DaoStudentImpl;
import com.manager.domain.Student;

import java.util.Scanner;

public class ShowStudentByIdCommand extends GenericCommand {

    private DaoStudent daoStudent;

    public ShowStudentByIdCommand(Scanner lineReader) {
        super(lineReader);
        daoStudent = new DaoStudentImpl();
    }

    public Object doCommand() {

        print("Id: ");
        String id = lineReader.nextLine();

        Student student = daoStudent.searchById(Integer.parseInt(id));
        if(student == null) println("Student not Found");
        else println(student.toString());

        return null;
    }
}
