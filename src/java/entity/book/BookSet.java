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
public class BookSet implements Serializable {

    private int idBookSet;
    private String name;
    private String description;
    private String sortLink;

    public BookSet() {
    }

    public void setIdBookSet(int idBookSet) {
        this.idBookSet = idBookSet;
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

    public int getIdBookSet() {
        return idBookSet;
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

    public BookSet(int idBookSet, String name, String description) {
        this.idBookSet = idBookSet;
        this.name = name;
        this.description = description;
    }

    public BookSet(String name, String description, String sortLink) {
        this.name = name;
        this.description = description;
        this.sortLink = sortLink;
    }

    public BookSet(int idBookSet, String name, String description, String sortLink) {
        this.idBookSet = idBookSet;
        this.name = name;
        this.description = description;
        this.sortLink = sortLink;
    }

    public String toString(String delim) {
        return idBookSet + delim + name + delim + description + delim + sortLink;
    }

}
