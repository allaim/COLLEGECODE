package com.manager.command.impl;

import com.manager.dao.DaoBranch;
import com.manager.dao.DaoCourse;
import com.manager.dao.DaoFaculty;
import com.manager.dao.impl.DaoBranchImpl;
import com.manager.dao.impl.DaoCourseImpl;
import com.manager.dao.impl.DaoFacultyImpl;
import com.manager.domain.Branch;
import com.manager.domain.Course;
import com.manager.command.GenericCommand;
import com.manager.domain.Faculty;

import java.util.Scanner;

public class CreateCourseCommand extends GenericCommand {

    private DaoBranch daoBranch;
    private DaoCourse daoCourse;
    private DaoFaculty daoFaculty;

    public CreateCourseCommand(Scanner lineReader) {

        super(lineReader);
        daoBranch = new DaoBranchImpl();
        daoCourse = new DaoCourseImpl();
        daoFaculty = new DaoFacultyImpl();
    }

    public Object doCommand() {

        println("Creating a new Courses ");

        print("Name: ");
        String name = lineReader.nextLine();

        print("Faculty id: ");
        String facultyIdString = lineReader.nextLine();

        Course course = new Course();

        try {
            Faculty faculty = daoFaculty.searchById(Integer.parseInt(facultyIdString));
            if (faculty == null) {
                println("Faculty not Found");
            } else {
                course.setFaculty(faculty);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        print("Branch Id: ");
        String branchIdString = lineReader.nextLine();

        try {
            Branch branch = daoBranch.searchById(Integer.parseInt(branchIdString));
            if (branch == null) {
                println("Branch not Found");
            } else {
                course.setBranch(branch);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        course.setName(name);

        daoCourse.save(course);
        println("Course Created");
        println(course.toString());

        return null;

    }

}
