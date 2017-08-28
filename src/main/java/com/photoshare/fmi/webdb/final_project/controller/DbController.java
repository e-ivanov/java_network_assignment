/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshare.fmi.webdb.final_project.controller;

import com.photoshare.fmi.webdb.final_project.annotations.Controller;
import com.photoshare.fmi.webdb.final_project.annotations.RequestMapping;
import com.photoshare.fmi.webdb.final_project.domain.DbTable;
import com.photoshare.fmi.webdb.final_project.exceptions.UnsuccessfulBookRetrievalException;
import com.photoshare.fmi.webdb.final_project.services.DbInfoService;
import com.photoshare.fmi.webdb.final_project.views.View;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author killer
 */
@Singleton
@Controller(value = "/dbcontroller")
public class DbController implements IController{
    
    private final DbInfoService DbInfoService;

    @Inject
    public DbController(DbInfoService DbInfoService) {
        this.DbInfoService = DbInfoService;
    }
    
    @RequestMapping(value="/showdbinfo")
    public View showDbInfo(HttpServletRequest request, HttpServletResponse response){
        View view = new View("/dbinfo.jsp");
        try {
            List<String> databases = DbInfoService.getDatabases();
            request.setAttribute("databases", databases);
        } catch (UnsuccessfulBookRetrievalException ex) {
            Logger.getLogger(DbController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return view;
    }
    @RequestMapping(value="/showtableinfo")
    public View showTableInfo(HttpServletRequest request, HttpServletResponse response){
        View view = new View("/tableinfo.jsp");
        String dbName = request.getParameter("dbname");
        List<DbTable> tables = DbInfoService.getTables(dbName);
        try{
        request.setAttribute("tables", tables);
        }catch(Exception e){
            String test = "";
        }
        return view;
    }
}
