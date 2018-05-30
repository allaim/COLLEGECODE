package com.manager.dao.impl;

import com.manager.connector.JdbcConnector;
import com.manager.dao.DaoStudent;
import com.manager.domain.Branch;
import com.manager.domain.Course;
import com.manager.domain.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoStudentImpl extends JdbcConnector implements DaoStudent {

    public Student save(Student student) {
        initiateConnection();

        StringBuffer sql = new StringBuffer(" INSERT INTO student (name,address,phonenumber) VALUES (?,?,?)");

        try {

            preparedStatement = dbConnection.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getAddress());
            preparedStatement.setString(3,student.getPhoneNumber());

            // execute select SQL stetement
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            Integer insertKey = null;

            if(rs.next()) {
                insertKey = rs.getInt(1);
            }
            student.setId(insertKey);

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return student;
    }

    public Student searchById(Integer id) {
        Student student = null;

        initiateConnection();

        StringBuffer sql = new StringBuffer(" SELECT  ")
                .append(" s.id as id, ")
                .append(" s.name as name, ")
                .append(" s.address as address, ")
                .append(" s.phonenumber as phonenumber ")
                .append(" FROM student as s ")
                .append(" WHERE s.id = ? ");

        try {

            preparedStatement = dbConnection.prepareStatement(sql.toString());
            preparedStatement.setInt(1,id);

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                student = new Student();

                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAddress(rs.getString("address"));
                student.setPhoneNumber(rs.getString("phonenumber"));

            }

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return student;
    }

    public List<Student> searchByName(String name) {
        List<Student> students = new ArrayList<Student>();

        initiateConnection();

        StringBuffer sql = new StringBuffer(" SELECT  ")
                .append(" s.id as id, ")
                .append(" s.name as name, ")
                .append(" s.address as address, ")
                .append(" s.phonenumber as phonenumber ")
                .append(" FROM student as s ")
                .append(" WHERE lower(s.name) LIKE lower(?)");

        try {

            preparedStatement = dbConnection.prepareStatement(sql.toString());
            preparedStatement.setString(1,"%"+name+"%");


            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Student student = new Student();

                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAddress(rs.getString("address"));
                student.setPhoneNumber(rs.getString("phonenumber"));

                students.add(student);

            }

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return students;
    }

    public List<Student> searchByCourseEnrolled(Course course) {
        List<Student> students = new ArrayList<Student>();

        initiateConnection();

        StringBuffer sql = new StringBuffer(" SELECT  ")
                .append(" s.id as id, ")
                .append(" s.name as name, ")
                .append(" s.address as address, ")
                .append(" s.phonenumber as phonenumber ")
                .append(" FROM enrolment as e ")
                .append(" LEFT OUTER JOIN student as s ON (s.id = e.student_id)")
                .append(" WHERE e.course_id = ?");

        try {

            preparedStatement = dbConnection.prepareStatement(sql.toString());
            preparedStatement.setInt(1,course.getId());

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Student student = new Student();

                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAddress(rs.getString("address"));
                student.setPhoneNumber(rs.getString("phonenumber"));

                students.add(student);

            }

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return students;
    }

}
