package controllers;

import play.mvc.*;
import play.data.*;
import play.Logger;
import javax.inject.*;
import views.html.*;
import models.User;
import play.data.validation.Constraints.Validate;
import play.data.validation.Constraints.Validatable;
/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class AuthController extends Controller {

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



    @Validate
    public static class Login implements Validatable<String>{

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

}
