package com.manager.command.impl;

import com.manager.command.GenericCommand;
import com.manager.dao.DaoFaculty;
import com.manager.dao.DaoStudent;
import com.manager.dao.DaoTypeUser;
import com.manager.dao.DaoUser;
import com.manager.dao.impl.DaoFacultyImpl;
import com.manager.dao.impl.DaoStudentImpl;
import com.manager.dao.impl.DaoTypeUserImpl;
import com.manager.dao.impl.DaoUserImpl;
import com.manager.domain.Faculty;
import com.manager.domain.Student;
import com.manager.domain.User;

import java.util.Scanner;

public class CreateFacultyCommand extends GenericCommand {

    private DaoFaculty daoFaculty;
    private DaoUser daoUser;
    private DaoTypeUser daoTypeUser;

    public CreateFacultyCommand(Scanner lineReader) {

        super(lineReader);
        daoFaculty = new DaoFacultyImpl();
        daoUser = new DaoUserImpl();
        daoTypeUser = new DaoTypeUserImpl();
    }

    public Object doCommand() {

        println("Creating a new Faculty ");

        print("Name: ");
        String name = lineReader.nextLine();

        print("Address: ");
        String address = lineReader.nextLine();

        print("Phone Number: ");
        String phoneNumber = lineReader.nextLine();

        print("Login: ");
        String login = lineReader.nextLine();

        print("Password: ");
        String password = lineReader.nextLine();

        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setAddress(address);
        faculty.setPhoneNumber(phoneNumber);
        daoFaculty.save(faculty);

        User user = new User();
        user.setLogin(login);
        user.setName(name);
        user.setPassword(password);
        user.setFaculty(faculty);
        user.setTypeUser(daoTypeUser.searchById(2));
        daoUser.save(user);

        println("Faculty Created");
        println(faculty.toString());

        return null;

    }

}
