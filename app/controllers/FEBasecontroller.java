package controllers;

import models.User;
import play.mvc.Controller;
import services.UserService;

public abstract class FEBasecontroller extends Controller {

    protected boolean isLoggedIn()
    {
        String email = session("email");
        if(email == null || email.length() <= 1) {
            return false;
        }

        return true;
    }

    protected User getSessionUser()
    {
        String email = session("email");
        return UserService.findByEmail(email);
    }
}
