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
public class Book implements Serializable {

    private int idBook;
    private String image;
    private String title;
    private String author;
    private String publisher;
    private String publishYear;
    private String description;
    private String originalPrice;
    private String salePrice;
    private int quantity;
    private Category category;
    private BookSet set;
    private String sortLink;
    private int votes;
    private float ratePoint;

    public Book() {
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSet(BookSet set) {
        this.set = set;
    }

    public void setSortLink(String sortLink) {
        this.sortLink = sortLink;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public void setRatePoint(float ratePoint) {
        this.ratePoint = ratePoint;
    }

    public int getIdBook() {
        return idBook;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public String getDescription() {
        return description;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public Category getCategory() {
        return category;
    }

    public BookSet getSet() {
        return set;
    }

    public String getSortLink() {
        return sortLink;
    }

    public int getVotes() {
        return votes;
    }

    public float getRatePoint() {
        return ratePoint;
    }

    public Book(String image, String title, String author, String publisher, String publishYear, String description, String originalPrice, String salePrice, int quantity, Category category, BookSet set, String sortLink, int votes, float ratePoint) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.description = description;
        this.originalPrice = originalPrice;
        this.salePrice = salePrice;
        this.quantity = quantity;
        this.category = category;
        this.set = set;
        this.sortLink = sortLink;
        this.votes = votes;
        this.ratePoint = ratePoint;
    }

    public Book(int idBook, String image, String title, String author, String publisher, String publishYear, String description, String originalPrice, String salePrice, int quantity, Category category, BookSet set, String sortLink, int votes, float ratePoint) {
        this.idBook = idBook;
        this.image = image;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.description = description;
        this.originalPrice = originalPrice;
        this.salePrice = salePrice;
        this.quantity = quantity;
        this.category = category;
        this.set = set;
        this.sortLink = sortLink;
        this.votes = votes;
        this.ratePoint = ratePoint;
    }
}
