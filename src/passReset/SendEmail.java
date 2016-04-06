package passReset;

/**
 * Created by Mohammed on 1/21/2016.
 */
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {
    /**
     *
     * @param msg contains the message the the admin want to send to the user
     * @param reciever contain the reciever email address
     *                 sendMsg method contains the email configuration "smtp configuration"
     */
    public  void sendMsg(String msg, String title, String reciever)
    {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("mulhimmm@gmail.com","Mum208720m");
                    }
                });



        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mulhimmm@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(reciever));

            message.setSubject(title);
            message.setText(msg);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(message);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }
            });



            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
