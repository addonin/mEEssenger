/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.bionic.adonin.mEEssenger.entitiesbeans.usertype;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ua.bionic.adonin.mEEssenger.entities.UserType;
import ua.bionic.adonin.mEEssenger.entitiesbeans.AbstractFacade;

/**
 *
 * @author dimon
 */
@Stateless
public class UserTypeFacade extends AbstractFacade<UserType> implements UserTypeFacadeLocal {
    @PersistenceContext(unitName = "ua.bionic_socialnetwork-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserTypeFacade() {
        super(UserType.class);
    }
    
}
