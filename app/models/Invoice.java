package models;

import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class Invoice extends Model{

    @Id
    public Long id;

    @Constraints.Required
    @OneToOne
    public Order ordermodel;

    @Constraints.Required
    @ManyToOne
    public User user;

    @Constraints.Required
    public Date invoiceDate = new Date();

    @Constraints.Required
    public String country;

    @Constraints.Required
    public String zipcode;

    @Constraints.Required
    public String street;


    @Constraints.Required
    public String streetNumber;

    @Constraints.Required
    public String addressExtra;


    public Float shippingCosts = 0F;

    @OneToMany(mappedBy = "invoice")
    public List<InvoiceRow> invoiceRows;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrdermodel() {
        return ordermodel;
    }

    public void setOrdermodel(Order ordermodel) {
        this.ordermodel = ordermodel;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public Float getShippingCosts() {
        return shippingCosts;
    }

    public void setShippingCosts(Float shippingCosts) {
        this.shippingCosts = shippingCosts;
    }

    public List<InvoiceRow> getInvoiceRows() {
        return invoiceRows;
    }

    public void setInvoiceRows(List<InvoiceRow> invoiceRows) {
        this.invoiceRows = invoiceRows;
    }
}
