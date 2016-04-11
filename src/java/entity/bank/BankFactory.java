/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.bank;

/**
 *
 * @author zOzDarKzOz
 */
public class BankFactory {

    public Bank createBank(String bType) {
        try {
            if (bType == null) {
                return null;
            }
            if (bType.equals("kcoinBank")) {
                return new KcoinBank();
            }
            if (bType.equals("cardBank")) {
                return new CardBank();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
