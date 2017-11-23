package services;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;
import javax.inject.Inject;
import java.io.File;
import org.apache.commons.mail.EmailAttachment;

public class MailerService {
    @Inject MailerClient mailerClient;

    public void sendEmail(String receiver, String subject, String message) {
        Email email = new Email()
                .setSubject(subject)
                .setFrom("Webshop@webshoppppppp.nl")
                .addTo(receiver)
                .setBodyHtml(message);
        mailerClient.send(email);
    }
}