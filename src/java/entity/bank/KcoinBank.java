/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.bank;

import java.io.Serializable;

/**
 *
 * @author zOzDarKzOz
 */
public class KcoinBank extends Bank implements Serializable {

    private int idKcoinBank;

    public KcoinBank() {
    }

    public KcoinBank(int idKcoinBank) {
        this.idKcoinBank = idKcoinBank;
    }

    public KcoinBank(int idKcoinBank, int idBank, float blance) {
        super(idBank, blance);
        this.idKcoinBank = idKcoinBank;
    }

    public KcoinBank(int idKcoinBank, float blance) {
        super(blance);
        this.idKcoinBank = idKcoinBank;
    }

    public void setIdKcoinBank(int idKcoinBank) {
        this.idKcoinBank = idKcoinBank;
    }

    public int getIdKcoinBank() {
        return idKcoinBank;
    }

    @Override
    public String getBType() {
        return "kcoinBank";
    }

    @Override
    public void setIdBankOfKcoinBank(int idBank) {
        this.idBank = idBank;
    }

    @Override
    public int getIdBankOfKcoinBank() {
        return this.idBank;
    }

    @Override
    public int getIdOfKcoinBank() {
        return this.idKcoinBank;
    }

    @Override
    public void setIdOfKcoinBank(int idKcoinBank) {
        this.idKcoinBank = idKcoinBank;
    }

}
