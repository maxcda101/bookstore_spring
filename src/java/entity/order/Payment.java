/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.order;

import entity.bank.Bank;
import java.io.Serializable;

/**
 *
 * @author zOzDarKzOz
 */
public class Payment implements Serializable {
    
    private int id;
    private Cart cart;
    private Bank bank;

    public Payment() {
    }

    public Payment(int id, Cart cart, Bank bank) {
        this.id = id;
        this.cart = cart;
        this.bank = bank;
    }

    public Payment(Cart cart, Bank bank) {
        this.cart = cart;
        this.bank = bank;
    }

    public int getId() {
        return id;
    }

    public Cart getCart() {
        return cart;
    }

    public Bank getBank() {
        return bank;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
    
}
