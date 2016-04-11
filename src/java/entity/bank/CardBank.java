/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.bank;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author zOzDarKzOz
 */
public class CardBank extends Bank implements Serializable {

    private int idCardBank;
    private String cartType;
    private String nameOwner;
    private String numCard;
    private Date dateReissue;

    public CardBank() {
    }

    public CardBank(int idCardBank, String cartType, String nameOwner, String numCard, Date dateReissue) {
        this.idCardBank = idCardBank;
        this.cartType = cartType;
        this.nameOwner = nameOwner;
        this.numCard = numCard;
        this.dateReissue = dateReissue;
    }

    public CardBank(int idCardBank, String cartType, String nameOwner, String numCard, Date dateReissue, int idBank, float blance) {
        super(idBank, blance);
        this.idCardBank = idCardBank;
        this.cartType = cartType;
        this.nameOwner = nameOwner;
        this.numCard = numCard;
        this.dateReissue = dateReissue;
    }

    public CardBank(int idCardBank, String cartType, String nameOwner, String numCard, Date dateReissue, float blance) {
        super(blance);
        this.idCardBank = idCardBank;
        this.cartType = cartType;
        this.nameOwner = nameOwner;
        this.numCard = numCard;
        this.dateReissue = dateReissue;
    }

    public CardBank(String cartType, String nameOwner, String numCard, Date dateReissue) {
        this.cartType = cartType;
        this.nameOwner = nameOwner;
        this.numCard = numCard;
        this.dateReissue = dateReissue;
    }

    public CardBank(String cartType, String nameOwner, String numCard, Date dateReissue, int idBank, float blance) {
        super(idBank, blance);
        this.cartType = cartType;
        this.nameOwner = nameOwner;
        this.numCard = numCard;
        this.dateReissue = dateReissue;
    }

    public CardBank(String cartType, String nameOwner, String numCard, Date dateReissue, float blance) {
        super(blance);
        this.cartType = cartType;
        this.nameOwner = nameOwner;
        this.numCard = numCard;
        this.dateReissue = dateReissue;
    }

    public int getIdCardBank() {
        return idCardBank;
    }

    public String getCartType() {
        return cartType;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public String getNumCard() {
        return numCard;
    }

    public Date getDateReissue() {
        return dateReissue;
    }

    public void setIdCardBank(int idCardBank) {
        this.idCardBank = idCardBank;
    }

    public void setCartType(String cartType) {
        this.cartType = cartType;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public void setNumCard(String numCard) {
        this.numCard = numCard;
    }

    public void setDateReissue(Date dateReissue) {
        this.dateReissue = dateReissue;
    }

    @Override
    public String getBType() {
        return "cardBank";
    }

    @Override
    public void setIdBankOfKcoinBank(int idBank) {
    }

    @Override
    public void setIdOfKcoinBank(int idKcoinBank) {
    }

    @Override
    public int getIdBankOfKcoinBank() {
        return -1;
    }

    @Override
    public int getIdOfKcoinBank() {
        return -1;
    }

}
