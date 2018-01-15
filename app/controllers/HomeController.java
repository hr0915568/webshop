package controllers;

import play.data.Form;
import play.data.FormFactory;
import play.data.validation.Constraints;
import play.mvc.*;

import services.MailerService;
import views.html.*;
import javax.inject.Inject;
import services.MailerService;
/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    @Inject
    FormFactory formFactory;

    @Inject
    MailerService mailerService;

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */


    public Result index() {
        return ok("welcome. nothing to see here. 123");
    }

    public Result contact() {
        Form<ConactForm> conactFormForm = formFactory.form(ConactForm.class).bindFromRequest();

        if (conactFormForm.hasErrors()) {
            return badRequest(conactFormForm.getGlobalError().toString());
        } else {
            String body = "Email: " + conactFormForm.get().getEmail()
                    + "\n name: " + conactFormForm.get().getName()
                    + "\n phone: " + conactFormForm.get().getPhone()
                    + "\n message: " + conactFormForm.get().getMessage();
            mailerService.sendEmail("contact@hrwebshop.tk", "Message from contact form", body);
        }

        return ok("");
    }

    @Constraints.Validate
    public static class ConactForm implements Constraints.Validatable<String> {
        public String name;
        public String email;
        public String phone;
        public String message;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String validate() {

            return null;
        }
    }
}
