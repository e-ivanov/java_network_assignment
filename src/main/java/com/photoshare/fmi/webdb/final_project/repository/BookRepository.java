/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshare.fmi.webdb.final_project.repository;

import com.photoshare.fmi.webdb.final_project.domain.Book;
import com.photoshare.fmi.webdb.final_project.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Singleton;

/**
 *
 * @author killer
 */
@Singleton
public class BookRepository extends BaseRepository {

    public void saveBook(Book book) throws SQLException {

        String sql = "INSERT INTO Book(title, isbn, authors, tableOfContents, "
                + "sampleChapterLocation, extraResourcesLocation,"
                + "sampleCodeLocation, errata, cover) "
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement st = null;
        try {
            connection = ds.getConnection();
            st = connection.prepareStatement(sql);
            st.setString(1, book.getTitle());
            st.setString(2, book.getIsbn());
            st.setString(3, book.getAuthors());
            st.setString(4, book.getTableOfContents());
            st.setString(5, book.getSampleChapterLocation());
            st.setString(6, book.getExtraResourcesLocation());
            st.setString(7, book.getSampleCodeLocation());
            st.setString(8, book.getErrata());
            st.setString(9, book.getCover());
            st.execute();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Book> getBooks() throws SQLException {
        List<Book> books = new ArrayList<Book>();

        String sql = "SELECT * FROM Book";
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet resultSet = null;
        try {
            connection = ds.getConnection();
            st = connection.prepareStatement(sql);
            resultSet = st.executeQuery();
            while(resultSet.next()){
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setAuthors(resultSet.getString("authors"));
                book.setErrata(resultSet.getString("errata"));
                book.setExtraResourcesLocation(resultSet.getString("extraResourcesLocation"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setSampleChapterLocation(resultSet.getString("sampleChapterLocation"));
                book.setSampleCodeLocation(resultSet.getString("sampleCodeLocation"));
                book.setTableOfContents(resultSet.getString("tableOfContents"));
                book.setTitle(resultSet.getString("title"));
                book.setCover(resultSet.getString("cover"));
                books.add(book);
            }
        }finally{
            if(st != null){
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return books;
    }

    public Book getBookById(long id) throws SQLException {
        Book book = new Book();

        String sql = "SELECT * FROM Book WHERE id = ?";
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet resultSet = null;
        try {
            connection = ds.getConnection();
            st = connection.prepareStatement(sql);
            st.setLong(1, id);
            resultSet = st.executeQuery();
            while(resultSet.next()){
                
                book.setId(resultSet.getLong("id"));
                book.setAuthors(resultSet.getString("authors"));
                book.setErrata(resultSet.getString("errata"));
                book.setExtraResourcesLocation(resultSet.getString("extraResourcesLocation"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setSampleChapterLocation(resultSet.getString("sampleChapterLocation"));
                book.setSampleCodeLocation(resultSet.getString("sampleCodeLocation"));
                book.setTableOfContents(resultSet.getString("tableOfContents"));
                book.setTitle(resultSet.getString("title"));
                book.setCover(resultSet.getString("cover"));
            }
            
        }finally{
            if(st != null){
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return book;
    }

    public List<Book> getLatestBooks(int limitCount) {
        List<Book> latestBooks = new ArrayList<Book>();

        return latestBooks;
    }
}
