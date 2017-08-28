/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshare.fmi.webdb.final_project.repository;

import com.photoshare.fmi.webdb.final_project.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author killer
 */
public class UserRepository extends BaseRepository{


    public User getUserByUserName(String userName){
        String sql = "SELECT * FROM User WHERE username = ?";
        User user = new User();
        Connection connection = null;
        try {
            connection = ds.getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            ResultSet resultSet = st.executeQuery();
            while(resultSet.next()){
                
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
        
    }



    public void saveUser(User user) {
        String sql = "INSERT INTO User(firstName, lastName, username, email, password) VALUES (?,?,?,?,?)";
        String rolesSQL = "INSERT INTO UserRoles (username, rolename) VALUES (?,?)";
        Connection connection = null;
        try {
            connection = ds.getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user.getFirstName());
            st.setString(2, user.getLastName());
            st.setString(3, user.getUserName());
            st.setString(4, user.getEmail());
            st.setString(5, user.getPassword());
            st.execute();
            PreparedStatement roleSt = connection.prepareStatement(rolesSQL);
            roleSt.setString(1, user.getUserName());
            roleSt.setString(2, "USER");
            roleSt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteUser(User user) {

    }


}
