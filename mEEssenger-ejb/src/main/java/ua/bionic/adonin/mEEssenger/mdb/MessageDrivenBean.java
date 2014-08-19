package ua.bionic.adonin.mEEssenger.mdb;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ua.bionic.adonin.mEEssenger.entitiesbeans.message.MessageFacadeLocal;

/**
 *
 * @author adonin
 */
@MessageDriven(mappedName = "jms/mEEssengerQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MessageDrivenBean implements MessageListener {
    
    @Resource
    private MessageDrivenContext mdc;
    @PersistenceContext(unitName = "ua.bionic_socialnetwork-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @EJB
    private MessageFacadeLocal messageEJB;
    
    public MessageDrivenBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        ObjectMessage msg = null;
        try {
            if (message instanceof ObjectMessage) {
                msg = (ObjectMessage) message;
                ua.bionic.adonin.mEEssenger.entities.Message textMessage = (ua.bionic.adonin.mEEssenger.entities.Message) msg.getObject();
                messageEJB.create(textMessage);
            }
        } catch (JMSException c) {
            c.printStackTrace();
            mdc.setRollbackOnly();
        }
    }
    
}


