package models;

import io.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class ProductStat extends Model {

    @Id
    public Long id;

    @ManyToOne
    @Constraints.Required
    public Product product;

    @Constraints.Required
    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date orderTime = new Date();

    @Constraints.Required
    public ProductStatAction type;

    @ManyToOne
    public User visitor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public ProductStatAction getType() {
        return type;
    }

    public void setType(ProductStatAction type) {
        this.type = type;
    }

    public User getVisitor() {
        return visitor;
    }

    public void setVisitor(User visitor) {
        this.visitor = visitor;
    }
}
