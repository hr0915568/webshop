package models;

import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
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
    public String AddressNumberAdd;

    @ManyToOne(optional=false)
    public User user;

    @ManyToMany(mappedBy="orderedProducts")
    public List<Product> products;

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
        return AddressNumberAdd;
    }

    public void setAddressNumberAdd(String addressNumberAdd) {
        AddressNumberAdd = addressNumberAdd;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
