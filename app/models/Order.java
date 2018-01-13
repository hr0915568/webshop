package models;

import io.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "`order`")
public class Order extends Model{

    @Id
    public Long id;

    @Constraints.Required
    public String street;

    @Constraints.Required
    public String streetNumber;

    @Constraints.Required
    public String addressExtra;

    @Constraints.Required
    public String zipcode;

    @Constraints.Required
    public String country;

    @Constraints.Required
    public String city;

    public String company;

    public String notes;

    @Constraints.Required
    public Date orderTime = new Date();

    @ManyToOne(optional=false)
    public User user;


    @OneToMany(mappedBy = "order")
    public List<OrderProduct> orderProducts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getAddressExtra() {
        return addressExtra;
    }

    public void setAddressExtra(String addressExtra) {
        this.addressExtra = addressExtra;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public void setAddress(Address address) {
        this.street = address.getStreet();
        this.streetNumber = address.getStreetNumber();
        this.addressExtra = address.getAddressExtra();
        this.country = address.getCountry();
        this.zipcode = address.getZipcode();
    }
}
