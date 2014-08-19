package ua.bionic.adonin.mEEssenger.mb;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import ua.bionic.socialnetwork.entities.User;
import ua.bionic.socialnetwork.entities.UserType;
import ua.bionic.socialnetwork.entitiesbeans.user.UserFacadeLocal;
import ua.bionic.adonin.mEEssenger.managers.ConfigurationManager;

/**
 *
 * @author adonin
 */
@ManagedBean
@RequestScoped
public class RegistrationManagedBean {
    
    @EJB
    private UserFacadeLocal userEJB;

    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    
    public RegistrationManagedBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    
    public String register() {        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setInfo("");
        user.setPhoto(ConfigurationManager.getConfigurationManagerInstance().getProperty(ConfigurationManager.DEFAULT_PHOTO));
        user.setTypeId(new UserType(Integer.valueOf(ConfigurationManager.getConfigurationManagerInstance().getProperty(ConfigurationManager.DEFAULT_USER_TYPE))));
        userEJB.create(user);
        return "/login.xhtml?faces-redirect=true";
    }
    
}
