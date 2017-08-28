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
public class Book {
    private long id;
    private String title;
    private String isbn;
    private String cover;
    private String authors;
    private String tableOfContents;
    private String sampleChapterLocation;
    private String extraResourcesLocation;
    private String sampleCodeLocation;
    private String errata;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getSampleChapterLocation() {
        return sampleChapterLocation;
    }

    public void setSampleChapterLocation(String sampleChapterLocation) {
        this.sampleChapterLocation = sampleChapterLocation;
    }

    public String getExtraResourcesLocation() {
        return extraResourcesLocation;
    }

    public void setExtraResourcesLocation(String extraResourcesLocation) {
        this.extraResourcesLocation = extraResourcesLocation;
    }

    public String getSampleCodeLocation() {
        return sampleCodeLocation;
    }

    public void setSampleCodeLocation(String sampleCodeLocation) {
        this.sampleCodeLocation = sampleCodeLocation;
    }

    public String getErrata() {
        return errata;
    }

    public void setErrata(String errata) {
        this.errata = errata;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTableOfContents() {
        return tableOfContents;
    }

    public void setTableOfContents(String tableOfContents) {
        this.tableOfContents = tableOfContents;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
    
    
    
    
}
