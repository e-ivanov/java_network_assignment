/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshare.fmi.webdb.final_project.services;

import com.photoshare.fmi.webdb.final_project.domain.DbTable;
import com.photoshare.fmi.webdb.final_project.exceptions.UnsuccessfulBookRetrievalException;
import com.photoshare.fmi.webdb.final_project.repository.DbInfoRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * @author killer
 */
@Singleton
public class DbInfoService {
    private final DbInfoRepository dbInfoRepository;

    @Inject
    public DbInfoService(DbInfoRepository dbInfoRepository) {
        this.dbInfoRepository = dbInfoRepository;
    }

   
    public List<String> getDatabases() throws UnsuccessfulBookRetrievalException{
        try {
            return dbInfoRepository.getDatabases();
        } catch (SQLException ex) {
            Logger.getLogger(DbInfoService.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnsuccessfulBookRetrievalException();
        }
    }
    
    
    public List<DbTable> getTables(String database){
        return dbInfoRepository.getTables(database);
    }
    
    
    
}
