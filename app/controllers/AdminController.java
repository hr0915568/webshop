package controllers;

import models.Product;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.data.validation.Constraints;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.ProductService;
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

    public Result customer(Long id) {
        if(isAdmin() == false) {
            return badRequest("Permission denied");
        }

        User user = UserService.findUserByID(id);
        return ok(Json.toJson(user));
    }

    public Result editCustomer(Long id) {
        if(isAdmin() == false) {
            return badRequest("Permission denied");
        }

        User user = UserService.findUserByID(id);
        if(user == null) {
            return badRequest("user not found");
        }

        Form<EditUser>  editUserForm = formFactory.form(EditUser.class).bindFromRequest();

        if (editUserForm.hasErrors()) {
            return badRequest("Wrong inputs");
        } else {
            user.setFirstname(editUserForm.get().getFirstname());
            user.setLastname(editUserForm.get().getLastname());
            user.setEmail(editUserForm.get().getEmail());
            user.setPassword(editUserForm.get().getPassword());
            user.update();
            return ok("updated");
        }
    }



    public Result products() {
        if(isAdmin() == false) {
            return badRequest("Permission denied");
        }

        List<Product> products = ProductService.getAllProducts();
        return ok(Json.toJson(products));
    }


    @Constraints.Validate
    public static class EditUser implements Constraints.Validatable<String> {

        public String firstname;
        public String lastname;
        public String email;
        public String password;

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
            if (firstname.length() > 0 && lastname.length() > 0 && password.length() > 0 && email.length() > 0) {
                return null;
            }
            return "One of more input is not valid";
        }
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
