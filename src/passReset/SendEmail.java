package passReset;


import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {
    /**
     * This method has the SMTP configuration that used to send emails.
     * @param msg contains the message the the admin want to send to the user
     * @param reciever contain the reciever email address
     *                 sendMsg method contains the email configuration "smtp configuration"
     */
    public  void sendMsg(String msg, String title, String reciever)
    {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "m.outlook.com");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("no-reply@uodabet.com","Abetas123!");
                    }
                });



        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("no-reply@uodabet.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(reciever));

            message.setSubject(title);
            message.setText(msg);


            try {
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
