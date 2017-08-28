/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshare.fmi.webdb.final_project.repository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 *
 * @author killer
 */
public class BaseRepository {
    @Resource(name="jdbc/bookshowcase")
    DataSource ds;
    
}
