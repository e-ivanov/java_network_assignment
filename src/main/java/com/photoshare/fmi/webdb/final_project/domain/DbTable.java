/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshare.fmi.webdb.final_project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author killer
 */
public class DbTable {
    
    private String name;
    private List<DbColumn> columns = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DbColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<DbColumn> columns) {
        this.columns = columns;
    }

    
    
    
}
