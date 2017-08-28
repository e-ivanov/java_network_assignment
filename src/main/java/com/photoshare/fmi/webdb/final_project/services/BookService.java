/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshare.fmi.webdb.final_project.services;

import com.photoshare.fmi.webdb.final_project.domain.Book;
import com.photoshare.fmi.webdb.final_project.exceptions.UnsuccessfulBookRetrievalException;
import com.photoshare.fmi.webdb.final_project.exceptions.UnsuccessfulBookSaveException;
import com.photoshare.fmi.webdb.final_project.repository.BookRepository;
import java.sql.SQLException;
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
public class BookService {
    
    private final BookRepository bookRepository;

    @Inject
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    public void saveBook(Book book) throws UnsuccessfulBookSaveException{
        try {
            bookRepository.saveBook(book);
        } catch (SQLException ex) {
            Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnsuccessfulBookSaveException();
        }
    }
    
    public List<Book> getBooks() throws UnsuccessfulBookRetrievalException{
        try {
            return bookRepository.getBooks();
        } catch (SQLException ex) {
            Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnsuccessfulBookRetrievalException();
        }
    }
    
    public Book getBookById(long id) throws UnsuccessfulBookRetrievalException{
        try {
            return bookRepository.getBookById(id);
        } catch (SQLException ex) {
            Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnsuccessfulBookRetrievalException();
        }
    }
    
    public List<Book> getLatestBooks(int limitCount){
        return bookRepository.getLatestBooks(limitCount);
    }
    
}
