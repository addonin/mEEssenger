package ua.bionic.adonin.mEEssenger.mb;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import ua.bionic.socialnetwork.entities.User;
import ua.bionic.socialnetwork.entitiesbeans.user.UserFacadeLocal;

/**
 *
 * @author adonin
 */
@ManagedBean
@SessionScoped
public class UsersManagedBean implements Serializable {    
    
    @EJB
    private UserFacadeLocal userEJB;
    
    private String firstname;
    
    private String lastname;
    
    private String info;
    
    private String photo;
    
    private String searchString;
    
    private User selectedUser;

    public UsersManagedBean() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
    
    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
    
    public List<User> getAllUsers() {
        return userEJB.findAll();
    }
    
    public String setIsBlocked(User user, boolean flag) {
        user.setIsBlocked(flag);
        userEJB.edit(user);
        return "/admin/administration.xhtml";
    }
    
    public String editProfile() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = externalContext.getRequestParameterMap();
        String userId = params.get("userId");
        System.out.println(userId);
        String username = params.get("username");
        System.out.println(username);
        
        User user = userEJB.findByUserId(Integer.valueOf(userId));
        user.setUsername(username);
        userEJB.edit(user);
        
        HttpSession session = (HttpSession) externalContext.getSession(false);
        User principal = ((User) session.getAttribute("principal"));
        principal.setUsername(username);       
        
        return "/user/profile.xhtml";
    }
    
    public List<User> searchStringChanged() {
        List<User> users = null;
        users = userEJB.findUsersByLastname(searchString);
        return users;
    }
    
    public List<User> findFriends() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        User principal = ((User) session.getAttribute("principal"));
        return userEJB.findFriends(principal.getUserId());
    }
   
    
}
