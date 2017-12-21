package controllers;

import models.Category;
import models.Product;
import models.User;
import org.hibernate.validator.constraints.NotEmpty;
import play.data.Form;
import play.data.FormFactory;
import play.data.validation.Constraints;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.CategoryService;
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

    public Result editProduct(Long id) {
        if(isAdmin() == false) {
            return badRequest("Permission denied");
        }

        Product product = ProductService.findByID(id);
        if(product == null) {
            return badRequest("user not found");
        }

        Form<EditProduct>  editProductForm = formFactory.form(EditProduct.class).bindFromRequest();

        if (editProductForm.hasErrors()) {
            return badRequest("Wrong inputs");
        } else {
            product.setCategories_id(editProductForm.get().getCategories_id());
            product.setDescription(editProductForm.get().getDescription());
            product.setPrice(editProductForm.get().getPrice());
            product.setProductname(editProductForm.get().getProductname());
            product.update();
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

    public Result categories() {
        if(isAdmin() == false) {
            return badRequest("Permission denied");
        }

        List<Category> categories = CategoryService.getAllCategories();
        return ok(Json.toJson(categories));
    }




    public Result product(Long id) {
        if(isAdmin() == false) {
            return badRequest("Permission denied");
        }

        Product product = ProductService.findByID(id);
        return ok(Json.toJson(product));
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


    public static class EditProduct{

        @NotEmpty
        @Constraints.Required
        public String productname;

        @NotEmpty
        @Constraints.Required
        public String description;


        @Constraints.Required
        public Float price;

        @Constraints.Required
        public Long categories_id;

        public String getProductname() {
            return productname;
        }

        public void setProductname(String productname) {
            this.productname = productname;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public Long getCategories_id() {
            return categories_id;
        }

        public void setCategories_id(Long categories_id) {
            this.categories_id = categories_id;
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
