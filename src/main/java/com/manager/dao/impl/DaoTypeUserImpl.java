package com.manager.dao.impl;

import com.manager.connector.JdbcConnector;
import com.manager.dao.DaoTypeUser;
import com.manager.domain.TypeUser;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoTypeUserImpl extends JdbcConnector implements DaoTypeUser {

    public TypeUser searchById(Integer id) {
        TypeUser typeUser = null;

        initiateConnection();

        StringBuffer sql = new StringBuffer(" SELECT  ")
                .append(" tu.id as id, ")
                .append(" tu.name as name ")
                .append(" FROM typeuser as tu ")
                .append(" WHERE tu.id = ? ");

        try {

            preparedStatement = dbConnection.prepareStatement(sql.toString());
            preparedStatement.setInt(1,id);

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                typeUser = new TypeUser();

                typeUser.setId(rs.getInt("id"));
                typeUser.setName(rs.getString("name"));

            }

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return typeUser;
    }

}
