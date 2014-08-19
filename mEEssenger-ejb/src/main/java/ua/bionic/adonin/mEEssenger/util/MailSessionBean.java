package ua.bionic.adonin.mEEssenger.util;

import java.io.UnsupportedEncodingException;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author adonin
 */
@Singleton
public class MailSessionBean {

    @Resource(name = "mailsender")
    private Session mailSender;
    
    public void send(String email, String messageBody) {
        Message message = new MimeMessage(mailSender);
        try {
            message.setSubject("Social network password recovery");
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setFrom(new InternetAddress("dmitrii.adonin@gmail.com", "Social network"));
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(messageBody);
            Multipart multiPart = new MimeMultipart();
            multiPart.addBodyPart(messageBodyPart);
            message.setContent(multiPart);
            Transport.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            //Logger.getLogger(MailSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Failed to send message " + e);
        }
        
    }
    
}
