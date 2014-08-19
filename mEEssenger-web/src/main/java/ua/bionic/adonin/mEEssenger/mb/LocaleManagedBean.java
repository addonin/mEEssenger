package ua.bionic.adonin.mEEssenger.mb;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author adonin
 */
@ManagedBean
@SessionScoped
public class LocaleManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String locale;// = FacesContext.getCurrentInstance().getApplication().getDefaultLocale().getLanguage();

    private static Map<String,Object> countries;
   
    static {        
        countries = new LinkedHashMap<String,Object>();
        countries.put("EN", Locale.ENGLISH);
        countries.put("RU", new Locale("ru", "RU"));
    }

    public LocaleManagedBean() {
        this.locale = FacesContext.getCurrentInstance().getApplication().getDefaultLocale().getLanguage();
    }    
    
    public Map<String, Object> getCountries() {
        return countries;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    //value change event listener
    public void localeChanged(ValueChangeEvent e) {        
        String newLocaleValue = e.getNewValue().toString();
        for (Map.Entry<String, Object> entry : countries.entrySet()) {
            System.out.println("=====" + entry.getValue());
            if(entry.getValue().toString().equals(newLocaleValue)){
                System.out.println("====match " + entry.getValue());
                FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale)entry.getValue());            
            }
        }
    }
}
