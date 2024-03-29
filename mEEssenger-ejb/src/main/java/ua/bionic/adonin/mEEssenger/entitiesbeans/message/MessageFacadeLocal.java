/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.bionic.adonin.mEEssenger.entitiesbeans.message;

import java.util.List;
import javax.ejb.Local;
import ua.bionic.adonin.mEEssenger.entities.Message;

/**
 *
 * @author dimon
 */
@Local
public interface MessageFacadeLocal {

    void create(Message message);

    void edit(Message message);

    void remove(Message message);

    Message find(Object id);

    List<Message> findAll();

    List<Message> findRange(int[] range);

    int count();
    
}
