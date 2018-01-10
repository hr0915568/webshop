package controllers;

import models.ForgottenPasswordCode;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import play.api.Play;
import play.api.i18n.Lang;
import play.api.i18n.Messages;
import play.api.i18n.MessagesApi;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;
import play.mvc.*;
import play.data.*;
import play.Logger;

import javax.inject.*;

import scala.collection.Seq;
import services.MailerService;
import services.UserService;
import views.html.*;
import models.User;
import play.data.validation.Constraints.Validate;
import play.data.validation.Constraints.Validatable;
import play.api.Configuration;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class AuthController extends Controller {

    @Inject
    Configuration configuration;

    @Inject
    MailerService mailerService;

    @Inject
    MessagesApi messagesApi;

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(index.render("Your new application is ready. 123"));
    }

    private final FormFactory formFactory;



    public Result resetPassword() {
        Form<NewPassword> newPasswordForm = formFactory.form(NewPassword.class).bindFromRequest();

        if (newPasswordForm.hasErrors()) {
            return badRequest("Code not found.");
        } else {
            /*
            find the user and reset its password
             */
            ForgottenPasswordCode forgottenPasswordCode = ForgottenPasswordCode.findByCode(newPasswordForm.get().getCode());
            User user = UserService.find.byId(forgottenPasswordCode.user_id);
            user.setPassword(newPasswordForm.get().getPassword());
            user.update();

            forgottenPasswordCode.delete();
            return ok("success");
        }
    }


    public Result forgottenPassword() {

        Form<ForgottenPasswordForm> forgottenPasswordFormForm = formFactory.form(ForgottenPasswordForm.class).bindFromRequest();

        if (forgottenPasswordFormForm.hasErrors()) {
            return badRequest("Account not found.");
        } else {
            User user = UserService.findByEmail(forgottenPasswordFormForm.get().getEmail());
            /*
            generate code and send email with forgotten password link.
             */
            //ForgottenPasswordCode forgottenPasswordCode = ForgottenPasswordCode.generateNewCode(forgottenPasswordFormForm.get().getEmail());
            //String appURL = configuration.getString("play.app.url", null).toString();
            //String appURL = "http://hrwebshop.tk";
            String message = "You password is " + user.getPassword();
            mailerService.sendEmail(forgottenPasswordFormForm.get().email, "You have forgotten your password", message);
            return ok("success");
        }
    }


    @Inject
    public AuthController(final FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    public Result login() {
        Form<Login> loginForm = formFactory.form(Login.class).bindFromRequest();

        if (loginForm.hasErrors()) {
            return badRequest("Wrong credentials");
        } else {
            session().clear();
            session("email", loginForm.get().email);

            return ok("success");
        }
    }

    public Result register() {
        Form<User> registerationForm = formFactory.form(User.class).bindFromRequest();

        if (registerationForm.hasErrors()) {
            String error = registerationForm.allErrors().get(0).message();
//            Collection<Lang> candidates = Collections.singletonList(new Lang(Locale.US));
//            Messages messages = messagesApi.preferred(candidates);
            return badRequest(messagesApi.messages().get("default").get().get(error).get());
        }

        UserService.newUser(registerationForm.get().getEmail(),
                registerationForm.get().getFirstname(),
                registerationForm.get().getLastname(),
                registerationForm.get().getPassword()
        );


        return ok("success");
    }

    @Validate
    public static class Registeration{

        @Constraints.Email
        public String email;

        @Constraints.Required
        public String password;


        public String firstname;


        public String lastname;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

    }



    @Validate
    public static class Login implements Validatable<String> {

        public String email;
        public String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String validate() {
            if (User.authenticate(email, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }
    }

    @Validate
    public static class ForgottenPasswordForm implements Validatable<String> {

        public String email;

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public String validate() {
            if (UserService.findByEmail(email) == null) {
                return "User not found";
            }
            return null;
        }
    }


    @Validate
    public static class NewPassword implements Validatable<String> {

        public String code;
        public String password;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        @Override
        public String validate() {
            if (ForgottenPasswordCode.findByCode(code) == null) {
                return "Code not found";
            }
            return null;
        }
    }
}
