package controllers;

import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.data.validation.Constraints;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.UserService;

import javax.inject.Inject;
import java.util.List;

public class AdminController extends Controller{

    private final FormFactory formFactory;

    @Inject
    public AdminController(final FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    private boolean isAdmin() {
        return "1".equals(session("admin"));
    }

    public Result login() {
        response().setHeader("Access-Control-Allow-Origin", "*");
        Form<Login> loginForm = formFactory.form(Login.class).bindFromRequest();

        if (loginForm.hasErrors()) {
            return badRequest("Wrong credentials");
        } else {
            session().clear();
            session("username", loginForm.get().getUsername());
            session("admin", "1");
            return ok("success");
        }
    }

    public Result customers() {
        if(isAdmin() == false) {
            return badRequest("Permission denied");
        }

        List<User> users = UserService.find.all();
        return ok(Json.toJson(users));
    }


    @Constraints.Validate
    public static class Login implements Constraints.Validatable<String> {

        public String username;
        public String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String validate() {
            if (username.equals("admin") && password.equals("admin")) {
                return null;
            }
            return "Wrong username or password";
        }
    }
}
