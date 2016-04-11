/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.person.employee;

import java.io.Serializable;

/**
 *
 * @author zOzDarKzOz
 */
public class StaffStore extends Employee implements Serializable {

    @Override
    public String getEmployeeType() {
        return "staffStore";
    }

//    @Override
//    public Seller createSeller() {
//        return null;
//    }
//
//    @Override
//    public StaffManager createStaffManager() {
//        return null;
//    }
//
//    @Override
//    public StaffStore createStaffStore() {
//        return new StaffStore();
//    }
    
}
