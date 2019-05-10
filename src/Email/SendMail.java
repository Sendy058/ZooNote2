package Email;



import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class  SendMail  {

    public boolean send(String mail ,String kod) {
        try {
            String host = "smtp.gmail.com";
            String user = "mochnacky@spse-po.sk";
            String pass = "Divergencia128";
            String to = "mail";
            String from = "mochnacky@spse-po.sk";
            String subject = "Reset password!!";
            String text = "kod";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.user", "username");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "25");
            props.put("mail.debug", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.EnableSSL.enable", "true");

            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(text);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message send successfully");
            return true;
        } catch (Exception ex) {
            System.out.println("Chyba nepodarilo sa odoslat emial");
            return  false;

        }
    }
}

