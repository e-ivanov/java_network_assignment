/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshare.fmi.webdb.final_project.views;

import java.util.Map;

/**
 *
 * @author killer
 */
public class View {

    private Map model;
    private String forward;
    private String redirect;

    public View(String forward) {
        this.setForward(forward);
    }

    public View(String forward, Map data) {
        this.setForward(forward);
        if (data != null) {
            this.model = data;
        }
    }

    public void clean() {
        this.model = null;
    }

    public Map getModel() {
        return model;
    }

    public void setModel(Map model) {
        this.model = model;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
    
}
