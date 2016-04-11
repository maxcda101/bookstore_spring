/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.order;

import entity.book.Book;
import java.io.Serializable;

/**
 *
 * @author txtd1
 */
public class BookOrder implements Serializable {
    private int id;
    private int quantity;
    private float totalPrice;
    private Book book;

    public BookOrder() {
    }

    public BookOrder(int quantity, float totalPrice, Book book) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.book = book;
    }
    
    public BookOrder(int id, int quantity, float totalPrice, Book book) {
        this.id = id;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.book = book;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public Book getBook() {
        return book;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    
}
