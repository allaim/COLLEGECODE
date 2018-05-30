package com.manager.dao.impl;

import com.manager.connector.JdbcConnector;
import com.manager.dao.DaoEnrolment;
import com.manager.domain.Course;
import com.manager.domain.Enrolment;
import com.manager.domain.Faculty;
import com.manager.domain.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoEnrolmentImpl extends JdbcConnector implements DaoEnrolment {

    public Enrolment save(Enrolment enrolment) {
        initiateConnection();

        StringBuffer sql = new StringBuffer(" INSERT INTO enrolment (student_id,course_id,atendance,assignment,exame) VALUES (?,?,?,?,?)");

        try {

            preparedStatement = dbConnection.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1,enrolment.getStudent().getId());
            preparedStatement.setInt(2,enrolment.getCourse().getId());
            preparedStatement.setDouble(3,enrolment.getAtendance());
            preparedStatement.setDouble(4,enrolment.getAssignment());
            preparedStatement.setDouble(5,enrolment.getExame());

            // execute select SQL stetement
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            Integer insertKey = null;

            if(rs.next()) {
                insertKey = rs.getInt(1);
            }
            enrolment.setId(insertKey);

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return enrolment;
    }

    public void updateGrade(Enrolment enrolment) {
        initiateConnection();

        StringBuffer sql = new StringBuffer(" UPDATE enrolment SET atendance = ? , assignment = ? , exame = ? WHERE id = ? ");

        try {

            preparedStatement = dbConnection.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setDouble(1,enrolment.getAtendance());
            preparedStatement.setDouble(2,enrolment.getAssignment());
            preparedStatement.setDouble(3,enrolment.getExame());
            preparedStatement.setInt(4,enrolment.getId());

            // execute select SQL stetement
            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
    }

    public Enrolment getByCourseAndStudent(Student student, Course course) {
        Enrolment enrolment = null;

        initiateConnection();

        StringBuffer sql = new StringBuffer(" SELECT  ")
                .append(" e.id as id, ")
                .append(" e.atendance as atendance, ")
                .append(" e.assignment as assignment, ")
                .append(" e.exame as exame ")
                .append(" FROM enrolment as e ")
                .append(" WHERE e.student_id = ? AND e.course_id = ? ");

        try {

            preparedStatement = dbConnection.prepareStatement(sql.toString());
            preparedStatement.setInt(1,student.getId());
            preparedStatement.setInt(2,course.getId());

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                enrolment = new Enrolment();

                enrolment.setId(rs.getInt("id"));
                enrolment.setStudent(student);
                enrolment.setCourse(course);
                enrolment.setAtendance(rs.getDouble("atendance"));
                enrolment.setAssignment(rs.getDouble("assignment"));
                enrolment.setExame(rs.getDouble("exame"));

            }

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return enrolment;
    }

}
