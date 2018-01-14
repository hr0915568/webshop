package models;

import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import services.UserService;

@Entity
public class User extends Model {

    @Id
    public Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    public List<Order> order;

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


    public static User authenticate(String email, String password) {
        User user = UserService.findByEmailAndPassword(email, password);

        return user;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}