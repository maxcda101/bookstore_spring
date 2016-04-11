/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.book;

import java.io.Serializable;

/**
 *
 * @author txtd1
 */
public class Category implements Serializable {

    private int idCategory;
    private String name;
    private String description;
    private String sortLink;

    public Category() {
    }

    public Category(int idCategory, String name, String description) {
        this.idCategory = idCategory;
        this.name = name;
        this.description = description;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSortLink(String sortLink) {
        this.sortLink = sortLink;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSortLink() {
        return sortLink;
    }

    public Category(String name, String description, String sortLink) {
        this.name = name;
        this.description = description;
        this.sortLink = sortLink;
    }

    public Category(int idCategory, String name, String description, String sortLink) {
        this.idCategory = idCategory;
        this.name = name;
        this.description = description;
        this.sortLink = sortLink;
    }

    public String toString(String delim) {
        return idCategory + delim + name + delim + description + delim + sortLink;
    }

}
