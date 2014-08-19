package ua.bionic.adonin.mEEssenger.entitiesbeans.user;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import ua.bionic.adonin.mEEssenger.entities.User;
import ua.bionic.adonin.mEEssenger.entitiesbeans.AbstractFacade;

/**
 *
 * @author dimon
 */
@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {
    @PersistenceContext(unitName = "ua.bionic_socialnetwork-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class);
        query.setParameter("email", email);
        User user = null;
        user = query.getSingleResult();
        return user;        
    }

    @Override
    public User findByUsername(String username) {        
        TypedQuery<User> query = em.createNamedQuery("User.findByUsername", User.class);
        query.setParameter("username", username);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (PersistenceException e) {
            System.out.println("Failed to login with username \"" + username + "\"");
        }
        return user;
    }

    @Override
    public User findByUserId(Integer userId) {
        TypedQuery<User> query = em.createNamedQuery("User.findByUserId", User.class);
        query.setParameter("userId", userId);
        User user = null;
        user = query.getSingleResult();
        return user;
    }

    @Override
    public List<User> findUsersByLastname(String lastname) {
        TypedQuery<User> query = em.createNamedQuery("User.findUsersByLastname", User.class);
        List<User> users = null;
        if (lastname != null && (lastname.trim().length() > 0)) { 
            query.setParameter("lastname", lastname.trim() + "%");
            users = query.getResultList();
        }
        return users;
    }
    
    @Override
    public List<User> findFriends(Integer userId) {
        Query query = em.createNativeQuery("SELECT u.* FROM Users u JOIN Relations r1 ON u.user_id = r1.from_id JOIN Relations r2 ON r1.from_id = r2.to_id AND r1.to_id = r2.from_id AND r1.from_id <> r1.to_id WHERE r2.from_id = " + userId, User.class);
        List<User> list = null;
        list = query.getResultList();
        return list;
    }
    
}
