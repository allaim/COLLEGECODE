package com.manager.command.impl;

import com.manager.command.GenericCommand;
import com.manager.dao.DaoStudent;
import com.manager.dao.impl.DaoStudentImpl;
import com.manager.domain.Course;
import com.manager.domain.Student;

import java.util.List;
import java.util.Scanner;

public class ShowStudentByNameCommand extends GenericCommand {

    private DaoStudent daoStudent;

    public ShowStudentByNameCommand(Scanner lineReader) {
        super(lineReader);
        daoStudent = new DaoStudentImpl();
    }

    public Object doCommand() {

        print("Name: ");
        String name = lineReader.nextLine();

        List<Student> students = daoStudent.searchByName(name);
        if(students.size() == 0) println("Student not Found");
        else {
            for(Student student : students){
                println(student.toString());
            }
        }

        return null;
    }
}
