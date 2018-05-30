package com.manager.dao.impl;

import com.manager.dao.DaoUser;
import com.manager.domain.TypeUser;
import com.manager.domain.User;
import com.manager.connector.JdbcConnector;
import main.Constants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoUserImpl extends JdbcConnector implements DaoUser {

    public User searchUserByLogin(String login) {

        User user = null;

        initiateConnection();

        StringBuffer sql = new StringBuffer(" SELECT  ")
                                    .append(" u.id as id, ")
                                    .append(" u.login as login, ")
                                    .append(" u.name as name, ")
                                    .append(" u.password as password, ")
                                    .append(" u.id_extra as idextra, ")
                                    .append(" tu.id as tid, ")
                                    .append(" tu.name as tname ")
                                    .append(" FROM user as u ")
                                    .append(" LEFT OUTER JOIN typeuser as tu ON (u.typeuser_id = tu.id)")
                                    .append(" WHERE u.login LIKE ? ");

        try {

            preparedStatement = dbConnection.prepareStatement(sql.toString());
            preparedStatement.setString(1,login);

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                user = new User();

                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                    TypeUser typeUser = new TypeUser();
                    typeUser.setId(rs.getInt("tid"));
                    typeUser.setName(rs.getString("tname"));
                user.setTypeUser(typeUser);
                user.setIdExtra(rs.getInt("idextra"));

            }

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return user;
    }

    public User save(User user) {
        initiateConnection();

        StringBuffer sql = new StringBuffer();

        if(user.getTypeUser().getId()==2 || user.getTypeUser().getId()==4){
            sql.append(" INSERT INTO user (login,password,name,typeuser_id,id_extra) VALUES (?,?,?,?,?)");
        }else{
            sql.append(" INSERT INTO user (login,password,name,typeuser_id) VALUES (?,?,?,?)");
        }

        try {

            preparedStatement = dbConnection.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getName());
            preparedStatement.setInt(4,user.getTypeUser().getId());

            if(user.getTypeUser().getId()==2){
                preparedStatement.setInt(5,user.getFaculty().getId());
            }
            if(user.getTypeUser().getId()==4){
                preparedStatement.setInt(5,user.getStudent().getId());
            }

            // execute select SQL stetement
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            Integer insertKey = null;

            if(rs.next()) {
                insertKey = rs.getInt(1);
            }
            user.setId(insertKey);

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return user;
    }

}
