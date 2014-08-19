package ua.bionic.adonin.mEEssenger.filters;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.bionic.socialnetwork.entities.User;

/**
 *
 * @author adonin
 */
public class SecurityFilter implements Filter {

    private FilterConfig filterConfig = null;
        
    public SecurityFilter() {
    }   
    
    @Override
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        
        User principal = null;
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String requestURI = req.getRequestURI();
        
        if (session != null) {
            principal = (User) session.getAttribute("principal");
        }
        
        if (principal != null) {
            if (principal.getIsBlocked()) {
                //res.sendRedirect(req.getContextPath() + "/faces/block.xhtml");
//                response.setContentType("text/xml");
//                response.getWriter()
//                        .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
//                        .printf("<partial-response><redirect url=\"%s\"></redirect></partial-response>", "/faces/block.xhtml");
                FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/block.xhtml");
            } else if (!principal.getTypeId().toString().equals("Administrator") && requestURI.contains("/admin/")) {
                //res.sendRedirect(req.getContextPath() + "/faces/403.xhtml");
//                response.setContentType("text/xml");
//                response.getWriter()
//                        .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
//                        .printf("<partial-response><redirect url=\"%s\"></redirect></partial-response>", "/faces/403.xhtml");
                FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/403.xhtml");
            }
        }      
        chain.doFilter(request, response);        
    }
    
    @Override
    public void destroy() {        
    }

    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("SecurityFilter()");
        }
        StringBuffer sb = new StringBuffer("SecurityFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());        
    }   
    
}
