/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshare.fmi.webdb.final_project.controller;

import com.photoshare.fmi.webdb.final_project.annotations.Controller;
import com.photoshare.fmi.webdb.final_project.annotations.RequestMapping;
import com.photoshare.fmi.webdb.final_project.domain.User;
import com.photoshare.fmi.webdb.final_project.services.UserService;
import com.photoshare.fmi.webdb.final_project.views.View;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author killer
 */
@Singleton
@Controller(value = "/user")
public class UserController implements IController {

    
    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register")
    public View createUser(HttpServletRequest request, HttpServletResponse response) {
        String method = request.getMethod();
        if (request.getMethod() == "POST") {
            User user = new User();
            user.setEmail(request.getParameter("email"));
            user.setFirstName(request.getParameter("first_name"));
            user.setLastName(request.getParameter("last_name"));
            user.setUserName(request.getParameter("username"));
            String password = DigestUtils.sha512Hex(request.getParameter("password"));
            user.setPassword(password);
            userService.saveUser(user);
            request.setAttribute("message", "Успешна регистрация!");
        }
        View createUserView = new View("/createuser.jsp");

        return createUserView;
    }

    @RequestMapping(value = "/login")
    public View loginUser(HttpServletRequest request, HttpServletResponse response) {
        View loginView = new View("/login.jsp");

        return loginView;
    }

    @RequestMapping(value = "/loginError")
    public View loginError(HttpServletRequest request, HttpServletResponse response) {
        View loginError = new View("/error.jsp");

        return loginError;
    }



}
