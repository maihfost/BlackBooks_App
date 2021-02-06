/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcedu.blackbooks.entities.dto;

import java.util.List;

/**
 *
 * @author maihf
 */
public class FilterDTO {
    private List<String> authors;
    private List<String> categories;

    public FilterDTO() {
    }

    public FilterDTO(List<String> authors, List<String> categories) {
        this.authors = authors;
        this.categories = categories;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

}
