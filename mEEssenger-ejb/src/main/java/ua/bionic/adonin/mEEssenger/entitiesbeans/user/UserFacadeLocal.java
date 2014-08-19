/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.bionic.adonin.mEEssenger.entitiesbeans.user;

import java.util.List;
import javax.ejb.Local;
import ua.bionic.adonin.mEEssenger.entities.User;

/**
 *
 * @author dimon
 */
@Local
public interface UserFacadeLocal {

    void create(User user);

    void edit(User user);

    void remove(User user);

    User find(Object id);
    
    User findByUserId(Integer userId);
    
    User findByUsername(String username);
    
    List<User> findUsersByLastname(String lastname);
    
    User findByEmail(String email);

    List<User> findAll();

    List<User> findRange(int[] range);
    
    List<User> findFriends(Integer userId);

    int count();    
    
}
