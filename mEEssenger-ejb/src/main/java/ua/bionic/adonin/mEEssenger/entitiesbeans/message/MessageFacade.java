/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.bionic.adonin.mEEssenger.entitiesbeans.message;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ua.bionic.adonin.mEEssenger.entities.Message;
import ua.bionic.adonin.mEEssenger.entitiesbeans.AbstractFacade;

/**
 *
 * @author dimon
 */
@Stateless
public class MessageFacade extends AbstractFacade<Message> implements MessageFacadeLocal {
    @PersistenceContext(unitName = "ua.bionic_socialnetwork-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MessageFacade() {
        super(Message.class);
    }
    
}
