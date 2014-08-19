package ua.bionic.adonin.mEEssenger.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ua.bionic.socialnetwork.entities.Message;

/**
 *
 * @author Admin
 */
@ManagedBean
@SessionScoped
public class MessagesManagedBean implements Serializable {
 
    public MessagesManagedBean() {
    }
    
    public void sendMessage() {
        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = externalContext.getRequestParameterMap();
        
        int fromId = Integer.valueOf(params.get("fromId"));
        int toId = Integer.valueOf(params.get("toId"));
        long timestamp = Long.valueOf(params.get("timestamp"));

        try {
            Context jndiContext = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("jms/ConnectionFactoryPool");
            Queue queue = (Queue) jndiContext.lookup("jms/socialnetworkQueue");
            try {
                Connection connection = connectionFactory.createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer messageProducer = session.createProducer(queue);
                ObjectMessage message = session.createObjectMessage();
                

//                System.out.println("=== " + params.get("fromId"));
//                System.out.println("=== " + params.get("toId"));
//                System.out.println("=== " + params.get("text"));
//                System.out.println("=== " + params.get("timestamp"));                

                Message textMessage = new Message(fromId, toId, params.get("text"), false, new Date(timestamp));

                message.setObject(textMessage);
                messageProducer.send(message);
                messageProducer.close();
                connection.close();

            } catch (JMSException ex) {
                //Logger.getLogger(SendMessageCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NamingException ex) {
            //Logger.getLogger(MessagesManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
