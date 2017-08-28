/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshare.fmi.webdb.final_project.controller;

import com.photoshare.fmi.webdb.final_project.FrontController;
import com.photoshare.fmi.webdb.final_project.annotations.Controller;
import com.photoshare.fmi.webdb.final_project.annotations.RequestMapping;
import com.photoshare.fmi.webdb.final_project.domain.Book;
import com.photoshare.fmi.webdb.final_project.exceptions.UnsuccessfulBookRetrievalException;
import com.photoshare.fmi.webdb.final_project.exceptions.UnsuccessfulBookSaveException;
import com.photoshare.fmi.webdb.final_project.services.BookService;
import com.photoshare.fmi.webdb.final_project.utils.CommonUtils;

import com.photoshare.fmi.webdb.final_project.views.View;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@Controller(value = "/book")
public class BooksController implements IController {

    private static final String SAVE_DIR = "uploadFiles";

    private final BookService bookService;

    @Inject
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/create")
    public View loginUser(HttpServletRequest request, HttpServletResponse response) {

        View uploadView = new View("/upload.jsp");
        if (request.getMethod().equals("POST")) {
            String savePath = FrontController.DIR_LOCATION;
            File fileSaveDir = new File(savePath);
            String fileName = "";
            String sampleChapterLocation = "";
            String extraResourcesLocation = "";
            String sampleCodeLocation = "";
            String coverLocation = "";
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }
            try {
                for (Part part : request.getParts()) {
                    fileName = CommonUtils.extractFileName(part);

                    if (fileName != "") {
                        part.write(savePath + File.separator + fileName);
                        String test = part.getName();
                        if (part.getName().equals("sampleChapterLocation")) {
                            sampleChapterLocation = part.getSubmittedFileName();
                        }
                        
                        if(part.getName().equals("extraResourcesLocation")){
                            extraResourcesLocation = part.getSubmittedFileName();
                        }
                        
                        if(part.getName().equals("sampleCodeLocation")){
                            sampleCodeLocation = part.getSubmittedFileName();
                        }
                        
                        if(part.getName().equals("bookCover")){
                            coverLocation = part.getSubmittedFileName();
                        }
                    }

                }
            } catch (IOException ex) {
                Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ServletException se) {
                Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, se);
            }
            Book book = new Book();
            book.setAuthors(request.getParameter("authors"));
            book.setErrata(request.getParameter("errata"));
            book.setIsbn(request.getParameter("isbn"));
            book.setTitle(request.getParameter("bookTitle"));
            book.setTableOfContents(request.getParameter("tableOfContents"));
            book.setExtraResourcesLocation(extraResourcesLocation);
            book.setSampleChapterLocation(sampleChapterLocation);
            book.setSampleCodeLocation(sampleCodeLocation);
            book.setCover(coverLocation);
            
            try {
                bookService.saveBook(book);
                request.setAttribute("message", "Успешно съхранихте книга със заглавие: "+book.getTitle());
            } catch (UnsuccessfulBookSaveException ex) {
                Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", "Неуспешен опит за съхранение на книга");
            }
        }

        
        return uploadView;

    }

    @RequestMapping(value = "/list")
    public View viewPhotos(HttpServletRequest request, HttpServletResponse response) {
        View viewPhotos = new View("/books.jsp");
        List<Book> books;
        try {
            books = bookService.getBooks();
            request.setAttribute("books", books);
        } catch (UnsuccessfulBookRetrievalException ex) {
            Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "Неуспешен опит за преглед на списък с книги");
        }
        return viewPhotos;
    }

    @RequestMapping(value = "/view")
    public View viewPhoto(HttpServletRequest request, HttpServletResponse response) {
        View viewPhoto = new View("/book.jsp");
        String id = request.getParameter("id");
        Book book;
        try {
            book = bookService.getBookById(Long.valueOf(request.getParameter("id")));
            request.setAttribute("book", book);
        } catch (UnsuccessfulBookRetrievalException ex) {
            Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "Неуспешен опит за преглед на книга");
        }
        
        return viewPhoto;
    }


}
