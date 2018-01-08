package models;

import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Constraint;

@Entity
public class Invoice extends Model{

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    @OneToOne
    public OrderModel ordermodel;

    @Constraints.Required
    public Long totalPrice;

    @Constraints.Required
    public Long taxAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Long taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderModel getOrdermodel() {
        return ordermodel;
    }

    public void setOrdermodel(OrderModel ordermodel) {
        this.ordermodel = ordermodel;
    }


}
