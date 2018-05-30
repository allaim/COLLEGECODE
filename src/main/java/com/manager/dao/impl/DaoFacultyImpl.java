package com.manager.dao.impl;

import com.manager.connector.JdbcConnector;
import com.manager.dao.DaoFaculty;
import com.manager.dao.DaoStudent;
import com.manager.domain.Faculty;
import com.manager.domain.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoFacultyImpl extends JdbcConnector implements DaoFaculty {

    public Faculty save(Faculty faculty) {
        initiateConnection();

        StringBuffer sql = new StringBuffer(" INSERT INTO faculty (name,address,phonenumber) VALUES (?,?,?)");

        try {

            preparedStatement = dbConnection.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,faculty.getName());
            preparedStatement.setString(2,faculty.getAddress());
            preparedStatement.setString(3,faculty.getPhoneNumber());

            // execute select SQL stetement
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            Integer insertKey = null;

            if(rs.next()) {
                insertKey = rs.getInt(1);
            }
            faculty.setId(insertKey);

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return faculty;
    }

    public Faculty searchById(Integer id) {
        Faculty faculty = null;

        initiateConnection();

        StringBuffer sql = new StringBuffer(" SELECT  ")
                .append(" f.id as id, ")
                .append(" f.name as name, ")
                .append(" f.address as address, ")
                .append(" f.phonenumber as phonenumber ")
                .append(" FROM faculty as f ")
                .append(" WHERE f.id = ? ");

        try {

            preparedStatement = dbConnection.prepareStatement(sql.toString());
            preparedStatement.setInt(1,id);

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                faculty = new Faculty();

                faculty.setId(rs.getInt("id"));
                faculty.setName(rs.getString("name"));
                faculty.setAddress(rs.getString("address"));
                faculty.setPhoneNumber(rs.getString("phonenumber"));

            }

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return faculty;
    }

    public List<Faculty> searchByName(String name) {
        List<Faculty> faculties = new ArrayList<Faculty>();

        initiateConnection();

        StringBuffer sql = new StringBuffer(" SELECT  ")
                .append(" f.id as id, ")
                .append(" f.name as name, ")
                .append(" f.address as address, ")
                .append(" f.phonenumber as phonenumber ")
                .append(" FROM faculty as f ")
                .append(" WHERE lower(f.name) LIKE lower(?)");

        try {

            preparedStatement = dbConnection.prepareStatement(sql.toString());
            preparedStatement.setString(1,"%"+name+"%");


            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Faculty faculty = new Faculty();

                faculty.setId(rs.getInt("id"));
                faculty.setName(rs.getString("name"));
                faculty.setAddress(rs.getString("address"));
                faculty.setPhoneNumber(rs.getString("phonenumber"));

                faculties.add(faculty);

            }

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return faculties;
    }

}
