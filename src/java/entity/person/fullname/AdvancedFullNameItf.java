/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.person.fullname;

/**
 *
 * @author zOzDarKzOz
 */
public interface AdvancedFullNameItf {
    
    public String getViFullName(String firstName, String middleName, String lastName);
    public String getEnFullName(String firstName, String middleName, String lastName);
}
