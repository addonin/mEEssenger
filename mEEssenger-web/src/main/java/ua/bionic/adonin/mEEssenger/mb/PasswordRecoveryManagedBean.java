package ua.bionic.adonin.mEEssenger.mb;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import ua.bionic.socialnetwork.entities.User;
import ua.bionic.socialnetwork.entitiesbeans.user.UserFacadeLocal;
import ua.bionic.adonin.mEEssenger.managers.ConfigurationManager;
import ua.bionic.socialnetwork.util.MailSessionBean;

/**
 *
 * @author adonin
 */
@ManagedBean
public class PasswordRecoveryManagedBean {

    @EJB
    private UserFacadeLocal userEJB;
    
    @EJB
    private MailSessionBean mailEJB;    
    
    private String sender;
    
    private String email;
    
    public PasswordRecoveryManagedBean() {
    }    
    
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String checkEmail() {
        User user;
        if (email != null) {
            user = userEJB.findByEmail(email);
            if (user != null) {
                String address = user.getEmail();
                String password = user.getPassword();
                if (address != null && password != null) {
                    createRecoveryPasswordLetter(address, password);
                }
            }
        }
        return "/login.xhtml?faces-redirect=true";
    }
    
    private void createRecoveryPasswordLetter(String email, String password) {
        System.out.println("=====email " + email);
        System.out.println("=====password  " + password);
        mailEJB.send(email, ConfigurationManager.getConfigurationManagerInstance().getProperty(ConfigurationManager.PASSWORD_RECOVERY_EMAIL_TEMPLATE) + password);
    }
    
}
