/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshare.fmi.webdb.final_project.controller;

import com.photoshare.fmi.webdb.final_project.annotations.Controller;
import com.photoshare.fmi.webdb.final_project.annotations.RequestMapping;
import com.photoshare.fmi.webdb.final_project.services.ConverterService;
import com.photoshare.fmi.webdb.final_project.utils.CommonUtils;
import com.photoshare.fmi.webdb.final_project.views.View;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author killer
 */
@Singleton
@Controller(value="/converter")
public class ConverterController implements IController{
    
    private final ConverterService converterService;

    @Inject
    public ConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }
    
    
    @RequestMapping(value="/convert")
    public View convertFile(HttpServletRequest request, HttpServletResponse response){
        View view = new View("/convert.jsp");
        String fileName = "";
        byte output[] = null;
        if (request.getMethod().equals("POST")) {
            try {
                for (Part part : request.getParts()) {
                    fileName = CommonUtils.extractFileName(part);

                    if (fileName != "") {

                        output = converterService.convert(part);
                    }

                }
            } catch (IOException ex) {
                Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ServletException se) {
                Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, se);
            }
            response.setContentType("application/pdf");
        try {
            OutputStream outStream = response.getOutputStream();
            outStream.write(output);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(ConverterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        return view;
    }

}
