package ua.bionic.adonin.mEEssenger.entitiesbeans.relation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import ua.bionic.adonin.mEEssenger.entities.Relation;
import ua.bionic.adonin.mEEssenger.entitiesbeans.AbstractFacade;

/**
 *
 * @author dimon
 */
@Stateless
public class RelationFacade extends AbstractFacade<Relation> implements RelationFacadeLocal {
    @PersistenceContext(unitName = "ua.bionic_socialnetwork-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RelationFacade() {
        super(Relation.class);
    }

    @Override
    public Relation findRelation(int fromId, int toId) {
        TypedQuery<Relation> query = em.createNamedQuery("Relation.findRelation", Relation.class);
        query.setParameter("fromId", fromId);
        query.setParameter("toId", toId);
        Relation relation = null;
        try {
            relation = query.getSingleResult();            
        } catch (PersistenceException e) {
            System.out.println("No relation between " + fromId + " and " + toId);
        }
        return relation;
    }
    
}
