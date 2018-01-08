package models;

import io.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class OrderModel extends Model{

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String addressStreet;

    @Constraints.Required
    public Long addressNumber;

    @Constraints.Required
    public String addressNumberAdd;

    @Constraints.Required
    public String postalCode;

    @Constraints.Required
    @Formats.DateTime(pattern="dd-MM-YYY")
    public Date orderTime = new Date();

    @ManyToOne(optional=false)
    public User user;

    @OneToMany(mappedBy = "order")
    public List<OrderProducts> orderProducts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public Long getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(Long addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAddressNumberAdd() {
        return addressNumberAdd;
    }

    public void setAddressNumberAdd(String addressNumberAdd) {
        this.addressNumberAdd = addressNumberAdd;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderProducts> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProducts> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
