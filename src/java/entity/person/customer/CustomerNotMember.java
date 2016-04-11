/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.person.customer;

import java.io.Serializable;

/**
 *
 * @author zOzDarKzOz
 */
public class CustomerNotMember extends Customer implements Serializable {

    @Override
    public String getCustomerType() {
        return "customerNotMember";
    }

//    @Override
//    public CustomerMember createCustomerMember(int idCustomerMember, String username, String password, KcoinBank kcoinBank, Date createDate, int idPerson, FullName fullName, Address address, int idCustomer, String phoneNum, String email) {
//        return null;
//    }
//
//    @Override
//    public CustomerNotMember createCustomerNotMember() {
//        return new CustomerNotMember();
//    }
//
//    @Override
//    public CustomerMember createCustomerMember() {
//        return null;
//    }
    @Override
    public String getCustomerMemberUsername() {
        return null;
    }

    @Override
    public String getCustomerMemberPassword() {
        return null;
    }

    @Override
    public void setCustomerMemberUsername(String username) {
    }

    @Override
    public void setCustomerMemberPassword(String password) {
    }

    @Override
    public int getCustomerMemberId() {
        return -1;
    }

}
