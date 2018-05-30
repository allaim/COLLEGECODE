package com.manager.command.impl;

import com.manager.dao.DaoFaculty;
import com.manager.dao.DaoStudent;
import com.manager.dao.DaoUser;
import com.manager.dao.impl.DaoFacultyImpl;
import com.manager.dao.impl.DaoStudentImpl;
import com.manager.dao.impl.DaoUserImpl;
import com.manager.domain.User;
import com.manager.command.GenericCommand;
import com.manager.ui.UITemplate;
import com.manager.ui.impl.AdministratorTemplate;
import com.manager.ui.impl.FacultyTemplate;
import com.manager.ui.impl.StudentManagerTemplate;
import com.manager.ui.impl.StudentTemplate;

import java.util.Scanner;

public class LoginCommand extends GenericCommand {

    private DaoUser daoUser;
    private DaoFaculty daoFaculty;
    private DaoStudent daoStudent;

    public LoginCommand(Scanner lineReader) {
        super(lineReader);
        daoUser = new DaoUserImpl();
        daoFaculty = new DaoFacultyImpl();
        daoStudent = new DaoStudentImpl();
    }

    public Object doCommand() {
        print("Login: ");
        String login = lineReader.nextLine();
        print("Password: ");
        String password = lineReader.nextLine();

        User user = daoUser.searchUserByLogin(login);
        if(user==null){
            println("Username and password not found in database");
        }else{
            if(user.getPassword().equals(password)){
                if(user.getTypeUser().getId()==1) return new AdministratorTemplate(lineReader);
                if(user.getTypeUser().getId()==2) return new FacultyTemplate(lineReader,daoFaculty.searchById(user.getIdExtra()));
                if(user.getTypeUser().getId()==3) return new StudentManagerTemplate(lineReader);
                if(user.getTypeUser().getId()==4) return new StudentTemplate(lineReader,daoStudent.searchById(user.getIdExtra()));
            }else{
                println("password incorrect");
            }
        }
        return null;
    }

}
