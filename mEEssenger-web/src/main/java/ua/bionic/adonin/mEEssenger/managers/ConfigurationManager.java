package ua.bionic.adonin.mEEssenger.managers;

import java.util.ResourceBundle;

public class ConfigurationManager { 

    public static final String LOGIN_PAGE = "LOGIN_PAGE";
    public static final String REGISTRATION_PAGE = "REGISTRATION_PAGE";
    public static final String PASSWORD_RECOVERY_PAGE = "PASSWORD_RECOVERY_PAGE";
    public static final String ERROR_PAGE = "ERROR_PAGE";
    public static final String BLOCK_PAGE = "BLOCK_PAGE";
    public static final String FORBIDDEN_PAGE = "FORBIDDEN_PAGE";
    public static final String ADMINISTRATION_PAGE = "ADMINISTRATION_PAGE";
    public static final String PROFILE_PAGE = "PROFILE_PAGE";
        
    public static final String PEOPLE_PAGE = "PEOPLE_PAGE";
    public static final String MESSAGES_PAGE = "MESSAGES_PAGE";
    
    public static final String DEFAULT_PHOTO = "DEFAULT_PHOTO";
    public static final String DEFAULT_USER_TYPE = "DEFAULT_USER_TYPE";
    public static final String PASSWORD_RECOVERY_EMAIL_TEMPLATE = "PASSWORD_RECOVERY_EMAIL_TEMPLATE";
    public static final String ADMIN_ROLE = "ADMINISTRATOR";
    public static final String USER_ROLE = "USER";
    public static final String ADMINISTRATOR_EMAIL = "ADMINISTRATOR_EMAIL";
    
    private static final String CONFIGURATION = "common";
    private static volatile ConfigurationManager configurationManagerInstance;
    private ResourceBundle resourceBundle;
    
    public static ConfigurationManager getConfigurationManagerInstance(){
    	if (configurationManagerInstance == null) {
                synchronized (ConfigurationManager.class) {				
                        if (configurationManagerInstance == null) {
                                configurationManagerInstance = new ConfigurationManager();
                                configurationManagerInstance.resourceBundle = ResourceBundle.getBundle(CONFIGURATION);					
                        }
                }
        }
        return configurationManagerInstance;
    }
    
    public String getProperty(String key){
        return (String)resourceBundle.getObject(key);
    }
}

