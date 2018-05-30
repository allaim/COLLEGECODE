package com.manager.dao.impl;

import com.manager.connector.JdbcConnector;
import com.manager.dao.DaoCourse;
import com.manager.dao.DaoUser;
import com.manager.domain.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoCourseImpl extends JdbcConnector implements DaoCourse {

    public Course searchById(Integer id) {
        Course course = null;

        initiateConnection();

        StringBuffer sql = new StringBuffer(" SELECT  ")
                .append(" c.id as id, ")
                .append(" c.name as name, ")
                .append(" c.grade_visible as visible, ")
                .append(" b.id as bid, ")
                .append(" b.name as bname, ")
                .append(" b.address as baddress, ")
                .append(" f.id as fid, ")
                .append(" f.name as fname, ")
                .append(" f.address as faddress, ")
                .append(" f.phonenumber as fphonenumber ")
                .append(" FROM course as c ")
                .append(" LEFT OUTER JOIN faculty as f ON (f.id = c.faculty_id ) ")
                .append(" LEFT OUTER JOIN branch as b ON (b.id = c.branch_id)")
                .append(" WHERE c.id = ? ");

        try {

            preparedStatement = dbConnection.prepareStatement(sql.toString());
            preparedStatement.setInt(1,id);

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                course = new Course();

                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setGradeVisible(rs.getBoolean("visible"));
                
                Branch branch = new Branch();
                    branch.setId(rs.getInt("bid"));
                    branch.setName(rs.getString("bname"));
                    branch.setAddress(rs.getString("baddress"));
                    
                course.setBranch(branch);
                
                Faculty faculty = new Faculty();
                    faculty.setId(rs.getInt("fid"));
                    faculty.setName(rs.getString("fname"));
                    faculty.setAddress(rs.getString("faddress"));
                    faculty.setPhoneNumber(rs.getString("fphonenumber"));
                
                course.setFaculty(faculty);

            }

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return course;
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<Course>();

        initiateConnection();

        StringBuffer sql = new StringBuffer(" SELECT  ")
                .append(" c.id as id, ")
                .append(" c.name as name, ")
                .append(" c.grade_visible as visible, ")
                .append(" b.id as bid, ")
                .append(" b.name as bname, ")
                .append(" b.address as baddress, ")
                .append(" f.id as fid, ")
                .append(" f.address as faddress, ")
                .append(" f.phonenumber as fphonenumber, ")
                .append(" f.name as fname ")
                .append(" FROM course as c ")
                .append(" LEFT OUTER JOIN branch as b ON (b.id = c.branch_id)")
                .append(" LEFT OUTER JOIN faculty as f ON (f.id = c.faculty_id)");

        try {

            preparedStatement = dbConnection.prepareStatement(sql.toString());

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Course course = new Course();

                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setGradeVisible(rs.getBoolean("visible"));

                    Branch branch = new Branch();
                    branch.setId(rs.getInt("bid"));
                    branch.setName(rs.getString("bname"));
                    branch.setAddress(rs.getString("baddress"));
                course.setBranch(branch);
                    Faculty faculty = new Faculty();
                    faculty.setId(rs.getInt("fid"));
                    faculty.setAddress(rs.getString("faddress"));
                    faculty.setName(rs.getString("fname"));
                    faculty.setPhoneNumber(rs.getString("fphonenumber"));
                course.setFaculty(faculty);

                courses.add(course);

            }

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return courses;
    }

    public Course save(Course course) {
        initiateConnection();

        StringBuffer sql = new StringBuffer(" INSERT INTO course (name,faculty_id,branch_id,grade_visible) VALUES (?,?,?,false)");

        try {

            preparedStatement = dbConnection.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,course.getName());
            preparedStatement.setInt(2,course.getFaculty().getId());
            preparedStatement.setInt(3,course.getBranch().getId());

            // execute select SQL stetement
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            Integer insertKey = null;

            if(rs.next()) {
                insertKey = rs.getInt(1);
            }
            course.setId(insertKey);

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return course;
    }

    public List<Course> cursesEnrolled(Student student) {
        List<Course> courses = new ArrayList<Course>();

        initiateConnection();

        StringBuffer sql = new StringBuffer(" SELECT  ")
                .append(" c.id as id, ")
                .append(" c.name as name, ")
                .append(" c.grade_visible as visible, ")
                .append(" b.id as bid, ")
                .append(" b.name as bname, ")
                .append(" b.address as baddress, ")
                .append(" f.id as fid, ")
                .append(" f.address as faddress, ")
                .append(" f.phonenumber as fphonenumber, ")
                .append(" f.name as fname ")
                .append(" FROM enrolment as e ")
                .append(" LEFT OUTER JOIN course as c ON (c.id = e.course_id)")
                .append(" LEFT OUTER JOIN branch as b ON (b.id = c.branch_id)")
                .append(" LEFT OUTER JOIN faculty as f ON (f.id = c.faculty_id)")
                .append(" WHERE e.student_id = ?");

        try {

            preparedStatement = dbConnection.prepareStatement(sql.toString());
            preparedStatement.setInt(1,student.getId());

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Course course = new Course();

                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setGradeVisible(rs.getBoolean("visible"));

                Branch branch = new Branch();
                    branch.setId(rs.getInt("bid"));
                    branch.setName(rs.getString("bname"));
                    branch.setAddress(rs.getString("baddress"));
                course.setBranch(branch);
                Faculty faculty = new Faculty();
                    faculty.setId(rs.getInt("fid"));
                    faculty.setAddress(rs.getString("faddress"));
                    faculty.setName(rs.getString("fname"));
                    faculty.setPhoneNumber(rs.getString("fphonenumber"));
                course.setFaculty(faculty);

                courses.add(course);

            }

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return courses;
    }

    public List<Course> cursesResponsible(Faculty faculty) {
        List<Course> courses = new ArrayList<Course>();

        initiateConnection();

        StringBuffer sql = new StringBuffer(" SELECT  ")
                .append(" c.id as id, ")
                .append(" c.name as name, ")
                .append(" c.grade_visible as visible, ")
                .append(" b.id as bid, ")
                .append(" b.name as bname, ")
                .append(" b.address as baddress ")
                .append(" FROM course c ")
                .append(" LEFT OUTER JOIN branch as b ON (b.id = c.branch_id)")
                .append(" WHERE c.faculty_id = ?");

        try {

            preparedStatement = dbConnection.prepareStatement(sql.toString());
            preparedStatement.setInt(1,faculty.getId());

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Course course = new Course();

                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setGradeVisible(rs.getBoolean("visible"));

                Branch branch = new Branch();
                    branch.setId(rs.getInt("bid"));
                    branch.setName(rs.getString("bname"));
                    branch.setAddress(rs.getString("baddress"));
                course.setBranch(branch);
                course.setFaculty(faculty);

                courses.add(course);

            }

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return courses;
    }

    public void makePublic(Course course) {
        initiateConnection();

        StringBuffer sql = new StringBuffer(" UPDATE course SET grade_visible = true WHERE id = ? ");

        try {

            preparedStatement = dbConnection.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1,course.getId());

            // execute select SQL stetement
            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
    }

}
