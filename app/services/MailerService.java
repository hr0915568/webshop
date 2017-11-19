package services;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;
import javax.inject.Inject;
import java.io.File;
import org.apache.commons.mail.EmailAttachment;

public class MailerService {
    @Inject MailerClient mailerClient;

    public void sendEmail() {
        String cid = "1234";
        Email email = new Email()
                .setSubject("Simple email")
                .setFrom("Mister FROM <from@email.com>")
                .addTo("Miss TO <to@email.com>")
                // adds attachment

                // adds inline attachment from byte array

                // adds cid attachment

                // sends text, HTML or both...
                .setBodyText("A text message")
                .setBodyHtml("<html><body><p>An <b>html</b> message with cid <img src=\"cid:" + cid + "\"></p></body></html>");
        mailerClient.send(email);
    }
}