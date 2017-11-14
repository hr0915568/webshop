package models;

import java.util.*;
import javax.persistence.*;
import play.Logger;
import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class User extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String firstname;

    @Constraints.Required
    @Constraints.Email
    @Column(unique = true)
    public String email;

    @Constraints.Required
    public String password;

    @Constraints.Required
    public String lastname;

    public boolean done;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date dueDate = new Date();

    public static final Finder<Long, User> find = new Finder<>(User.class);

    public static User findByEmailAndPassword(String email, String password) {
        List<User> users = User.find.query().where().eq("email", email).eq("password", password)
                .setMaxRows(1)
                .findPagedList()
                .getList();

        if(users.size() == 0 ) {
            return null;
        }

        return users.get(0);
    }

    public static User authenticate(String email, String password) {
        User user = findByEmailAndPassword(email, password);

        return user;
    }
}