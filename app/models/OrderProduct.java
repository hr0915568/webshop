package models;

import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;

@Entity
public class OrderProduct extends Model {

    @Id
    public Long id;

    @ManyToOne(optional=false)
    public Order order;

    //this only works if we use softdeletes. Otherwise name of product should be stored
    @ManyToOne
    public Product orderedproduct;

    @Constraints.Required
    public Float priceAtOrdertime;

    @Constraints.Required
    public Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPriceAtOrdertime() {
        return priceAtOrdertime;
    }

    public void setPriceAtOrdertime(Float priceAtOrdertime) {
        this.priceAtOrdertime = priceAtOrdertime;
    }

    public Product getOrderedproduct() {
        return orderedproduct;
    }

    public void setOrderedproducts(Product orderedproduct) {
        this.orderedproduct = orderedproduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
