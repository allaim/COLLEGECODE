package com.manager.command.impl;

import com.manager.command.GenericCommand;
import com.manager.dao.DaoBranch;
import com.manager.dao.DaoStudent;
import com.manager.dao.DaoTypeUser;
import com.manager.dao.DaoUser;
import com.manager.dao.impl.DaoBranchImpl;
import com.manager.dao.impl.DaoStudentImpl;
import com.manager.dao.impl.DaoTypeUserImpl;
import com.manager.dao.impl.DaoUserImpl;
import com.manager.domain.Branch;
import com.manager.domain.Student;
import com.manager.domain.User;

import java.util.Scanner;

public class CreateStudentCommand extends GenericCommand {

    private DaoStudent daoStudent;
    private DaoUser daoUser;
    private DaoTypeUser daoTypeUser;

    public CreateStudentCommand(Scanner lineReader) {

        super(lineReader);
        daoStudent = new DaoStudentImpl();
        daoUser = new DaoUserImpl();
        daoTypeUser = new DaoTypeUserImpl();
    }

    public Object doCommand() {

        println("Creating a new Student ");

        print("Name: ");
        String name = lineReader.nextLine();

        print("Address: ");
        String teacherName = lineReader.nextLine();

        print("Phone Number: ");
        String phoneNumber = lineReader.nextLine();

        print("Login: ");
        String login = lineReader.nextLine();

        print("Password: ");
        String password = lineReader.nextLine();

        Student student = new Student();
        student.setName(name);
        student.setAddress(name);
        student.setPhoneNumber(phoneNumber);
        daoStudent.save(student);

        User user = new User();
        user.setLogin(login);
        user.setName(name);
        user.setPassword(password);
        user.setStudent(student);
        user.setTypeUser(daoTypeUser.searchById(4));
        daoUser.save(user);

        println("Student Created");
        println(student.toString());

        return null;

    }

}
