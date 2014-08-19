/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.bionic.adonin.mEEssenger.entitiesbeans.usertype;

import java.util.List;
import javax.ejb.Local;
import ua.bionic.adonin.mEEssenger.entities.UserType;

/**
 *
 * @author dimon
 */
@Local
public interface UserTypeFacadeLocal {

    void create(UserType userType);

    void edit(UserType userType);

    void remove(UserType userType);

    UserType find(Object id);

    List<UserType> findAll();

    List<UserType> findRange(int[] range);

    int count();
    
}
