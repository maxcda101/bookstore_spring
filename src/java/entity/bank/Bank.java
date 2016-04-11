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
public abstract class Bank implements Serializable {

    protected int idBank;
    protected float blance;

    public Bank() {
    }

    public Bank(int idBank, float blance) {
        this.idBank = idBank;
        this.blance = blance;
    }

    public Bank(float blance) {
        this.blance = blance;
    }

    public int getIdBank() {
        return idBank;
    }

    public float getBlance() {
        return blance;
    }

    public void setIdBank(int idBank) {
        this.idBank = idBank;
    }

    public void setBlance(float blance) {
        this.blance = blance;
    }

    public abstract String getBType();

    public abstract void setIdBankOfKcoinBank(int idBank);

    public abstract void setIdOfKcoinBank(int idKcoinBank);

    public abstract int getIdBankOfKcoinBank();

    public abstract int getIdOfKcoinBank();
}
