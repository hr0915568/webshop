package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.Constraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.*;
import play.data.validation.*;

@Entity
public class Product extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public String productname;

    @Constraints.Required
    public String description;

    @Constraints.Required
    public String filename;  

    @Constraints.Required
    public Float price;

    @Constraints.Required
    public Long viewed;

    @Constraints.Required
    public Long categories_id;

    @JsonIgnore
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }        

    public Long getViewed() {
        return viewed;
    }

    public void setViewed(Long viewed) {
        this.viewed = viewed;
    }

    public Long getCategories_id() {
        return categories_id;
    }

    public void setCategories_id(Long categories_id) {
        this.categories_id = categories_id;
    }
}