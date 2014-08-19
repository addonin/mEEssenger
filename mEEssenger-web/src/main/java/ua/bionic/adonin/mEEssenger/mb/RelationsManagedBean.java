package ua.bionic.adonin.mEEssenger.mb;

import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import ua.bionic.socialnetwork.entities.Relation;
import ua.bionic.socialnetwork.entities.User;
import ua.bionic.socialnetwork.entitiesbeans.relation.RelationFacadeLocal;

/**
 *
 * @author Admin
 */
@ManagedBean
@RequestScoped
public class RelationsManagedBean {

    @EJB
    private RelationFacadeLocal relationEJB;
    
    @ManagedProperty(value="#{usersManagedBean}")
    private UsersManagedBean usersManagedBean;

    public UsersManagedBean getUsersManagedBean() {
        return usersManagedBean;
    }

    public void setUsersManagedBean(UsersManagedBean usersManagedBean) {
        this.usersManagedBean = usersManagedBean;
    }
    
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    Map<String, String> params = externalContext.getRequestParameterMap();
    HttpSession session = (HttpSession) externalContext.getSession(false);
    Integer principalId = ((User) session.getAttribute("principal")).getUserId();  
    
    private int fromId;
    private int toId;
    
    public RelationsManagedBean() {
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }
    
    public boolean findRelation(int fromId, int toId) {
        boolean result = false;
        if (relationEJB.findRelation(fromId, toId) != null) {
            result = true;
        }
        return result;
    }
    
    public void addRelation() {
        Integer candidateId = Integer.valueOf(params.get("candidateId"));
        System.out.println("add relation " + principalId + " : " + candidateId);
        relationEJB.create(new Relation(principalId, candidateId));
        usersManagedBean.searchStringChanged();
    }
    
    public void removeRelation() {
        Integer candidateId = Integer.valueOf(params.get("candidateId"));        
        Relation relation = relationEJB.findRelation(principalId, candidateId);
        if (relation != null) {
            relationEJB.remove(relation);
            System.out.println("Removing relation " + principalId + " : " + candidateId);
        } else {
            System.out.println("Failed to remove relation");
        }        
    }

}
