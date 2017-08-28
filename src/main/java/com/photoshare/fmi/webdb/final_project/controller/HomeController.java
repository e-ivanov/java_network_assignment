/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshare.fmi.webdb.final_project.controller;

import com.photoshare.fmi.webdb.final_project.views.View;
import com.photoshare.fmi.webdb.final_project.annotations.Controller;
import com.photoshare.fmi.webdb.final_project.annotations.RequestMapping;
import com.photoshare.fmi.webdb.final_project.domain.Book;
import com.photoshare.fmi.webdb.final_project.exceptions.UnsuccessfulBookRetrievalException;
import com.photoshare.fmi.webdb.final_project.services.BookService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@Controller(value="/")
public class HomeController implements IController{
    
    private final BookService bookService;

    @Inject
    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @RequestMapping(value="/home")
    public View index(HttpServletRequest request, HttpServletResponse response){
        View home = new View("/home.jsp");
        
        try {
            List<Book> books = bookService.getBooks();
            request.setAttribute("books", books);
        } catch (UnsuccessfulBookRetrievalException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "Неуспешен опит за преглед на списък с книги");
        }
        return home;
    }
    

    
    public View send404(HttpServletRequest request, HttpServletResponse response){
         View test = new View("/notfound.jsp");
        Map<String, Object> params = new HashMap<String, Object>();
        test.setModel(params);
        
        return test;
    }
    
}
