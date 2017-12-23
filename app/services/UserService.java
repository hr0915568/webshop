package services;

import io.ebean.Finder;
import models.User;

import java.util.List;

public class UserService {
    public static final Finder<Long, User> find = new Finder<>(User.class);

    public static User findByEmailAndPassword(String email, String password) {
        List<User> users = UserService.find.query().where().eq("email", email).eq("password", password)
                .setMaxRows(1)
                .findPagedList()
                .getList();

        if(users.size() == 0 ) {
            return null;
        }

        return users.get(0);
    }

    public static User findByEmail(String email) {
        List<User> users = UserService.find.query().where().eq("email", email)
                .setMaxRows(1)
                .findPagedList()
                .getList();

        if(users.size() == 0 ) {
            return null;
        }

        return users.get(0);
    }


    public static User findUserByID(Long id) {
        return UserService.find.byId(id);
    }

    public static User newUser(String email, String firstname, String lastname, String password)
    {
        User user = new User();
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPassword(password);
        user.save();

        return user;
    }
}
