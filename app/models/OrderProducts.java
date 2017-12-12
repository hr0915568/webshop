package models;

import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;

@Entity
public class OrderProducts extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;

    @ManyToOne(optional=false)
    public OrderModel order;

    //this only works if we use softdeletes. Otherwise name of product should be stored
    @OneToOne
    public Product orderedproduct;

    @Constraints.Required
    public Float priceAtOrdertime;

    @Constraints.Required
    public Long quantity;

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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }
}
