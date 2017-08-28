/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshare.fmi.webdb.final_project.domain;

/**
 *
 * @author killer
 */
public class DbColumn {
    private String name;
    private String defaultValue;
    private String autoincrement;
    private String type;
    private String isNullable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAutoincrement() {
        return autoincrement;
    }

    public void setAutoincrement(String autoincrement) {
        this.autoincrement = autoincrement;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    @Override
    public String toString() {
        return "DbColumn{" + "name=" + name + ", defaultValue=" + defaultValue + ", autoincrement=" + autoincrement + ", type=" + type + ", isNullable=" + isNullable + '}';
    }
    
    
    
    
    
}
