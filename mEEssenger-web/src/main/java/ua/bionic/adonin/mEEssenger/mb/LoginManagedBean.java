package ua.bionic.adonin.mEEssenger.mb;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import ua.bionic.socialnetwork.entities.User;
import ua.bionic.socialnetwork.entities.UserType;
import ua.bionic.socialnetwork.entitiesbeans.user.UserFacadeLocal;
import ua.bionic.adonin.mEEssenger.managers.ConfigurationManager;

@ManagedBean
public class LoginManagedBean {
    
    @EJB
    private UserFacadeLocal userEJB;
    
    private String username;
    private String password;
    
    public LoginManagedBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(session != null){
            session.invalidate();
        }
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
     
    public String login() {
        
        String navto = "";
        User principal = userEJB.findByUsername(username);
        if (principal != null && principal.getPassword().equals(password)) {
            
            principal.setActivity(true);            
            userEJB.edit(principal);
            
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.getSessionMap().put("principal", principal);
            
            UserType role = principal.getTypeId();
            
//            if (role.toString().equals(ConfigurationManager.getConfigurationManagerInstance().getProperty(ConfigurationManager.ADMIN_ROLE))) {
//                navto = ConfigurationManager.getConfigurationManagerInstance().getProperty(ConfigurationManager.ADMINISTRATION_PAGE);
//            } else if (role.toString().equals(ConfigurationManager.getConfigurationManagerInstance().getProperty(ConfigurationManager.ADMIN_ROLE))) {
//                navto = ConfigurationManager.getConfigurationManagerInstance().getProperty(ConfigurationManager.PROFILE_PAGE);;
//            } else {
//                navto = ConfigurationManager.getConfigurationManagerInstance().getProperty(ConfigurationManager.ERROR_PAGE);
//            }
//            
//            if (principal.getIsBlocked()) {
//                navto = ConfigurationManager.getConfigurationManagerInstance().getProperty(ConfigurationManager.BLOCK_PAGE);
//            }    
            
            if (role.toString().equals("Administrator")) {
                System.out.println("admin");
                navto = "/admin/administration.xhtml";
            } else if (role.toString().equals("User")) {
                System.out.println("user");
                navto = "/user/profile.xhtml";
            } else {
                System.out.println("error");
                navto = "/error.xhtml";
            }
            
            if (principal.getIsBlocked()) {
                navto = "/block.xhtml";
            }
            
        }
        return navto + "?faces-redirect=true";
    }

    public void logout(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);        
        if(session != null){
            User principal = (User) session.getAttribute("principal");
            principal.setActivity(false);
            userEJB.edit(principal);
            session.invalidate();            
        }
        System.out.println("logout");
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/login.xhtml?faces-redirect=true");
    }
    
}
