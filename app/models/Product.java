package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.Constraint;

import io.ebean.*;
import play.data.validation.*;

@Entity
public class Product extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String productname;

    @Constraints.Required
    public String description;

    @Constraints.Required
    public Float price;

    @Constraints.Required
    public Long viewed;

    @ManyToOne(optional=false)
    public Category categories;

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getViewed() {
        return viewed;
    }

    public void setViewed(Long viewed) {
        this.viewed = viewed;
    }
}