package controllers;

import models.ForgottenPasswordCode;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.data.validation.Constraints;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import services.UserService;

import javax.inject.Inject;

public class ProfileController extends Controller {

    @Inject
    FormFactory formFactory;

    private boolean isLoggedIn()
    {
        String email = session("email");
        if(email == null || email.length() <= 1) {
            return false;
        }

        return true;
    }

    public Result edit()
    {
        if(isLoggedIn() == false) {
            return badRequest("Not logged in");
        }

        Form<Profile> profileForm = formFactory.form(Profile.class).bindFromRequest();

        if (profileForm.hasErrors()) {
            return badRequest(profileForm.getGlobalError().toString());
        } else {
          //update profile
            String email = "sdfs@dsfsd.com";
            User user = UserService.findByEmail(email);
            user.setFirstname(profileForm.get().getFirstname());
            user.setLastname(profileForm.get().getLastname());
            user.setPassword(profileForm.get().getPassword());
            user.update();
            return ok("success");
        }
    }

    /**
     * retrieve profile information
     * @return
     */
    public Result get()
    {
        return ok();
    }

    @Constraints.Validate
    public static class Profile implements Constraints.Validatable<String> {

        public String firstname;
        public String lastname;
        public String password;
        public String email;

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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String validate() {

            if(email == null) {
                return "email is not valid";
            }

            //email must be unique.
//            String sessionEmail = session("email");
//            Long sessionUserId = UserService.findByEmail(sessionEmail).id;
//
//            Long existingUserId = UserService.findByEmail(this.email).id;
//
//            if(existingUserId != null && existingUserId != sessionUserId) {
//                return "Email already exists.";
//            }

            if (firstname == null) {
                return "Firstname cannot be empty";
            }

            if (lastname == null) {
                return "Lastname cannot be empty";
            }

            if (password == null || password.length() < 6) {
                return "Password is too short";
            }

            return null;
        }
    }
}
