/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshare.fmi.webdb.final_project.services;

import com.photoshare.fmi.webdb.final_project.domain.User;
import com.photoshare.fmi.webdb.final_project.repository.UserRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author killer
 */
@Singleton
public class UserService {
    
    private final UserRepository userRepo;

    @Inject
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User getUserByUserName(String userName){
        return userRepo.getUserByUserName(userName);
    }
    
    public void saveUser(User user){
        userRepo.saveUser(user);
    }
    
    public void deleteUser(User user){
        userRepo.deleteUser(user);
    }
}
