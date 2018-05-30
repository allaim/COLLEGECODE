package com.manager.dao.impl;

import com.manager.connector.JdbcConnector;
import com.manager.dao.DaoBranch;
import com.manager.dao.DaoCourse;
import com.manager.domain.Branch;
import com.manager.domain.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoBranchImpl extends JdbcConnector implements DaoBranch {

    public List<Branch> getAllBranches() {
        List<Branch> branches = new ArrayList<Branch>();

        initiateConnection();

        StringBuffer sql = new StringBuffer(" SELECT  ")
                .append(" b.id as id, ")
                .append(" b.name as name, ")
                .append(" b.address as address ")
                .append(" FROM branch as b ");

        try {

            preparedStatement = dbConnection.prepareStatement(sql.toString());

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Branch branch = new Branch();
                branch.setId(rs.getInt("id"));
                branch.setName(rs.getString("name"));
                branch.setAddress(rs.getString("address"));

                branches.add(branch);

            }

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return branches;
    }

    public Branch searchById(Integer id) {
        Branch branch = null;

        initiateConnection();

        StringBuffer sql = new StringBuffer(" SELECT  ")
                .append(" b.id as id, ")
                .append(" b.name as name, ")
                .append(" b.address as address ")
                .append(" FROM branch as b ")
                .append(" WHERE b.id = ? ");

        try {

            preparedStatement = dbConnection.prepareStatement(sql.toString());
            preparedStatement.setInt(1,id);

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                branch = new Branch();
                branch.setId(rs.getInt("id"));
                branch.setName(rs.getString("name"));
                branch.setAddress(rs.getString("address"));

            }

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return branch;
    }

    public Branch save(Branch branch) {
        initiateConnection();

        StringBuffer sql = new StringBuffer(" INSERT INTO branch (name,address) VALUES (?,?)");

        try {

            preparedStatement = dbConnection.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,branch.getName());
            preparedStatement.setString(2,branch.getAddress());

            // execute select SQL stetement
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            Integer insertKey = null;

            if(rs.next()) {
                insertKey = rs.getInt(1);
            }
            branch.setId(insertKey);

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return branch;
    }

}
